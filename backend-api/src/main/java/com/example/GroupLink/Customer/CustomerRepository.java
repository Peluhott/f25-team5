package com.example.GroupLink.Customer;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.GroupLink.CustomerNotification.CustomerNotification;
import com.example.GroupLink.GroupMembership.GroupMembership;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {

    boolean existsByEmail(String email);

    boolean existsByUsername(String username);

    Optional<Customer> findByEmail(String email);

    Optional<Customer> findByUsername_(String username);

    Customer findByUsername(String username);

    @Query("SELECT c FROM CustomerNotification c WHERE c.customer.id = ?1 ORDER BY c.id DESC")
    List<CustomerNotification> findAllNotificationsByIdDesc(Long id);

    @Query("SELECT gm FROM GroupMembership gm WHERE gm.customer.id = ?1 AND gm.status = 'active' AND gm.group.active = true ORDER BY gm.id DESC")
    List<GroupMembership> findAcceptedActiveGroupMembershipsByIdDesc(Long id);

    @Query("SELECT gm FROM GroupMembership gm WHERE gm.customer.id = ?1 AND gm.status = 'inactive' ORDER BY gm.id DESC")
    List<GroupMembership> findAcceptedInactiveGroupMembershipsByIdDesc(Long id);

    @Query("SELECT gm FROM GroupMembership gm WHERE gm.customer.id = ?1 AND gm.status = 'pending' ORDER BY gm.id DESC")
    List<GroupMembership> findPendingGroupMembershipsByIdDesc(Long id);

}
