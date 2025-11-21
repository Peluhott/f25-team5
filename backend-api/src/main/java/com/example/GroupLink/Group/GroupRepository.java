package com.example.GroupLink.Group;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GroupRepository extends JpaRepository<Group, Long> {
    // create group
    List<Group> findByProvider_Id(Long providerId);

}
