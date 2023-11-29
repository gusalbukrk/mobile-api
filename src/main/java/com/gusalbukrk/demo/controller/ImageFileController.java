package com.gusalbukrk.demo.controller;

import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import com.gusalbukrk.demo.model.ImageFile;
import com.gusalbukrk.demo.service.ImageFileService;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@RestController
@RequestMapping("/api/images")
public class ImageFileController {
  private ImageFileService imageFileService;

  public ImageFileController(ImageFileService imageFileService) {
    this.imageFileService = imageFileService;
  }

  @GetMapping("")
  public Iterable<ImageFile> findAll() {
    return imageFileService.findAll();
  }

  @PostMapping("")
  public ResponseEntity<?> upload(@RequestParam("image") MultipartFile file) throws IOException {
    String uploadImage = imageFileService.upload(file);
    return ResponseEntity.status(HttpStatus.OK)
        .body(uploadImage);
  }

  @GetMapping("/{filename}")
  public ResponseEntity<?> download(@PathVariable String filename) throws IOException {
    try {
      UrlResource resource = imageFileService.download(filename);

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
