package com.rest.training.service;

import javax.inject.Inject;
import org.jvnet.hk2.annotations.Service;

import com.rest.training.repo.PetRepository;

@Service
public class PetService {

	@Inject
	private PetRepository repo;
	
	public String helloPet() {
		return repo.hiRepo() + " and Service";
	}
}
