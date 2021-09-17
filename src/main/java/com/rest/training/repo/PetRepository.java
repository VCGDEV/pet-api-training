package com.rest.training.repo;

import org.jvnet.hk2.annotations.Service;


@Service
public class PetRepository {

	public String hiRepo( ) {
		return "Hi from repo";
	}
}
