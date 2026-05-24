package me.electronicsboy.nidofy.controllers.priv;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import me.electronicsboy.nidofy.data.PrivilegeLevel;
import me.electronicsboy.nidofy.dtos.ProjectDto;
import me.electronicsboy.nidofy.exceptions.UnprivilagedExpection;
import me.electronicsboy.nidofy.models.Project;
import me.electronicsboy.nidofy.models.User;
import me.electronicsboy.nidofy.repositories.ProjectRepository;

@RestController
@RequestMapping("/admin/projects")
public class AdminProjectController {
	private final ProjectRepository projectsRepo;
	
	public AdminProjectController(ProjectRepository projectsRepo) {
		this.projectsRepo = projectsRepo;
	}
	
	@PostMapping("/put")
	public Project addProject(@RequestBody ProjectDto reqBody) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		User currentUser = (User) authentication.getPrincipal();
		if(currentUser.getPrivilegeLevel() != PrivilegeLevel.ADMIN)
			throw new UnprivilagedExpection("You aren't privilaged enough to do this!");
		
		Project project = new Project();
		
		project.setDate(reqBody.getDate());
		project.setDescription(reqBody.getDescription());
		project.setExplanation(reqBody.getExplanation());
		project.setGithub(reqBody.getGithub());
		project.setLanguages(reqBody.getLanguages());
		project.setLiveDemo(reqBody.getLiveDemo());
		project.setThumbnail(reqBody.getThumbnail());
		project.setTimeperiod(reqBody.getTimeperiod());
		project.setTitle(reqBody.getTitle());
		project.setYoutube(reqBody.getYoutube());
		
		projectsRepo.save(project);
		
		return project;
	}
}
