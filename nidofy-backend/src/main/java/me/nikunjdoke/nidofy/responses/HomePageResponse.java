package me.nikunjdoke.nidofy.responses;

import java.util.List;

public class HomePageResponse {
	private int numberOfPoems;
	private int numberOfProject;
	
	private List<PoemPreviewResponse> poetry;
	private List<ProjectPreviewResponse> projects;
	
	public HomePageResponse() {
		super();
	}

	public HomePageResponse(int numberOfPoems, int numberOfProject, List<PoemPreviewResponse> poetry, List<ProjectPreviewResponse> projects) {
		this.numberOfPoems = numberOfPoems;
		this.numberOfProject = numberOfProject;
		this.poetry = poetry;
		this.projects = projects;
	}
	
	/**
	 * @return the numberOfPoems
	 */
	public int getNumberOfPoems() {
		return numberOfPoems;
	}
	/**
	 * @param numberOfPoems the numberOfPoems to set
	 */
	public void setNumberOfPoems(int numberOfPoems) {
		this.numberOfPoems = numberOfPoems;
	}
	/**
	 * @return the numberOfProject
	 */
	public int getNumberOfProject() {
		return numberOfProject;
	}
	/**
	 * @param numberOfProject the numberOfProject to set
	 */
	public void setNumberOfProject(int numberOfProject) {
		this.numberOfProject = numberOfProject;
	}
	/**
	 * @return the poetry
	 */
	public List<PoemPreviewResponse> getPoetry() {
		return poetry;
	}
	/**
	 * @param poetry the poetry to set
	 */
	public void setPoetry(List<PoemPreviewResponse> poetry) {
		this.poetry = poetry;
	}
	/**
	 * @return the projects
	 */
	public List<ProjectPreviewResponse> getProjects() {
		return projects;
	}
	/**
	 * @param projects the projects to set
	 */
	public void setProjects(List<ProjectPreviewResponse> projects) {
		this.projects = projects;
	}
}
