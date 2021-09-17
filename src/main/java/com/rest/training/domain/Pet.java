package com.rest.training.domain;

import java.util.List;

public class Pet {
	private Integer id;
	private String name;
	private PetStatus status;
	private Category category;
	private List<Tag> tags;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public PetStatus getStatus() {
		return status;
	}
	public void setStatus(PetStatus status) {
		this.status = status;
	}
	public Category getCategory() {
		return category;
	}
	public void setCategory(Category category) {
		this.category = category;
	}
	public List<Tag> getTags() {
		return tags;
	}
	public void setTags(List<Tag> tags) {
		this.tags = tags;
	}
	
	
	
}
