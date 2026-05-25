package me.nikunjdoke.nidofy.services;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import me.nikunjdoke.nidofy.data.PrivilegeLevel;
import me.nikunjdoke.nidofy.dtos.LoginUserDto;
import me.nikunjdoke.nidofy.dtos.RegisterUserDto;
import me.nikunjdoke.nidofy.models.User;
import me.nikunjdoke.nidofy.repositories.UserRepository;

@Service
public class UserAuthenticationService {
    private final UserRepository userRepository;
    
    private final PasswordEncoder passwordEncoder;
    
    private final AuthenticationManager authenticationManager;

    public UserAuthenticationService(
        UserRepository userRepository,
        AuthenticationManager authenticationManager,
        PasswordEncoder passwordEncoder
    ) {
        this.authenticationManager = authenticationManager;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public User signup(RegisterUserDto input) {
        User user = new User()
                .setFullname(input.getFullname())
                .setEmail(input.getEmail())
                .setUsername(input.getUsername())
                .setPrivilageLevel(PrivilegeLevel.MEMBER)
                .setEnabled(false)
                .setPassword(passwordEncoder.encode(input.getPassword()));

        return userRepository.save(user);
    }

    public User authenticate(LoginUserDto input) {
        User user = userRepository.findByEmail(input.getEmail())
                .orElseThrow();
    	authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        user.getUsername(),
                        input.getPassword()
                )
        );

        return user;
    }
    
    public String getEncodedPassword(String plaintest) {
    	return passwordEncoder.encode(plaintest);
    }
}
