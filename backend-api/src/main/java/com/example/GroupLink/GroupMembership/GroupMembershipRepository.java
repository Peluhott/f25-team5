package com.example.GroupLink.GroupMembership;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.GroupLink.Customer.Customer;
import com.example.GroupLink.Group.Group;

@Repository
public interface GroupMembershipRepository extends JpaRepository<GroupMembership, Long> {

    List<GroupMembership> findByCustomer(Customer customer);

    List<GroupMembership> findByGroup(Group group);

    List<GroupMembership> findByGroup_Provider_Id(Long providerId);
}
