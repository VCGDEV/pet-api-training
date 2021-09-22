package com.rest.training.repo;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

import com.rest.training.domain.Pet;

import org.jvnet.hk2.annotations.Service;


@Service
public class PetRepository {

	private final Map<Integer, Pet> petsDB = new HashMap<>();// DB
	private final AtomicInteger idGenerator = new AtomicInteger(100);

	public Pet save(Pet pet) {
		Integer id = idGenerator.getAndIncrement();
		pet.setId(id);
		pet.getCategory().setId(id);
		pet.getTags().forEach(tag -> tag.setId(idGenerator.getAndIncrement()));
		petsDB.put(id, pet);
		return pet;
	}

	public Optional<Pet> findById(Integer id) {
		return Optional.ofNullable(petsDB.get(id));
	}

	public boolean existsById(Integer id) {
		return petsDB.containsKey(id);
	}

	public void deleteById(Integer id) {
		petsDB.remove(id);;
	}

	public Pet update(Pet pet) {
		petsDB.put(pet.getId(), pet);
		// agregar logica para verificar ids de categoria y tags
		return pet;
	}

	public List<Pet> findAll(){
		return petsDB.values().stream().collect(Collectors.toList());
	}

	public List<Pet> findByName(String name){
		return petsDB.values()
			.stream()
			.filter(p -> p.getName().equalsIgnoreCase(name)).collect(Collectors.toList());
	}
}
