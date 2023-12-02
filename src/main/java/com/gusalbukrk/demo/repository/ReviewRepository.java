package com.gusalbukrk.demo.repository;

import org.springframework.data.repository.CrudRepository;

import com.gusalbukrk.demo.model.Review;

public interface ReviewRepository extends CrudRepository<Review, Long> {}
