package com.ngtnkhoa.springecommerce.service;

import java.io.IOException;
import org.springframework.web.multipart.MultipartFile;

public interface IFileDataService {

  void uploadFile(MultipartFile file) throws IOException;

  byte[] downloadFile(String fileName) throws IOException;
}
