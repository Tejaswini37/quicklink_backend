package com.example.demo.controller;

import java.net.URI;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.service.UrlService;

@RestController
@CrossOrigin(origins = "*")
// @CrossOrigin(origins = "http://localhost:3000")

public class UrlController {

    private final UrlService service;

    public UrlController(UrlService service) {
        this.service = service;
    }

    @PostMapping("/shorten")
    public ResponseEntity<String> shorten(@RequestBody Map<String, String> request) {
        String originalUrl = request.get("originalUrl");
        String shortCode = service.createShortUrl(originalUrl);

        // RETURN ONLY SHORT CODE
        return ResponseEntity.ok(shortCode);
        // return ResponseEntity.ok(service.createShortUrl(originalUrl));
    }

    @GetMapping("/{shortCode}")
    public ResponseEntity<Void> redirect(@PathVariable String shortCode) {

        String originalUrl = service.getOriginalUrl(shortCode);

        return ResponseEntity.status(HttpStatus.FOUND)
                .location(URI.create(originalUrl))
                .build();
    }
}
