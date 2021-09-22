package com.rest.training.dto.hateoas;

import com.rest.training.dto.CategoryDTO;
import com.rest.training.dto.PetDTO;
import com.rest.training.dto.TagDTO;

public interface LinkVisitor {
    void visit(PetDTO pet);
    void visit(CategoryDTO category);
    void visit(TagDTO tag);
}
