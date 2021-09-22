package com.rest.training.controllers;

import java.util.List;

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
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.core.Response.Status;
import com.rest.training.dto.PetDTO;
import com.rest.training.dto.hateoas.LinkVisitor;
import com.rest.training.dto.hateoas.LinkVisitorImpl;
import com.rest.training.exception.PetNotFoundException;
import com.rest.training.service.PetService;

@Path("pets")
public class PetController {

	@Inject
	private PetService petService;

	@Context//
	private UriInfo uriInfo;
	
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response addPet(@Valid PetDTO petDTO) {
		PetDTO resultDTO = petService.addPet(petDTO);
		resultDTO.accept(new LinkVisitorImpl(uriInfo));
		return Response.status(Status.CREATED)
				.entity(resultDTO)
				.build();
	}

	@Path("/{id}")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response findById(@PathParam(value = "id")Integer id) throws PetNotFoundException {
		PetDTO dto = petService.findById(id);
		dto.accept(new LinkVisitorImpl(uriInfo));
		return Response.ok(dto)
			.build();
	}

	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response findAll(@QueryParam(value = "name")String name) {
		List<PetDTO> pets = petService.find(name);
		LinkVisitor visitor = new LinkVisitorImpl(uriInfo);
		pets.forEach(p -> p.accept(visitor));
		return Response.status(Status.OK)
				.entity(pets).build();
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
		PetDTO updatedDTO = petService.updatePet(id, petDTO);
		updatedDTO.accept(new LinkVisitorImpl(uriInfo)); 
		return Response.ok(updatedDTO)
			.build();
	}


}
