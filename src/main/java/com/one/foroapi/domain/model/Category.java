package com.one.foroapi.domain.model;

import com.one.foroapi.domain.dto.category.CreateCategoryDTO;
import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "categories")
@Entity(name = "Category")
@EqualsAndHashCode(of = "id")
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String description;

    public Category(CreateCategoryDTO createCategoryDTO) {
        this.name = createCategoryDTO.name();
        this.description = createCategoryDTO.description();
    }
}
