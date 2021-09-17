package com.rest.training.controllers;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import com.rest.training.dto.PetDTO;
import com.rest.training.service.PetService;

@Path("pets")
public class PetController {

	@Inject
	private PetService petService;
	
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response addPet(PetDTO petDTO) {
		PetDTO resultDTO = petService.addPet(petDTO);
		return Response.status(Status.CREATED)
				.entity(resultDTO)
				.build();
	}

	@Path("/{id}")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response findById(@PathParam(value = "id")Integer id) {
		return Response.ok(petService.findById(id))
			.build();
	}
}
