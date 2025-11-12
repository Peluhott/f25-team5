package com.example.GroupLink.Review;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReviewRepository extends JpaRepository<Review, Long> {
    List<Review> findByMembership_Customer_Id(Long customerId);

    List<Review> findByMembership_Group_Provider_Id(Long providerId);

    List<Review> findByMembership_Group_id(Long groupID);
}
