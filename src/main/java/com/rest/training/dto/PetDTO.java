package com.rest.training.dto;

import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PetDTO {

	private Integer id;
	@NotBlank(message = "Name should not be empty")
	private String name;
	@NotBlank(message = "Status should not be blank")
	private String status;

	@NotNull(message = "Category should be provided")
	@Valid
	private CategoryDTO category;

	@NotEmpty(message = "Provide at least one Tag")
	private List<TagDTO> tags;
	
	
}
