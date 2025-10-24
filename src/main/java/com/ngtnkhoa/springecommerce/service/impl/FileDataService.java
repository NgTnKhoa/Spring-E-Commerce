package com.ngtnkhoa.springecommerce.service.impl;

import com.ngtnkhoa.springecommerce.entity.FileData;
import com.ngtnkhoa.springecommerce.repository.FileDataRepository;
import com.ngtnkhoa.springecommerce.service.IFileDataService;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;
import java.util.Optional;

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
  public void uploadFile(List<MultipartFile> files) throws IOException {
    if (files == null || files.isEmpty()) {
      throw new IllegalArgumentException("No files provided");
    }

    for (MultipartFile file : files) {
      if (file.isEmpty()) continue;

      String filePath = warehousePath + File.separator + file.getOriginalFilename();

      fileDataRepository.save(FileData.builder()
              .name(file.getOriginalFilename())
              .type(file.getContentType())
              .filePath(filePath)
              .build());

      File dest = new File(filePath);
      dest.getParentFile().mkdirs();

      file.transferTo(dest);
    }
  }

  @Override
  public byte[] downloadFile(String fileName) throws IOException {
    Optional<FileData> fileData = fileDataRepository.findByName(fileName);
    String filePath = fileData.get().getFilePath();
    return Files.readAllBytes(new File(filePath).toPath());
  }
}
