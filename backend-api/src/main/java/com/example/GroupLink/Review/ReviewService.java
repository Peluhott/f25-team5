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

public class ReviewService {

    @Autowired
    ReviewRepository reviewRepository;

    public Optional<Review> getReviewById(@PathVariable long id) {
        return Optional.ofNullable(reviewRepository.findById(id).orElse(null));
    }

    public Review addReview(Review review) {
        return reviewRepository.save(review);
    }

    @Transactional
    public Review updateReview(Long reviewId, Review review) {
        Review reviewToUpdate = reviewRepository.findById(reviewId)
                .orElseThrow(() -> new IllegalArgumentException(reviewId + " not found"));

        reviewToUpdate.setGroupID(review.getGroupId());
        reviewToUpdate.setMessage(review.getMessage());
        reviewToUpdate.setRating(review.getRating());
        reviewToUpdate.setResponse(review.getResponse());
        return reviewToUpdate;
    }

    public void deleteReview(Long reviewId) {
        reviewRepository.deleteById(reviewId);
    }

}
