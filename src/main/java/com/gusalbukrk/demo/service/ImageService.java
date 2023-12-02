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

import com.gusalbukrk.demo.model.Image;
import com.gusalbukrk.demo.repository.ImageRepository;

@Service
public class ImageService {
  private ImageRepository imageRepository;

  public ImageService(ImageRepository imageRepository) {
    this.imageRepository = imageRepository;
  }

  private final String DIR = "./images/";

  public Iterable<Image> findAll() {
    return imageRepository.findAll();
  }

  public Optional<Image> findById(Long id) {
    return imageRepository.findById(id);
  }

  public String upload(MultipartFile file) throws IOException {
    String originalFilename = file.getOriginalFilename();
    String extension = originalFilename.substring(originalFilename.lastIndexOf(".") + 1);
    String uniqueFilename = UUID.randomUUID().toString() + "." + extension;

    System.out.println("!!! " + uniqueFilename);

    Path path = Paths.get(DIR + uniqueFilename);

    // Create directory if it doesn't exist
    if (!Files.exists(path.getParent())) {
      Files.createDirectories(path.getParent());
    }

    byte[] bytes = file.getBytes();

    Files.write(path, bytes);

    Image image = imageRepository.save(Image.builder()
        .name(uniqueFilename).build());

    System.out.println(image);

    return "file uploaded successfully : " + path;
  }

  public UrlResource download(String filename) throws IOException {
    Optional<Image> image = imageRepository.findByName(filename);

    if (!image.isPresent()) {
      throw new FileNotFoundException("Image not found");
    }

    Path path = Paths.get(DIR + image.get().getName());

    UrlResource resource = new UrlResource(path.toUri());
    return resource;
  }
}
