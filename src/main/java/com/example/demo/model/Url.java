package com.example.demo.model;

import java.time.LocalDateTime;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;

@Data
@Document(collection = "urls")
public class Url {

    @Id
    private String id;

    private String originalUrl;
    private String shortCode;
    private LocalDateTime createdAt;
}
