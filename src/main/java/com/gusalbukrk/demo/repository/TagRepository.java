package com.gusalbukrk.demo.repository;

import org.springframework.data.repository.CrudRepository;

import com.gusalbukrk.demo.model.Tag;

public interface TagRepository extends CrudRepository<Tag, Long> {}
