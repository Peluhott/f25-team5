package com.example.GroupLink.Review;

import java.util.List;
import java.util.Optional;
import java.io.IOException;
import java.io.File;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import com.example.GroupLink.Group.Group;
import com.example.GroupLink.GroupMembership.GroupMembership;
import com.example.GroupLink.GroupMembership.GroupMembershipService;
import com.example.GroupLink.Provider.ProviderRepository;
import com.fasterxml.jackson.databind.ObjectMapper;

import jakarta.transaction.Transactional;

@Service
public class ReviewService {

    ReviewRepository reviewRepository;
    GroupMembershipService groupMembershipService;

    public ReviewService(ReviewRepository reviewRepository, GroupMembershipService groupMembershipService) {
        this.reviewRepository = reviewRepository;
        this.groupMembershipService = groupMembershipService;
    }

    public Review getReviewById(@PathVariable long id) {
        return reviewRepository.findById(id).orElse(null);
    }

    public Review createReview(long membershipId, Review review) {
        GroupMembership membership = groupMembershipService.getGroupMembershipById(membershipId);
        review.setMembership(membership);
        return reviewRepository.save(review);
    }

    public List<Review> getAllReviewsForGroup(Long groupId) {
        return reviewRepository.findByMembership_Group_id(groupId);
    }

    public double getAverageRatingForProvider(Long providerId) {
        List<Review> reviews = reviewRepository.findByMembership_Group_Provider_Id(providerId);
        if (reviews.isEmpty()) {
            return 0.00;
        }
        int total = 0;
        for (Review review : reviews) {
            total += review.getRating();
        }

        int numberOfReviews = reviews.size();

        return (double) total / numberOfReviews;
    }

    public int getNumberOfRatingsForProvider(Long providerId) {
        List<Review> reviews = reviewRepository.findByMembership_Group_Provider_Id(providerId);
        if (reviews.isEmpty()) {
            return 0;
        }

        return reviews.size();
    }

    public List<Review> getAllReviewsForCustomer(Long customerId) {
        return reviewRepository.findByMembership_Customer_Id(customerId);
    }

    @Transactional
    public Review updateReview(Long reviewId, Review review) {
        Review reviewToUpdate = reviewRepository.findById(reviewId)
                .orElseThrow(() -> new IllegalArgumentException(reviewId + " not found"));

        reviewToUpdate.setMessage(review.getMessage());
        reviewToUpdate.setRating(review.getRating());
        reviewToUpdate.setResponse(review.getResponse());
        return reviewToUpdate;
    }

    public void deleteReview(Long reviewId) {
        reviewRepository.deleteById(reviewId);
    }

}
