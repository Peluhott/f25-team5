package com.example.GroupLink.Customer;

import java.util.List;

import com.example.GroupLink.GroupMembership.GroupMembership;
import com.example.GroupLink.Review.Review;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "customers")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String username;

    @Column(nullable = false)
    private String password;

    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnoreProperties("customer")
    private List<GroupMembership> groupMemberships;

    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnoreProperties("customer")
    private List<Review> reviews;

    private String profilePicturePath;

    public Customer() {
    }

    public Customer(String email, String username, String password, String profilePicturePath) {
        this.email = email;
        this.username = username;
        this.password = password;
        this.profilePicturePath = profilePicturePath;
    }

    public Customer(String email, String username, String password, List<GroupMembership> groupMemberships, List<Review> reviews, String profilePicturePath) {
        this.email = email;
        this.username = username;
        this.password = password;
        this.groupMemberships = groupMemberships;
        this.reviews = reviews;
        this.profilePicturePath = profilePicturePath;
    }

    public Customer(long id, String email, String username, String password, List<GroupMembership> groupMemberships, List<Review> reviews, String profilePicturePath) {
        this.id = id;
        this.email = email;
        this.username = username;
        this.password = password;
        this.groupMemberships = groupMemberships;
        this.reviews = reviews;
        this.profilePicturePath = profilePicturePath;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<GroupMembership> getGroupMemberships() {
        return groupMemberships;
    }

    public List<Review> getReviews() {
        return reviews;
    }

    public String getProfilePicturePath() {
        return profilePicturePath;
    }

    public void setProfilePicturePath(String profilePicturePath) {
        this.profilePicturePath = profilePicturePath;
    }
}
