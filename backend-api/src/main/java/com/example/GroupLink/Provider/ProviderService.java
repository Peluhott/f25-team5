package com.example.GroupLink.Provider;

import java.util.List;
import java.util.Optional;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.io.File;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.multipart.MultipartFile;

import com.example.GroupLink.GroupMembership.GroupMembershipService;
import com.example.GroupLink.Review.ReviewRepository;
import com.example.GroupLink.Review.ReviewService;
import com.fasterxml.jackson.databind.ObjectMapper;

import jakarta.transaction.Transactional;

import jakarta.persistence.EntityNotFoundException;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

@Service
public class ProviderService {
    // get by id
    // create provider

    ProviderRepository providerRepository;

    public ProviderService(ProviderRepository providerRepository) {
        this.providerRepository = providerRepository;

    }

    private static final Path UPLOAD_DIR = Paths.get("src/main/resources/provider");

    public Provider getProviderById(long id) {
        return providerRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException(id + " not found"));
    }

    public Provider getProviderByUsername(String username) {
        return providerRepository.findByUsername(username);
    }

    private String saveProfilePicture(Long providerId, MultipartFile file) {
        try {
            String original = file.getOriginalFilename();
            if (original == null || !original.contains("."))
                return null;
            String ext = original.substring(original.lastIndexOf('.') + 1);
            String fileName = "provider" + providerId + "." + ext;
            Files.createDirectories(UPLOAD_DIR);
            Path target = UPLOAD_DIR.resolve(fileName);
            try (InputStream in = file.getInputStream()) {
                Files.copy(in, target, StandardCopyOption.REPLACE_EXISTING);
            }
            return fileName;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public Provider addProvider(Provider provider, MultipartFile profilePicture) {
        if (providerRepository.existsByEmail(provider.getEmail())) {
            throw new IllegalArgumentException("User already registered with this email");
        }

        Provider savedProvider = providerRepository.save(provider);

        if (profilePicture != null && !profilePicture.isEmpty()) {
            String fileName = saveProfilePicture(savedProvider.getId(), profilePicture);
            if (fileName != null) {
                savedProvider.setProfilePicturePath(fileName);
                savedProvider = providerRepository.save(savedProvider);
            }
        }

        return savedProvider;
    }

    @Transactional
    public Provider updateProvider(Long providerId, Provider form, MultipartFile profilePicture) {
        Provider existing = providerRepository.findById(providerId)
                .orElseThrow(() -> new IllegalArgumentException("Provider " + providerId + " not found"));

        existing.setEmail(form.getEmail());
        existing.setPassword(form.getPassword());
        existing.setUsername(form.getUsername());

        if (profilePicture != null && !profilePicture.isEmpty()) {
            String fileName = saveProfilePicture(providerId, profilePicture);
            if (fileName != null) {
                existing.setProfilePicturePath(fileName);
            }
        }

        return existing;
    }

    public void deleteProvider(Long providerId) {
        providerRepository.deleteById(providerId);
    }

}
