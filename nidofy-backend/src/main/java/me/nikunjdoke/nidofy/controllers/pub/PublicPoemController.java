package me.nikunjdoke.nidofy.controllers.pub;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import me.nikunjdoke.nidofy.models.Poem;
import me.nikunjdoke.nidofy.repositories.PoemRepository;
import me.nikunjdoke.nidofy.responses.PoemPreviewResponse;

@RequestMapping("/public/poems")
@RestController
public class PublicPoemController {
	private final PoemRepository poemsRepo;
	
	public PublicPoemController(PoemRepository poemsRepo) {
		this.poemsRepo = poemsRepo;
	}
	
	@GetMapping("/getPoemById")
	public Poem getPoemById(@RequestParam(name="id") long id) {
		return poemsRepo.findById(id).orElseThrow();
	}
	
	@GetMapping("/getAllPoems")
	public List<PoemPreviewResponse> getAllPoems() {
		List<PoemPreviewResponse> response = new ArrayList<>();
		
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
			
			response.add(curr);
		}
		
		return response;
	}
}
