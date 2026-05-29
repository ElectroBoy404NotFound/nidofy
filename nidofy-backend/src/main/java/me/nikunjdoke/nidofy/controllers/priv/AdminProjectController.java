package me.nikunjdoke.nidofy.controllers.priv;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import me.nikunjdoke.nidofy.data.PrivilegeLevel;
import me.nikunjdoke.nidofy.dtos.ProjectDto;
import me.nikunjdoke.nidofy.exceptions.NotFoundException;
import me.nikunjdoke.nidofy.exceptions.UnprivilagedExpection;
import me.nikunjdoke.nidofy.models.Project;
import me.nikunjdoke.nidofy.models.User;
import me.nikunjdoke.nidofy.repositories.ProjectRepository;

@RestController
@RequestMapping("/admin/projects")
public class AdminProjectController {
	@Value("${uploads.targetdirectory}")
    private String uploadDirectory;
	
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
	
	@PostMapping("/uploadImage")
	public String uploadImage(@RequestParam("file") MultipartFile file) throws IOException {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		User currentUser = (User) authentication.getPrincipal();
		if(currentUser.getPrivilegeLevel() != PrivilegeLevel.ADMIN)
			throw new UnprivilagedExpection("You aren't privilaged enough to do this!");
		
		String originalFilename = file.getOriginalFilename();
    	
    	String extension = "";

    	int dotIndex = originalFilename.lastIndexOf('.');
    	if (dotIndex != -1) {
    	    extension = originalFilename.substring(dotIndex);
    	    originalFilename = file.getName();
    	}
    	
    	String filename = originalFilename + "-" + System.currentTimeMillis() + extension;
    	
    	Path directoryPath = Path.of((uploadDirectory).replaceAll(" ", "_"));
    	Path targetLocation = directoryPath.resolve(filename.replaceAll(" ", "_"));
		Files.copy(file.getInputStream(), targetLocation, StandardCopyOption.REPLACE_EXISTING);
		
		return "/uploads/" + filename;
	}
	
	@DeleteMapping("/delete/{id}")
	public void deleteProject(@PathVariable long id) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		User currentUser = (User) authentication.getPrincipal();
		if(currentUser.getPrivilegeLevel() != PrivilegeLevel.ADMIN)
			throw new UnprivilagedExpection("You aren't privilaged enough to do this!");
		
		if(!projectsRepo.existsById(id))
			throw new NotFoundException();
		
		projectsRepo.deleteById(id);
	}
}
