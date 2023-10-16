package com.one.foroapi.domain.service;

import com.one.foroapi.domain.dto.category.CreateCategoryDTO;
import com.one.foroapi.domain.model.Category;
import com.one.foroapi.domain.repository.CategoryRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class CategoryService {

    private final CategoryRepository categoryRepository;

    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public Category createCategory(CreateCategoryDTO createCategoryDTO) {
        Category category = new Category(createCategoryDTO);
        return categoryRepository.save(category);
    }

    public Page<Category> getAllCategories(Pageable pagination) {
        return categoryRepository.findAll(pagination);
    }
}
