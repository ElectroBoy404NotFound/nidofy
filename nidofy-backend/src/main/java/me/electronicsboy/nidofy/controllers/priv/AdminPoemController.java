package me.electronicsboy.nidofy.controllers.priv;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import me.electronicsboy.nidofy.data.PrivilegeLevel;
import me.electronicsboy.nidofy.dtos.PoemDto;
import me.electronicsboy.nidofy.exceptions.UnprivilagedExpection;
import me.electronicsboy.nidofy.models.Poem;
import me.electronicsboy.nidofy.models.User;
import me.electronicsboy.nidofy.repositories.PoemRepository;

@RestController
@RequestMapping("/admin/poems")
public class AdminPoemController {
	private final PoemRepository poemsRepo;
	
	public AdminPoemController(PoemRepository poemsRepo) {
		this.poemsRepo = poemsRepo;
	}
	
	@PostMapping("/put")
	public Poem addPoem(@RequestBody PoemDto reqBody) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		User currentUser = (User) authentication.getPrincipal();
		if(currentUser.getPrivilegeLevel() != PrivilegeLevel.ADMIN)
			throw new UnprivilagedExpection("You aren't privilaged enough to do this!");
		
		Poem poem = new Poem(reqBody.getTitle(), reqBody.getPoem(), reqBody.getDate(), reqBody.getSignature());
		poemsRepo.save(poem);
		
		return poem;
	}
}
