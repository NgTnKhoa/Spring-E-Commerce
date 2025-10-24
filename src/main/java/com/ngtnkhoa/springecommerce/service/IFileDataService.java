package com.ngtnkhoa.springecommerce.service;

import java.io.IOException;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

public interface IFileDataService {

  void uploadFile(List<MultipartFile> files) throws IOException;

  byte[] downloadFile(String fileName) throws IOException;
}
