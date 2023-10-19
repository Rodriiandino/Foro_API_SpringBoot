package com.one.foroapi.domain.service;

import com.one.foroapi.domain.dto.category.CreateCategoryDTO;
import com.one.foroapi.domain.dto.category.UpdateCategoryDTO;
import com.one.foroapi.domain.model.Category;
import com.one.foroapi.domain.repository.CategoryRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

    public Category getCategoryById(Long categoryId) {
        if (categoryRepository.findById(categoryId).isPresent()) {
            return categoryRepository.findById(categoryId).get();
        }
        return null;
    }

    public void deleteCategoryById(Long categoryId) {
        categoryRepository.deleteById(categoryId);
    }

    public Category updateCategory(Long categoryId, UpdateCategoryDTO updateCategoryDTO) {
        Category category = getCategoryById(categoryId);
        if (updateCategoryDTO.name() != null) {
            category.setName(updateCategoryDTO.name());
        }
        if (updateCategoryDTO.description() != null) {
            category.setDescription(updateCategoryDTO.description());
        }
        return categoryRepository.save(category);
    }
}
