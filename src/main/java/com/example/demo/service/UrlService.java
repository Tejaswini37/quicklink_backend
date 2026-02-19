package com.example.demo.service;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.example.demo.model.Url;
import com.example.demo.repository.UrlRepository;

@Service
public class UrlService {

    private final UrlRepository repository;

    public UrlService(UrlRepository repository) {
        this.repository = repository;
    }

    public String createShortUrl(String originalUrl) {

        // ✅ Ensure URL has protocol
        if (!originalUrl.startsWith("http://") &&
            !originalUrl.startsWith("https://")) {
            originalUrl = "https://" + originalUrl;
        }

        String shortCode = UUID.randomUUID().toString().substring(0, 6);

        Url url = new Url();
        url.setOriginalUrl(originalUrl);
        url.setShortCode(shortCode);
        url.setCreatedAt(LocalDateTime.now());

        repository.save(url);

        System.out.println("Saved URL: " + shortCode);

        // return "http://localhost:8080/" + shortCode;
        return shortCode;
    }

    public String getOriginalUrl(String shortCode) {

        Optional<Url> urlOptional = repository.findByShortCode(shortCode);

        if (urlOptional.isEmpty()) {
            throw new RuntimeException("Short URL does not exist");
        }

        String originalUrl = urlOptional.get().getOriginalUrl();

        System.out.println("Redirecting to: " + originalUrl);

        return originalUrl;
    }
}
