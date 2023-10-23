package com.one.foroapi.domain.service;

import com.one.foroapi.domain.dto.category.CreateCategoryDTO;
import com.one.foroapi.domain.dto.category.UpdateCategoryDTO;
import com.one.foroapi.domain.model.Category;
import com.one.foroapi.domain.repository.CategoryRepository;
import jakarta.persistence.EntityNotFoundException;
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

    public Category getCategoryById(Long categoryId) {
        if (!categoryRepository.existsById(categoryId)) {
            throw new EntityNotFoundException("Category not found: " + categoryId);
        }
        return categoryRepository.findById(categoryId).orElse(null);
    }

    public void deleteCategoryById(Long categoryId) {
        if (!categoryRepository.existsById(categoryId)) {
            throw new EntityNotFoundException("Category not found: " + categoryId);
        }
        categoryRepository.deleteById(categoryId);
    }

    public Category updateCategory(Long categoryId, UpdateCategoryDTO updateCategoryDTO) {
        Category category = getCategoryById(categoryId);
        if (category != null) {
            if (updateCategoryDTO.name() != null) {
                category.setName(updateCategoryDTO.name());
            }
            if (updateCategoryDTO.description() != null) {
                category.setDescription(updateCategoryDTO.description());
            }
            return categoryRepository.save(category);
        } else {
            throw new EntityNotFoundException("Category not found: " + categoryId);
        }
    }
}
