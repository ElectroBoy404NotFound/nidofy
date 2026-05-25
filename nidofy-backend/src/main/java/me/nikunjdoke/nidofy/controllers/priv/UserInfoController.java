package me.nikunjdoke.nidofy.controllers.priv;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import me.nikunjdoke.nidofy.data.PrivilegeLevel;
import me.nikunjdoke.nidofy.exceptions.UnprivilagedExpection;
import me.nikunjdoke.nidofy.exceptions.UserNotFoundException;
import me.nikunjdoke.nidofy.models.User;
import me.nikunjdoke.nidofy.repositories.UserRepository;
import me.nikunjdoke.nidofy.responses.PrivilageLevelResponse;

@RequestMapping("/info/users")
@RestController
public class UserInfoController {
	@Autowired
	private UserRepository userRepository;
	
	@GetMapping("/me")
	public ResponseEntity<User> authenticatedUserInfo() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		User currentUser = (User) authentication.getPrincipal();
		return ResponseEntity.ok(currentUser);
    }
	
	@GetMapping("/me/privilage_level")
	public ResponseEntity<PrivilageLevelResponse> getPrivilageLevelString() {
	    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
	    User user = (User) authentication.getPrincipal();

	    return ResponseEntity.ok(new PrivilageLevelResponse().setPrivilageLevel(user.getPrivilegeLevel()));
	}
	
	@GetMapping("/getById/{id}")
	public ResponseEntity<User> getById(@PathVariable long id) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		if(((User) authentication.getPrincipal()).getPrivilegeLevel() != PrivilegeLevel.ADMIN)
			throw new UnprivilagedExpection("You aren't privilaged enough to do this!");
		
		if(!userRepository.existsById(id))
			throw new UserNotFoundException("No user with id %d exists!".formatted(id));
		
        return ResponseEntity.ok(userRepository.findById(id).get());
    }
}
