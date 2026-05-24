package me.electronicsboy.nidofy.controllers.pub;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import me.electronicsboy.nidofy.models.Project;
import me.electronicsboy.nidofy.repositories.ProjectRepository;
import me.electronicsboy.nidofy.responses.ProjectPreviewResponse;

@RequestMapping("/public/projects")
@RestController
public class PublicProjectController {
	private final ProjectRepository projectsRepo;
	
	public PublicProjectController(ProjectRepository projectsRepo) {
		this.projectsRepo = projectsRepo;
	}
	
	@GetMapping("/getProjectById")
	public Project getProjectById(@RequestParam(name="id") long id) {
		return projectsRepo.findById(id).orElseThrow();
	}
	
	@GetMapping("/getAllProjects")
	public List<ProjectPreviewResponse> getAllProjects() {
		List<ProjectPreviewResponse> response = new ArrayList<>();
		
		for(Project p : projectsRepo.findAll()) {
			ProjectPreviewResponse curr = new ProjectPreviewResponse();
			
			curr.setId(p.getId());
			curr.setTitle(p.getTitle());
			curr.setDate(p.getDate());
			curr.setDescription(p.getDescription());
			curr.setThumbnail(p.getThumbnail());
			curr.setTimeperiod(p.getTimeperiod());
			curr.setLanguages(p.getLanguages());
			curr.setCreatedAt(p.getCreatedAt());
			
			response.add(curr);
		}
		
		return response;
	}
}
