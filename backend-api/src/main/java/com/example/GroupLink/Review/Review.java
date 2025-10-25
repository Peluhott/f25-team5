package com.example.GroupLink.Review;

import jakarta.persistence.*;

@Entity
@Table(name = "reviews")
public class Review {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long Id;

    @Column(nullable = false)
    private long group_membership_id;

    private int rating;
    private String message;
    private String response;

    public void setGroupID(long group_membership_id) {
        this.group_membership_id = group_membership_id;
    }

    public long getGroupId() {
        return group_membership_id;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public int getRating() {
        return rating;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setResponse(String response) {
        this.response = response;
    }

    public String getResponse() {
        return response;
    }

}
