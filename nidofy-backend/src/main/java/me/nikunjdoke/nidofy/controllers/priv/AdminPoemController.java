package me.nikunjdoke.nidofy.controllers.priv;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import me.nikunjdoke.nidofy.data.PrivilegeLevel;
import me.nikunjdoke.nidofy.dtos.EditPoemDto;
import me.nikunjdoke.nidofy.dtos.PoemDto;
import me.nikunjdoke.nidofy.exceptions.NotFoundException;
import me.nikunjdoke.nidofy.exceptions.UnprivilagedExpection;
import me.nikunjdoke.nidofy.models.Poem;
import me.nikunjdoke.nidofy.models.User;
import me.nikunjdoke.nidofy.repositories.PoemRepository;

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
		
		Poem poem = new Poem(reqBody.getTitle(), reqBody.getPoem(), reqBody.getDate(), reqBody.getSignature(), reqBody.getSignatureLength());
		poemsRepo.save(poem);
		
		return poem;
	}
	
	@PostMapping("/edit")
	public Poem editPoem(@RequestBody EditPoemDto reqBody) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		User currentUser = (User) authentication.getPrincipal();
		if(currentUser.getPrivilegeLevel() != PrivilegeLevel.ADMIN)
			throw new UnprivilagedExpection("You aren't privilaged enough to do this!");
		
		Poem poem = poemsRepo.findById(reqBody.getId())
				.orElseThrow(NotFoundException::new);
		
		poem.setPoem(reqBody.getPoem());
		poem.setTitle(reqBody.getTitle());
		poem.setDate(reqBody.getDate());
		poem.setSignature(reqBody.getSignature());
		poem.setSignatureLength(reqBody.getSignatureLength());
		
		poemsRepo.save(poem);
		
		return poem;
	}
	
	@DeleteMapping("/delete/{id}")
	public void deletePoem(@PathVariable long id) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		User currentUser = (User) authentication.getPrincipal();
		if(currentUser.getPrivilegeLevel() != PrivilegeLevel.ADMIN)
			throw new UnprivilagedExpection("You aren't privilaged enough to do this!");
		
		if(!poemsRepo.existsById(id))
			throw new NotFoundException();
		
		poemsRepo.deleteById(id);
	}
}
