package com.rest.training.service;


import javax.inject.Inject;
import org.jvnet.hk2.annotations.Service;

import com.rest.training.domain.Pet;
import com.rest.training.dto.PetDTO;
import com.rest.training.exception.PetNotFoundException;
import com.rest.training.mappers.PetMapper;
import com.rest.training.repo.PetRepository;

// mapstruct y lombok
@Service
public class PetService {

	@Inject
	private PetRepository repo;

	@Inject
	private PetMapper mapper;
	
	public PetDTO addPet(PetDTO dto) {
		Pet pet = repo.save(mapper.fromDTO(dto));
		return mapper.fromEntity(pet);
	}

	public PetDTO findById(Integer id) throws PetNotFoundException {
		return repo.findById(id)
			.map(mapper::fromEntity)
			.orElseThrow(PetNotFoundException::new);
	}
	
}
