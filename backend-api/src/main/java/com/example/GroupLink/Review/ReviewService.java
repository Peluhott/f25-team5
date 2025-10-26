package com.example.GroupLink.Review;

import java.util.List;
import java.util.Optional;
import java.io.IOException;
import java.io.File;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import com.example.GroupLink.Provider.ProviderRepository;
import com.fasterxml.jackson.databind.ObjectMapper;

import jakarta.transaction.Transactional;

@Service
public class ReviewService {

    @Autowired
    ReviewRepository reviewRepository;

    public Review getReviewById(@PathVariable long id) {
        return reviewRepository.findById(id).orElse(null);
    }

    public Review createReview(Review review) {
        return reviewRepository.save(review);
    }

    public List<Review> getAllReviewsForGroup(Long groupId) {
        return reviewRepository.findByMembership_Group_id(groupId);
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
