package com.ngtnkhoa.springecommerce.service.impl;

import com.ngtnkhoa.springecommerce.entity.FileData;
import com.ngtnkhoa.springecommerce.repository.FileDataRepository;
import com.ngtnkhoa.springecommerce.service.IFileDataService;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
@RequiredArgsConstructor
public class FileDataService implements IFileDataService {

  private final FileDataRepository fileDataRepository;

  @Value("${app.file-storage.location}")
  private String warehousePath;

  @Override
  public List<String> uploadFile(List<MultipartFile> files) throws IOException {
    List<String> uploadedFileNames = new ArrayList<>();

    if (files == null || files.isEmpty()) {
      throw new IllegalArgumentException("No files provided");
    }

    for (MultipartFile file : files) {
      if (file.isEmpty()) continue;

      String originalFilename = file.getOriginalFilename();
      String extension = "";

      if (originalFilename != null && originalFilename.contains(".")) {
        extension = originalFilename.substring(originalFilename.lastIndexOf('.'));
      }

      String uniqueFilename = UUID.randomUUID() + extension;
      Path filePath = Paths.get(warehousePath, uniqueFilename);

      fileDataRepository.save(FileData.builder()
              .name(uniqueFilename)
              .type(file.getContentType())
              .filePath(filePath.toString())
              .build());

      Files.createDirectories(filePath.getParent());
      file.transferTo(filePath.toFile());
      uploadedFileNames.add(uniqueFilename);
    }

    return uploadedFileNames;
  }

  @Override
  public byte[] downloadFile(String fileName) throws IOException {
    Optional<FileData> fileData = fileDataRepository.findByName(fileName);
    String filePath = fileData.get().getFilePath();
    return Files.readAllBytes(new File(filePath).toPath());
  }
}
