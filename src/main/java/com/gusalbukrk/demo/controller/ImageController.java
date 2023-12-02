package com.gusalbukrk.demo.controller;

import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import com.gusalbukrk.demo.model.Image;
import com.gusalbukrk.demo.service.ImageService;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Optional;

@RestController
@RequestMapping("/api/images")
public class ImageController {
  private ImageService imageService;

  public ImageController(ImageService imageService) {
    this.imageService = imageService;
  }

  @GetMapping("")
  public Iterable<Image> findAll() {
    return imageService.findAll();
  }

  @PostMapping("")
  public ResponseEntity<?> upload(@RequestParam("image") MultipartFile file) throws IOException {
    String uploadImage = imageService.upload(file);
    return ResponseEntity.status(HttpStatus.OK)
        .body(uploadImage);
  }

  @GetMapping("/{id}")
  public Optional<Image> findById(@PathVariable Long id) {
    return imageService.findById(id);
  }

  @GetMapping("/findByName/{filename}")
  public ResponseEntity<?> download(@PathVariable String filename) throws IOException {
    try {
      UrlResource resource = imageService.download(filename);

      if (resource.exists() || resource.isReadable()) {
        return ResponseEntity.status(HttpStatus.OK)
            .contentType(MediaType.valueOf(getContentType(filename)))
            .body(resource);
      } else {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Image not found");
      }
    } catch (FileNotFoundException e) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Image not found");
    } catch (IOException e) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error occurred");
    }
  }

  private String getContentType(String filename) throws IOException {
    Path path = Paths.get(filename);
    String contentType = Files.probeContentType(path);

    if (contentType == null) {
      return "application/octet-stream";
    } else {
      return contentType;
    }
  }
}
