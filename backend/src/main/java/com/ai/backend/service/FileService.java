package com.ai.backend.service;


import com.ai.backend.entity.UploadedFile;
import com.ai.backend.repository.FileRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Service
@RequiredArgsConstructor
public class FileService {

    private final FileRepository repo;
    private final AiClientService aiClient;

 public UploadedFile save(MultipartFile file) throws IOException {
    Path path = Paths.get("/app/uploads/" + file.getOriginalFilename());
    Files.createDirectories(path.getParent());
    Files.write(path, file.getBytes());

    UploadedFile entity = new UploadedFile();
    entity.setFileName(file.getOriginalFilename());
    entity.setFilePath(path.toString());
    

    String aiResponse = aiClient.transcribe(entity.getId(), path.toString());
    
    entity.setTranscript(aiResponse); 

    return repo.save(entity);
}
}