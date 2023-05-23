package com.feedback.hafit.repository;

import com.feedback.hafit.domain.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Long> {
}