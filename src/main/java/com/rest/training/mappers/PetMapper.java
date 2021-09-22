package com.rest.training.mappers;

import java.util.stream.Collectors;

import com.rest.training.domain.Category;
import com.rest.training.domain.Pet;
import com.rest.training.domain.PetStatus;
import com.rest.training.domain.Tag;
import com.rest.training.dto.CategoryDTO;
import com.rest.training.dto.PetDTO;
import com.rest.training.dto.TagDTO;

import org.jvnet.hk2.annotations.Service;

@Service
public class PetMapper {
    public Pet fromDTO(PetDTO dto) {
		Pet pet = new Pet();

		pet.setName(dto.getName());
		pet.setStatus(PetStatus.valueOf(dto.getStatus()));

		Category ct = new Category();
		ct.setName(dto.getCategory().getName());
		if(dto.getCategory().getId() != null) {
			ct.setId(dto.getCategory().getId());
		}
		pet.setCategory(ct);

		//tags
		pet.setTags(
			dto.getTags().stream()
				.map(tdto -> tdto.getId() == null ? new Tag(tdto.getName()) : new Tag(tdto.getId(), tdto.getName()))
				.collect(Collectors.toList())
		);

		return pet;
	}

	public PetDTO fromEntity(Pet pet) {
		PetDTO petDTO = new PetDTO();
		petDTO.setName(pet.getName());
		petDTO.setId(pet.getId());
		petDTO.setStatus(pet.getStatus().name());

		CategoryDTO ct = new CategoryDTO();
		ct.setId(pet.getCategory().getId());
		ct.setName(pet.getCategory().getName());

		petDTO.setCategory(ct);
		//tags
		petDTO.setTags(
			pet.getTags().stream()
				.map(tentity -> new TagDTO(tentity.getId(), tentity.getName()))
				.collect(Collectors.toList())
		);

		return petDTO;
	}
}
