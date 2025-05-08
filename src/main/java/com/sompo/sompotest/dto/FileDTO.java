package com.sompo.sompotest.dto;

import lombok.*;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class FileDTO {
    private String id;
    private String fileName;
    private String fileType;
}
