package com.rest.training.repo;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

import com.rest.training.domain.Pet;
import com.rest.training.pagination.Page;
import com.rest.training.pagination.PageImpl;

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

	// page > 0 
	public Page<Pet> getPage(String name, int page, int size) {
		Collection<Pet> dataset = null;

		if(name != null && !name.trim().isEmpty()) {
			dataset = petsDB.values().stream()
						.filter(p -> p.getName().equalsIgnoreCase(name))
						.collect(Collectors.toList());
		} else {
			dataset = petsDB.values();
		}

		//page = 1 [0, 1, 2 ,3] -> [1, 2] 
		Collection<Pet> paginated = dataset.stream()
										.skip((page -1) * size)
										.limit(size)
										.collect(Collectors.toList());
		// TODO calculate page count
		return new PageImpl<>(paginated, page, paginated.size(), dataset.size(), size);

	}
}
