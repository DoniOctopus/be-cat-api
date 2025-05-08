package com.sompo.sompotest.controller;

import com.sompo.sompotest.dto.FileDTO;
import com.sompo.sompotest.service.FileService;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("api/v1/files")
@RequiredArgsConstructor
public class FileController {

    private final FileService fileService;

    @PostMapping("/upload")
    public ResponseEntity<FileDTO> upload(@RequestParam("file") MultipartFile file) {
        FileDTO dto = fileService.store(file);
        return ResponseEntity.status(HttpStatus.CREATED).body(dto);
    }

    @GetMapping("/all")
    public List<FileDTO> listAll() {
        return fileService.getAllFiles();
    }

    @GetMapping("/{fileId}")
    public ResponseEntity<ByteArrayResource> download(@PathVariable String fileId) {
        FileDTO meta = fileService.getMetadata(fileId);
        byte[] data = fileService.getData(fileId);
        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(meta.getFileType()))
                .header(HttpHeaders.CONTENT_DISPOSITION,
                        "attachment; filename=\"" + meta.getFileName() + "\"")
                .body(new ByteArrayResource(data));
    }
}
