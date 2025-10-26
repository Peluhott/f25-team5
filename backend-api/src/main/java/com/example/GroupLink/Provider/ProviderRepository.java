package com.example.GroupLink.Provider;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProviderRepository extends JpaRepository<Provider, Long> {
    // sign up
    // login
    boolean existsByEmail(String email);

    Optional<Provider> findByEmail(String email);
}
