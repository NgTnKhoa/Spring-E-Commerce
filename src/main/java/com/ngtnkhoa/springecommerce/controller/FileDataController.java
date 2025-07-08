package com.ngtnkhoa.springecommerce.controller;

import com.ngtnkhoa.springecommerce.dto.response.BaseResponse;
import com.ngtnkhoa.springecommerce.service.IFileDataService;

import java.io.IOException;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/v1/files")
@RequiredArgsConstructor
public class FileDataController {

  private final IFileDataService fileDataService;

  @PostMapping
  public ResponseEntity<BaseResponse> upload(@RequestParam("file") MultipartFile file) throws IOException {
    fileDataService.uploadFile(file);
    return ResponseEntity
        .ok()
        .body(BaseResponse.builder()
            .message("Uploaded successfully")
            .statusCode(HttpStatus.NO_CONTENT.value())
            .status(true)
            .build());
  }

  @GetMapping("/{fileName}")
  public ResponseEntity<?> download(@PathVariable String fileName) throws IOException {
    byte[] fileData = fileDataService.downloadFile(fileName);
    return ResponseEntity
        .ok()
        .contentType(MediaType.valueOf("image/png"))
        .body(fileData);
  }
}
