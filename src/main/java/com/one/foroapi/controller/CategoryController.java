package com.one.foroapi.controller;

import com.one.foroapi.domain.dto.category.CreateCategoryDTO;
import com.one.foroapi.domain.dto.category.UpdateCategoryDTO;
import com.one.foroapi.domain.model.Category;
import com.one.foroapi.domain.service.CategoryService;
import com.one.foroapi.infra.exceptions.CustomErrorResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
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
@SecurityRequirement(name = "bearer-key")
@Tag(name = "Categories", description = "API for managing forum categories. Categories define the themes and topics.")
public class CategoryController {

    private final CategoryService categoryService;

    @Autowired
    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @PostMapping("/create")
    @Transactional
    @Operation(
            summary = "Create a new category",
            description = "Create a new category with a name and description"
    )
    @ApiResponse(
            responseCode = "201",
            description = "Category created successfully.",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = Category.class)
            )
    )
    public ResponseEntity<Category> createCategory(@RequestBody @Valid CreateCategoryDTO createCategoryDTO) {
        Category newCategory = categoryService.createCategory(createCategoryDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(newCategory);
    }

    @GetMapping("/all")
    @Operation(
            summary = "Get All Categories",
            description = "Retrieve a list of all forum categories."
    )
    @ApiResponse(
            responseCode = "200",
            description = "List of forum categories.",
            content = @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = Category.class))
            )
    )
    public ResponseEntity<Page<Category>> getAllCategories(@PageableDefault(size = 5) Pageable pagination) {
        Page<Category> categories = categoryService.getAllCategories(pagination);
        return ResponseEntity.ok(categories);
    }

    @GetMapping("/{categoryId}")
    @Operation(
            summary = "Get Category by ID",
            description = "Retrieve a category by its ID."
    )
    @ApiResponse(
            responseCode = "200",
            description = "Category details.",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = Category.class)
            )
    )
    @ApiResponse(
            responseCode = "404",
            description = "Category not found.",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = CustomErrorResponse.class)
            )
    )
    public ResponseEntity<Category> getCategoryById(@PathVariable Long categoryId) {
        Category category = categoryService.getCategoryById(categoryId);
        return ResponseEntity.ok(category);
    }

    @PutMapping("/{categoryId}")
    @Transactional
    @Operation(
            summary = "Update Category",
            description = "Update a category by its ID."
    )
    @ApiResponse(
            responseCode = "200",
            description = "Category updated successfully.",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = Category.class)
            )
    )
    @ApiResponse(
            responseCode = "404",
            description = "Category not found.",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = CustomErrorResponse.class)
            )
    )
    public ResponseEntity<Category> updateCategory(@PathVariable Long categoryId, @RequestBody @Valid UpdateCategoryDTO updateCategoryDTO) {
        Category category = categoryService.updateCategory(categoryId, updateCategoryDTO);
        return ResponseEntity.ok(category);
    }

    @DeleteMapping("/{categoryId}")
    @Transactional
    @Operation(
            summary = "Delete Category by ID",
            description = "Delete a category by its ID."
    )
    @ApiResponse(
            responseCode = "204",
            description = "Category deleted successfully."
    )
    @ApiResponse(
            responseCode = "404",
            description = "Category not found.",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = CustomErrorResponse.class)
            )
    )
    public ResponseEntity<?> deleteCategoryById(@PathVariable Long categoryId) {
        categoryService.deleteCategoryById(categoryId);
        return ResponseEntity.noContent().build();
    }
}
