package com.one.foroapi.controller;

import com.one.foroapi.domain.dto.category.CreateCategoryDTO;
import com.one.foroapi.domain.model.Category;
import com.one.foroapi.domain.service.CategoryService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
