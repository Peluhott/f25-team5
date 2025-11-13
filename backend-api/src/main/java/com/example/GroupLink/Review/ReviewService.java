package com.example.GroupLink.Review;

import java.util.List;
import java.util.Optional;
import java.io.IOException;
import java.io.File;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import com.example.GroupLink.Group.Group;
import com.example.GroupLink.Group.GroupService;
import com.example.GroupLink.GroupMembership.GroupMembership;
import com.example.GroupLink.GroupMembership.GroupMembershipService;
import com.example.GroupLink.Provider.ProviderRepository;
import com.example.GroupLink.Provider.ProviderService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.example.GroupLink.Provider.Provider;

import jakarta.transaction.Transactional;

@Service
public class ReviewService {

    ReviewRepository reviewRepository;
    GroupMembershipService groupMembershipService;
    ProviderService providerService;
    GroupService groupService;

    public ReviewService(ReviewRepository reviewRepository, GroupMembershipService groupMembershipService,
            ProviderService providerService, GroupService groupService) {
        this.reviewRepository = reviewRepository;
        this.groupMembershipService = groupMembershipService;
        this.providerService = providerService;
        this.groupService = groupService;
    }

    public Review getReviewById(@PathVariable long id) {
        return reviewRepository.findById(id).orElse(null);
    }

    public Review createReview(long membershipId, Review review) {
        GroupMembership membership = groupMembershipService.getGroupMembershipById(membershipId);
        review.setMembership(membership);

        Group groupToRetrieveProvider = membership.getGroup();

        Provider providerToUpdate = groupToRetrieveProvider.getProvider();

        Review newReview = reviewRepository.save(review);

        updateAverageRatingForProvider(providerToUpdate.getId());

        return newReview;
    }

    public List<Review> getAllReviewsForGroup(Long groupId) {
        return reviewRepository.findByMembership_Group_id(groupId);
    }

    public void updateAverageRatingForProvider(Long providerId) {
        List<Review> reviews = reviewRepository.findByMembership_Group_Provider_Id(providerId);
        Provider provider = providerService.getProviderById(providerId);
        if (reviews.isEmpty()) {
            provider.setAverageRating(0.0);

        } else {
            int total = 0;
            for (Review review : reviews) {
                total += review.getRating();
            }

            int numberOfReviews = reviews.size();

            double averageRating = total / numberOfReviews;

            provider.setAverageRating(averageRating);
        }

    }

    public void updateNumberOfRatingsForProvider(Long providerId) {
        List<Review> reviews = reviewRepository.findByMembership_Group_Provider_Id(providerId);
        Provider provider = providerService.getProviderById(providerId);
        if (reviews.isEmpty()) {
            provider.setReviewCount(0);
        } else {
            int size = reviews.size();
            provider.setReviewCount(size);
        }

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
