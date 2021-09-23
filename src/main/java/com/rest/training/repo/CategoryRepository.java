package com.rest.training.repo;

import com.rest.training.domain.Category;
import org.jvnet.hk2.annotations.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

@Service
public class CategoryRepository {
    private final Map<Integer, Category> categoriesDB = new HashMap<>();
    private final AtomicInteger idGenerator = new AtomicInteger(100);

    public Category save(Category category) {
        category.setId(idGenerator.getAndIncrement());
        categoriesDB.put(category.getId(), category);

        return category;

    }

    public List<Category> findAll() {
        return categoriesDB.values().stream().collect(Collectors.toList());
    }

    public Optional<Category> findById(Integer id) {
        return Optional.ofNullable(categoriesDB.get(id));
    }

    public boolean existsById(Integer id) {
        return categoriesDB.containsKey(id);
    }

    public Category updateCategory(Category category) {
        categoriesDB.put(category.getId(), category);
        return category;
    }

    public void deleteById(Integer id) {
        categoriesDB.remove(id);
    }
}
