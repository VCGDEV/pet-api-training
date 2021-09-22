package com.rest.training.controllers;

import javax.inject.Inject;
import javax.validation.Valid;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import com.rest.training.dto.PetDTO;
import com.rest.training.exception.PetNotFoundException;
import com.rest.training.service.PetService;

@Path("pets")
public class PetController {

	@Inject
	private PetService petService;
	
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response addPet(@Valid PetDTO petDTO) {
		PetDTO resultDTO = petService.addPet(petDTO);
		return Response.status(Status.CREATED)
				.entity(resultDTO)
				.build();
	}

	@Path("/{id}")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response findById(@PathParam(value = "id")Integer id) throws PetNotFoundException {
		return Response.ok(petService.findById(id))
			.build();
	}

	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response findAll(@QueryParam(value = "name")String name) {
		return Response.status(Status.OK)
				.entity(petService.find(name)).build();
	}

	@Path("/{id}")
	@DELETE
	@Produces(MediaType.APPLICATION_JSON)
	public Response deleteById(@PathParam(value = "id")Integer id) throws PetNotFoundException {
		petService.deleteById(id);
		return Response.noContent()
			.build();
	}
	
	@Path("/{id}")
	@PUT
	@Produces(MediaType.APPLICATION_JSON)
	public Response updatePet(@PathParam(value = "id")Integer id,
			@Valid PetDTO petDTO) throws PetNotFoundException {
		return Response.ok(petService.updatePet(id, petDTO))
			.build();
	}

}
