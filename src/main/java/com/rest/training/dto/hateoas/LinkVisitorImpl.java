package com.rest.training.dto.hateoas;

import java.util.List;

import javax.ws.rs.core.Link;
import javax.ws.rs.core.UriInfo;

import com.rest.training.dto.CategoryDTO;
import com.rest.training.dto.PetDTO;
import com.rest.training.dto.TagDTO;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class LinkVisitorImpl implements LinkVisitor {
    private UriInfo uriInfo;
    
    // tags y categories
    @Override
    public void visit(CategoryDTO category) {
        Link self = Link.fromUriBuilder(uriInfo.getBaseUriBuilder().path("categories").path("{id}")).rel("self").build(category.getId());
        category.setLinks(List.of(self));
    }

    @Override
    public void visit(PetDTO pet) {
        Link self = Link.fromUriBuilder(uriInfo.getBaseUriBuilder().path("pets").path("{id}")).rel("self").build(pet.getId());
        pet.setLinks(List.of(self));
        pet.getCategory().accept(this);
        pet.getTags().forEach(t -> t.accept(this));
    }

    @Override
    public void visit(TagDTO tag) {
        Link self = Link.fromUriBuilder(uriInfo.getBaseUriBuilder().path("tags").path("{id}")).rel("self").build(tag.getId());
        tag.setLinks(List.of(self));
    }
}
