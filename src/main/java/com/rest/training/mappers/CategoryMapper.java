package com.rest.training.mappers;

import com.rest.training.domain.Category;
import com.rest.training.dto.CategoryDTO;
import org.jvnet.hk2.annotations.Service;

@Service
public class CategoryMapper {
    public Category fromDTO(CategoryDTO dto) {

        Category category = new Category();
        category.setId(dto.getId());
        category.setName(dto.getName());


        return category;
    }

    public CategoryDTO fromEntity(Category category) {

        CategoryDTO categoryDTO = new CategoryDTO();
        categoryDTO.setId(category.getId());
        categoryDTO.setName(category.getName());

        return categoryDTO;
    }
}
