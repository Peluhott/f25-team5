package com.example.GroupLink.Review;

import com.example.GroupLink.GroupMembership.GroupMembership;

import jakarta.persistence.*;

@Entity
@Table(name = "reviews")
public class Review {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @OneToOne(optional = false)
    @JoinColumn
    private GroupMembership membership;

    private int rating;
    private String message;
    private String response;

    public GroupMembership getGroup() {
        return membership;
    }

    public void setMembership(GroupMembership membership) {
        this.membership = membership;
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
