package com.one.foroapi.domain.repository;

import com.one.foroapi.domain.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Long> {
}
