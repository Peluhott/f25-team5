package com.example.GroupLink.Provider;

import java.util.List;
import java.util.Optional;
import java.io.IOException;
import java.io.File;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import com.fasterxml.jackson.databind.ObjectMapper;

import jakarta.transaction.Transactional;

@Service
public class ProviderService {
    // get by id
    // create provider
    @Autowired
    ProviderRepository providerRepository;

    public Provider getProviderById(long id) {
        return providerRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException(id + " not found"));
    }

    public Provider getProviderByUsername(String username) {
        return providerRepository.findByUsername(username);
    }

    public Provider addProvider(Provider provider) {
        if (providerRepository.existsByEmail(provider.getEmail())) {
            throw new IllegalArgumentException("User already registed with this email");
        }
        return providerRepository.save(provider);
    }

    @Transactional
    public Provider updateProvider(Long providerId, Provider provider) {
        Provider providerToUpdate = providerRepository.findById(providerId)
                .orElseThrow(() -> new IllegalArgumentException(providerId + " not found"));

        providerToUpdate.setEmail(provider.getEmail());
        providerToUpdate.setPassword(provider.getPassword());
        providerToUpdate.setUsername(provider.getUsername());

        return providerToUpdate;
    }

    public void deleteProvider(Long providerId) {
        providerRepository.deleteById(providerId);
    }

}
