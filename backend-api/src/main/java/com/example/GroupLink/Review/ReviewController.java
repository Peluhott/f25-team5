package com.example.GroupLink.Review;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@Controller
public class ReviewController {

    @Autowired
    private ReviewService reviewService;

    // create review
    // get reviews per group
    // get reviews by customer
    // update reviews
    // delete reviews

    @PostMapping("/provider/{providerId}/reviews/{reviewId}/reply")
    public String replyToReview(@PathVariable Long providerId,
            @PathVariable Long reviewId,
            @RequestParam("response") String response) {
        reviewService.replyToReview(reviewId, response);
        return "redirect:/provider/" + providerId + "/reviews";
    }

}
