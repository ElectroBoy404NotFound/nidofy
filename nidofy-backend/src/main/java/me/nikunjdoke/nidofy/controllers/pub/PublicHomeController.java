package me.nikunjdoke.nidofy.controllers.pub;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import me.nikunjdoke.nidofy.models.Poem;
import me.nikunjdoke.nidofy.models.Project;
import me.nikunjdoke.nidofy.repositories.PoemRepository;
import me.nikunjdoke.nidofy.repositories.ProjectRepository;
import me.nikunjdoke.nidofy.responses.HomePageResponse;
import me.nikunjdoke.nidofy.responses.PoemPreviewResponse;
import me.nikunjdoke.nidofy.responses.ProjectPreviewResponse;

@RequestMapping("/public/home")
@RestController
public class PublicHomeController {
	private final PoemRepository poemsRepo;
	private final ProjectRepository projectsRepo;
	
	public PublicHomeController(PoemRepository poemsRepo, ProjectRepository projectsRepo) {
		this.poemsRepo = poemsRepo;
		this.projectsRepo = projectsRepo;
	}
	
	@GetMapping("/getHome")
	public HomePageResponse getHome() {
		List<PoemPreviewResponse> poems = new ArrayList<>();
		
		for(Poem p : poemsRepo.findAll()) {
			PoemPreviewResponse curr = new PoemPreviewResponse();
			
			curr.setId(p.getId());
			curr.setTitle(p.getTitle());
			curr.setDate(p.getDate());
			curr.setSignature(p.getSignature());
			curr.setCreatedAt(p.getCreatedAt());
			
			String line = p.getPoem().get(0).get(0);
			int i = 1;
			int j = 0;
			while(line.length() < 60) {
				line += ' ';
				line += p.getPoem().get(j).get(i);
				i++;
				
				if(i >= p.getPoem().get(j).size()) {
					j++;
					i = 0;
				}
			}
			curr.setContent(line.substring(0, 65) + "...");
			
			poems.add(curr);
		}
		
		List<ProjectPreviewResponse> projects = new ArrayList<>();
		
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
			
			projects.add(curr);
		}
		
		return new HomePageResponse(poems.size(), projects.size(), poems, projects);
	}
}
