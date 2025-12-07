package com.example.GroupLink.Review;

import com.example.GroupLink.Customer.Customer;
import com.example.GroupLink.GroupMembership.GroupMembership;
import com.example.GroupLink.Provider.Provider;

import jakarta.persistence.*;

@Entity
@Table(name = "reviews")
public class Review {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "providerid", nullable = false)
    private Provider provider;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "customerid", nullable = false)
    private Customer customer;

    private int rating;
    private String message;
    private String response;

    public Review() {
    }

    public Review(Provider provider, int rating, String message, String response, Customer customer) {
        this.provider = provider;
        this.rating = rating;
        this.message = message;
        this.response = response;
        this.customer = customer;
    }

    public Review(long id, Provider provider, int rating, String message, String response, Customer customer) {
        this.id = id;
        this.provider = provider;
        this.rating = rating;
        this.message = message;
        this.response = response;
        this.customer = customer;
    }

    public Provider getProvider() {
        return provider;
    }

    public Long getId() {
        return id;
    }

    public void setProvider(Provider provider) {
        this.provider = provider;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
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
