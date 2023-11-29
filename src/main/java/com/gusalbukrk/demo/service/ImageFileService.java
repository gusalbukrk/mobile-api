package com.gusalbukrk.demo.service;

import java.util.UUID;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Optional;

import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.gusalbukrk.demo.model.ImageFile;
import com.gusalbukrk.demo.repository.ImageFileRepository;

@Service
public class ImageFileService {
  private ImageFileRepository imageFileRepository;

  public ImageFileService(ImageFileRepository imageFileRepository) {
    this.imageFileRepository = imageFileRepository;
  }

  private final String DIR = "./images/";

  public String upload(MultipartFile image) throws IOException {
    String originalFilename = image.getOriginalFilename();
    String extension = originalFilename.substring(originalFilename.lastIndexOf(".") + 1);
    String uniqueFilename = UUID.randomUUID().toString() + "." + extension;

    System.out.println("!!! " + uniqueFilename);

    Path path = Paths.get(DIR + uniqueFilename);

    // Create directory if it doesn't exist
    if (!Files.exists(path.getParent())) {
      Files.createDirectories(path.getParent());
    }

    byte[] bytes = image.getBytes();

    Files.write(path, bytes);

    ImageFile imageFile = imageFileRepository.save(ImageFile.builder()
        .name(uniqueFilename).build());

    System.out.println(imageFile);

    return "file uploaded successfully : " + path;
  }

  public UrlResource download(String image) throws IOException {
    Optional<ImageFile> imageFile = imageFileRepository.findByName(image);

    if (!imageFile.isPresent()) {
      throw new FileNotFoundException("Image not found");
    }

    Path path = Paths.get(DIR + imageFile.get().getName());

    UrlResource resource = new UrlResource(path.toUri());
    return resource;
  }

  public Iterable<ImageFile> findAll() {
    return imageFileRepository.findAll();
  }
}
