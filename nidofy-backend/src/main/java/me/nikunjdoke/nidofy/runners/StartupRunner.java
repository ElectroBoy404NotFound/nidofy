package me.nikunjdoke.nidofy.runners;

import java.security.SecureRandom;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import me.nikunjdoke.nidofy.data.PrivilegeLevel;
import me.nikunjdoke.nidofy.dtos.RegisterUserDto;
import me.nikunjdoke.nidofy.models.User;
import me.nikunjdoke.nidofy.repositories.UserRepository;
import me.nikunjdoke.nidofy.services.EmailService;
import me.nikunjdoke.nidofy.services.UserAuthenticationService;

@Component
public class StartupRunner implements CommandLineRunner {
	private static final Logger LOGGER = LoggerFactory.getLogger(UserAuthenticationService.class);
	
	@Autowired
    private UserRepository userRepository;
	@Autowired
    private UserAuthenticationService authService;
	@Autowired
	private EmailService emailService;
	
	private final String CHARACTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789@#$%&*!";
    private final int PASSWORD_LENGTH = 12; // Change as needed
    private final SecureRandom RANDOM = new SecureRandom();

    @Value("${security.adminemail}")
    private String adminEmail;
    
    private String generateSecurePassword() {
        StringBuilder password = new StringBuilder(PASSWORD_LENGTH);
        for (int i = 0; i < PASSWORD_LENGTH; i++) {
            int index = RANDOM.nextInt(CHARACTERS.length());
            password.append(CHARACTERS.charAt(index));
        }
        return password.toString();
    }

    private void makeAdminUser() {
		RegisterUserDto rud = new RegisterUserDto();
		rud.setFullname("Super Admin");
		rud.setUsername("cmsadmin");
		rud.setEmail(adminEmail);
//		rud.setPrivilegeLevel(PrivilegeLevel.CMSADMIN);
		
		String password = generateSecurePassword();
		
		rud.setPassword(password);
        User newuser = authService.signup(rud);
        newuser.setPrivilageLevel(PrivilegeLevel.ADMIN);
        newuser.setEnabled(true);
        userRepository.save(newuser);
        
        emailService.sendSimpleEmail(adminEmail, "Admin user info", EmailService.ADMINACCOUNTPWD_BODY.formatted("cmsadmin", password));
        LOGGER.info("Created user 'cmsadmin' with email '%s' and with password '%s'".formatted(adminEmail, password));
	}
        
	@Override
	public void run(String... args) throws Exception {	
		for(User u : userRepository.findAll()) {
    		if(u.getPrivilegeLevel() == PrivilegeLevel.ADMIN) {
    			LOGGER.info("An cmsadmin (%s) already exists, not creating a new user...".formatted(u.getUsername()));
    			return;
    		}
    	}
		
		makeAdminUser();
    }
}
