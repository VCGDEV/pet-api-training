package com.rest.training.controllers;

import com.rest.training.dto.CategoryDTO;
import com.rest.training.dto.hateoas.LinkVisitorImpl;
import com.rest.training.exception.CategoryNotFoundException;
import com.rest.training.service.CategoryService;

import javax.inject.Inject;
import javax.validation.Valid;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import java.util.List;

@Path("categories")
public class CategoryController {
    @Inject
    private CategoryService categoryService;

    @Context
    private UriInfo uriInfo;

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addCategory(
            @Valid CategoryDTO categoryDTO) {
        CategoryDTO category = categoryService.addCategory(categoryDTO);
        category.accept(new LinkVisitorImpl(uriInfo));
        return Response.status(Response.Status.CREATED).entity(category).build();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response findAll() {
        List<CategoryDTO> categories = categoryService.findAll();
        categories.forEach(ct -> ct.accept(new LinkVisitorImpl(uriInfo)));
        return Response.ok().entity(categories).build();
    }

    @Path("/{id}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response findById(@PathParam(value = "id") Integer id) throws CategoryNotFoundException {
        CategoryDTO categoryDTO = categoryService.findById(id);
        categoryDTO.accept(new LinkVisitorImpl(uriInfo));

        return Response.ok().entity(categoryDTO).build();
    }

    @PUT
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response update(@PathParam(value = "id") Integer id,
                           @Valid CategoryDTO categoryDTO) throws CategoryNotFoundException {
        CategoryDTO categoryDTOUpdated = categoryService.update(id, categoryDTO);
        categoryDTOUpdated.accept(new LinkVisitorImpl(uriInfo));
        return Response.ok(categoryDTOUpdated).build();
    }

    @DELETE
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response deleteById(@PathParam(value = "id") Integer id) throws CategoryNotFoundException {
        categoryService.deleteById(id);
        return Response.noContent().build();
    }
}
