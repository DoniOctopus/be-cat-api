package com.sompo.sompotest.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CatDTO {
    private String id;
    private String url;
    private int width;
    private int height;
}
