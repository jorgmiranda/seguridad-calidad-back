package com.seguridad.seguridad_calidad_back.repository;

import com.seguridad.seguridad_calidad_back.model.RecipeComment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RecipeCommentRepository extends JpaRepository<RecipeComment, Long> {
}
