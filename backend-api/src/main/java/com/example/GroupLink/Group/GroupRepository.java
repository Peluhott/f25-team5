package com.example.GroupLink.Group;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface GroupRepository extends JpaRepository<Group, Long> {
    // create group
    List<Group> findByProvider_Id(Long providerId);

    public List<Group> findByTypeContainingAndLocationContainingAndProviderAverageRatingGreaterThanEqual(
            String type, String location, Double rating);

    public List<Group> findByNameContainingIgnoreCase(String name);

    @Query("SELECT DISTINCT g.type FROM Group g")
    List<String> findDistinctTypes();

    @Query("SELECT DISTINCT g.location FROM Group g")
    List<String> findDistinctLocations();

    @Query("SELECT g FROM Group g WHERE g.active = true")
    List<Group> findByActiveTrue();

   
}
