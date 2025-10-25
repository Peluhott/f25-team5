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

@RestController
public class ProviderController {

    @Autowired
    private ProviderService providerService;

    @PostMapping("/provider")
    public Provider addAnimal(@RequestBody Provider provider) {
        return providerService.addProvider(provider);
    }

    @PutMapping("/provider/{id}")
    public Optional<Provider> updateProvider(@PathVariable long id, @RequestBody Provider provider) {
        providerService.updateProvider(id, provider);
        return providerService.getProviderById(id);
    }

    @DeleteMapping("provider/{id}")
    public void deleteProvider(@PathVariable long id) {
        providerService.deleteProvider(id);
    }
}
