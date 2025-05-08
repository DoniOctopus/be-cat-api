package com.sompo.sompotest.entity;


import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Cat {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    @Column(name = "url")
    private String url;
    @Column(name = "width")
    private int width;
    @Column(name = "height")
    private int height;

}
