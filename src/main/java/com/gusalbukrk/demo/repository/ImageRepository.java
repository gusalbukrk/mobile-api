package com.gusalbukrk.demo.repository;

import com.gusalbukrk.demo.model.Image;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface ImageRepository extends CrudRepository<Image, Long> {
    Optional<Image> findByName(String fileName);
}
