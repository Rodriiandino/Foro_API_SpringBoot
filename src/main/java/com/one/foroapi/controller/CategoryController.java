package com.one.foroapi.controller;

import com.one.foroapi.domain.dto.category.CreateCategoryDTO;
import com.one.foroapi.domain.model.Category;
import com.one.foroapi.domain.service.CategoryService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/categories")
public class CategoryController {

    private final CategoryService categoryService;

    @Autowired
    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @PostMapping("/create")
    @Transactional
    public ResponseEntity<Category> createCategory(@RequestBody @Valid CreateCategoryDTO createCategoryDTO) {
        Category newCategory = categoryService.createCategory(createCategoryDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(newCategory);
    }

    @GetMapping("/all")
    public ResponseEntity<Page<Category>> getAllCategories(@PageableDefault(size = 5) Pageable pagination) {
        Page<Category> categories = categoryService.getAllCategories(pagination);
        return ResponseEntity.ok(categories);
    }

    @GetMapping("/{categoryId}")
    public ResponseEntity<Category> getCategoryById(@PathVariable Long categoryId) {
        Category category = categoryService.getCategoryById(categoryId);
        return ResponseEntity.ok(category);
    }

    @DeleteMapping("/{categoryId}")
    @Transactional
    public ResponseEntity<?> deleteCategoryById(@PathVariable Long categoryId) {
        categoryService.deleteCategoryById(categoryId);
        return ResponseEntity.noContent().build();
    }
}
