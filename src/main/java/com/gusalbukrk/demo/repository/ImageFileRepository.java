package com.gusalbukrk.demo.repository;

import com.gusalbukrk.demo.model.ImageFile;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface ImageFileRepository extends CrudRepository<ImageFile, Long> {
    Optional<ImageFile> findByName(String fileName);
}
