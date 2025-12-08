package com.example.GroupLink.Provider;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.io.File;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.multipart.MultipartFile;

import com.example.GroupLink.Customer.Customer;
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

import org.springframework.web.multipart.MultipartFile;

import com.example.GroupLink.Review.Review;
import jakarta.transaction.Transactional;

@Service
public class ProviderService {
    // get by id
    // create provider

    ProviderRepository providerRepository;
    ReviewRepository reviewRepository;

    public ProviderService(ProviderRepository providerRepository, ReviewRepository reviewRepository) {
        this.providerRepository = providerRepository;
        this.reviewRepository = reviewRepository;

    }

    private static final Path UPLOAD_DIR = Paths.get("backend-api/src/main/resources/static/provider/images/");

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
            String fileName = UUID.randomUUID().toString() + "provider" + providerId + "." + ext;
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

    public Provider updateProfilePicture(Long providerId, MultipartFile file) {
        Provider existing = providerRepository.findById(providerId)
                .orElseThrow(() -> new IllegalArgumentException("Provider " + providerId + " not found"));

        if (file == null || file.isEmpty()) {
            throw new IllegalArgumentException("No file uploaded");
        }

        String fileName = saveProfilePicture(providerId, file);
        if (fileName == null) {
            throw new IllegalArgumentException("Failed to save profile picture");
        }

        existing.setProfilePicturePath(fileName);
        return providerRepository.save(existing);
    }

    // New: update username/email (requires current password)
    @Transactional
    public Provider updateProfile(Long providerId, String newUsername, String newEmail, String currentPassword) {
        Provider existing = providerRepository.findById(providerId)
                .orElseThrow(() -> new IllegalArgumentException("Provider " + providerId + " not found"));

        if (currentPassword == null || !existing.getPassword().equals(currentPassword)) {
            throw new IllegalArgumentException("Incorrect current password");
        }

        if (newUsername != null && !newUsername.isBlank()) {
            existing.setUsername(newUsername);
        }
        if (newEmail != null && !newEmail.isBlank()) {
            existing.setEmail(newEmail);
        }

        return providerRepository.save(existing);
    }

    // New: update password
    @Transactional
    public Provider updatePassword(Long providerId, String currentPassword, String newPassword, String rePassword) {
        Provider existing = providerRepository.findById(providerId)
                .orElseThrow(() -> new IllegalArgumentException("Provider " + providerId + " not found"));

        if (currentPassword == null || !existing.getPassword().equals(currentPassword)) {
            throw new IllegalArgumentException("Incorrect current password");
        }
        if (newPassword == null || !newPassword.equals(rePassword)) {
            throw new IllegalArgumentException("New passwords do not match");
        }

        existing.setPassword(newPassword);
        return providerRepository.save(existing);
    }

    public void deleteProvider(Long providerId) {
        providerRepository.deleteById(providerId);
    }

    public List<Review> getAllReviewsForProvider(Long providerId) {
        return reviewRepository.findByProvider_Id(providerId);
    }

    public boolean authenticate(String username, String password) {
        Provider provider = providerRepository.findByUsername(username);
        if (provider != null) {
            return provider.getPassword().equals(password);
        }
        return false;
    }
    
    public long getProviderIdByReviewId(Long reviewId) {
        Review review = reviewRepository.findById(reviewId)
                .orElseThrow(() -> new IllegalArgumentException("Review " + reviewId + " not found"));
        return review.getProvider().getId();
    }

}
