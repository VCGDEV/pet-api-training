package com.rest.training.dto;

import javax.validation.constraints.NotEmpty;

public class CategoryDTO {
	private Integer id;

	@NotEmpty(message = "Please provide category name")
	private String name;
	
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
	
	
}
