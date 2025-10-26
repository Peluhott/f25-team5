package com.example.GroupLink.Provider;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.example.GroupLink.Customer.Customer;

import java.util.List;
import java.util.Optional;

@RestController
public class ProviderController {

    @Autowired
    private ProviderService providerService;

    @GetMapping("/provider/{id}")
    public ResponseEntity<Provider> getProvider(@PathVariable Long id) {
        return ResponseEntity.ok(providerService.getProviderById(id));
    }

    @PostMapping("/provider")
    public ResponseEntity<Provider> createProvider(@RequestBody Provider provider) {
        return ResponseEntity.ok(providerService.addProvider(provider));
    }

    @PutMapping("/provider/{id}")
    public ResponseEntity<Provider> updateProvider(@PathVariable long id, @RequestBody Provider provider) {
        providerService.updateProvider(id, provider);
        return ResponseEntity.ok(providerService.getProviderById(id));
    }

    @DeleteMapping("provider/{id}")
    public void deleteProvider(@PathVariable long id) {
        providerService.deleteProvider(id);
    }
}
