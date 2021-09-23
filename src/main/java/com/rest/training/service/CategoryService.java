package com.rest.training.service;

import com.rest.training.domain.Category;
import com.rest.training.dto.CategoryDTO;
import com.rest.training.exception.CategoryNotFoundException;
import com.rest.training.mappers.CategoryMapper;
import com.rest.training.repo.CategoryRepository;
import org.jvnet.hk2.annotations.Service;

import javax.inject.Inject;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CategoryService {
    @Inject
    private CategoryRepository categoryRepository;

    @Inject
    private CategoryMapper categoryMapper;

    public CategoryDTO addCategory(CategoryDTO categoryDTO) {
        Category category = categoryMapper.fromDTO(categoryDTO);
        return categoryMapper.fromEntity(categoryRepository.save(category));
    }

    public List<CategoryDTO> findAll() {
        List<Category> categories = categoryRepository.findAll();
        return categories.stream()
                .map(categoryMapper::fromEntity)
                .collect(Collectors.toList());
    }

    public CategoryDTO findById(Integer id) throws CategoryNotFoundException {
        return categoryRepository.findById(id)
                .map(categoryMapper::fromEntity)
                .orElseThrow(CategoryNotFoundException::new);
    }

    public CategoryDTO update(Integer id, CategoryDTO categoryDTO) throws CategoryNotFoundException {
        Category category = categoryMapper.fromDTO(categoryDTO);
        if (!categoryRepository.existsById(id)) {
            throw new CategoryNotFoundException();
        }

        return categoryMapper.fromEntity(categoryRepository.updateCategory(category));
    }

    public void deleteById(Integer id) throws CategoryNotFoundException {
        if (!categoryRepository.existsById(id)) {
            throw new CategoryNotFoundException();
        }

        categoryRepository.deleteById(id);
    }

}
