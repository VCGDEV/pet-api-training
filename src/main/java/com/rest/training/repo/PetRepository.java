package com.rest.training.repo;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

import com.rest.training.domain.Pet;

import org.jvnet.hk2.annotations.Service;


@Service
public class PetRepository {

	private final Map<Integer, Pet> petsDB = new HashMap<>();
	private final AtomicInteger idGenerator = new AtomicInteger(100);

	public Pet save(Pet pet) {
		Integer id = idGenerator.getAndIncrement();
		pet.setId(id);
		pet.getCategory().setId(id);
		pet.getTags().forEach(tag -> tag.setId(idGenerator.getAndIncrement()));
		petsDB.put(id, pet);
		return pet;
	}

	public Pet findById(Integer id) {
		return petsDB.get(id);
	}
}
