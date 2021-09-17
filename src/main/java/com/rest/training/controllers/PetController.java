package com.rest.training.controllers;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.rest.training.service.PetService;
// app-context/webapi/pets
@Path("pets")
public class PetController {

	@Inject
	private PetService petService;
	
	@GET
	@Produces(MediaType.TEXT_PLAIN)
	public String hello() {
		// hi form repo and service
		return petService.helloPet();
	}
}
