package com.example.GroupLink.GroupMembership;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface  GroupMembershipRepository extends JpaRepository<GroupMembership, Long>{
    
    List<GroupMembership> findByCustomerId(Long groupId);

    List<GroupMembership> findByGroupId(Long groupId);
}
