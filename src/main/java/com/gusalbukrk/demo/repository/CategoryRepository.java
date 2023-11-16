package com.gusalbukrk.demo.repository;

import org.springframework.data.repository.CrudRepository;

import com.gusalbukrk.demo.model.Category;

public interface CategoryRepository extends CrudRepository<Category, Long> {}
