package com.example.GroupLink.Review;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@RestController
public class ReviewController {

    @Autowired
    private ReviewService reviewService;

    @PostMapping("/review")
    public Review createReview(@RequestBody Review review) {
        return reviewService.createReview(review);
    }

    @GetMapping("/review/group/{id}")
    public List<Review> getReviewsByGroup(@PathVariable long groupId) {
        return reviewService.getAllReviewsForGroup(groupId);
    }

    @GetMapping("/review/creator/{id}")
    public List<Review> getReviewsByCustomer(@PathVariable long customerId) {
        return reviewService.getAllReviewsForCustomer(customerId);
    }

    @PutMapping("/review/{id}")
    public Review updateReview(@PathVariable long id, @RequestBody Review review) {
        reviewService.updateReview(id, review);
        return reviewService.getReviewById(id);
    }

    @DeleteMapping("review/{id}")
    public void deleteReview(@PathVariable long id) {
        reviewService.deleteReview(id);
    }
}
