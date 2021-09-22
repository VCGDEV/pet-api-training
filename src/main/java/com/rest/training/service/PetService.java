package com.rest.training.service;


import java.util.List;
import java.util.stream.Collectors;

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

	public void deleteById(Integer id) throws PetNotFoundException {
		if(!repo.existsById(id)) {
			throw new PetNotFoundException();
		} else {
			repo.deleteById(id);
		}
	}

	public PetDTO updatePet(Integer id, PetDTO dto) throws PetNotFoundException {
		if(!repo.existsById(id)) {
			throw new PetNotFoundException();
		} else {
			Pet toSave = mapper.fromDTO(dto);
			toSave.setId(id);
			Pet pet = repo.update(toSave);
			return mapper.fromEntity(pet);
		}
	}

	public List<PetDTO> find(String name){
		if(name != null && !name.trim().isEmpty())	{
			return repo.findByName(name).stream()
					.map(mapper::fromEntity)
					.collect(Collectors.toList());
		} else {
			return repo.findAll().stream()
					.map(mapper::fromEntity)
					.collect(Collectors.toList());
		}
	}
	
}
