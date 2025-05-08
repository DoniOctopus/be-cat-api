package com.sompo.sompotest.service.impl;

import com.sompo.sompotest.dto.FileDTO;
import com.sompo.sompotest.entity.FileEntity;
import com.sompo.sompotest.repository.FileRepository;
import com.sompo.sompotest.service.FileService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class FileServiceImpl implements FileService {

    private final FileRepository fileRepository;

    @Override
    public FileDTO store(MultipartFile file) {
        try {
            FileEntity entity = FileEntity.builder()
                    .fileName(file.getOriginalFilename())
                    .fileType(file.getContentType())
                    .data(file.getBytes())
                    .build();
            entity = fileRepository.save(entity);
            return new FileDTO(entity.getId(), entity.getFileName(), entity.getFileType());
        } catch (IOException e) {
            throw new RuntimeException("Failed to store file", e);
        }
    }

    @Override
    public List<FileDTO> getAllFiles() {
        return fileRepository.findAll().stream()
                .map(e -> new FileDTO(e.getId(), e.getFileName(), e.getFileType()))
                .collect(Collectors.toList());
    }

    @Override
    public FileDTO getMetadata(String fileId) {
        FileEntity e = fileRepository.findById(fileId)
                .orElseThrow(() -> new RuntimeException("File not found"));
        return new FileDTO(e.getId(), e.getFileName(), e.getFileType());
    }

    @Override
    public byte[] getData(String fileId) {
        return fileRepository.findById(fileId)
                .orElseThrow(() -> new RuntimeException("File not found"))
                .getData();
    }
}
