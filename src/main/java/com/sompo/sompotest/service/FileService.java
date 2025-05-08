package com.sompo.sompotest.service;

import com.sompo.sompotest.dto.FileDTO;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface FileService {
    FileDTO store(MultipartFile file);
    List<FileDTO> getAllFiles();
    FileDTO getMetadata(String fileId);
    byte[] getData(String fileId);
}
