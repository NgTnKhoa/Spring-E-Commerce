package com.ngtnkhoa.springecommerce.repository;

import com.ngtnkhoa.springecommerce.entity.FileData;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface FileDataRepository extends JpaRepository<FileData, Long> {

  Optional<FileData> findByName(String name);
}