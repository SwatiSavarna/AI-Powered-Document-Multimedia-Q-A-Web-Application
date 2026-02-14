package com.ai.backend.repository;

import com.ai.backend.entity.UploadedFile;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FileRepository extends JpaRepository<UploadedFile, Long> {
}