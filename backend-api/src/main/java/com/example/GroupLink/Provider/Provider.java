package com.example.GroupLink.Provider;

import java.util.List;

import com.example.GroupLink.Group.Group;
import com.example.GroupLink.Review.Review;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import jakarta.persistence.*;

@Entity
@Table(name = "providers")
public class Provider {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false)
    private String email;

    @Column(nullable = false)
    private String username;

    @Column(nullable = false)
    private String password;

    // one provider to many groups
    @OneToMany(mappedBy = "provider", cascade = CascadeType.ALL)
    @JsonIgnoreProperties("provider") // this will make sure to ignore the provider when serializing so there won't be
                                      // infinite loop
    private List<Group> groups;

    @OneToMany(mappedBy = "provider", cascade = CascadeType.ALL)
    @JsonIgnoreProperties("provider")
    private List<Review> reviews;

    private String profilePicturePath;
    private double averageRating;
    private int reviewCount;

    public Provider() {
    }

    public Provider(long id, String email, String username, String password, String profilePicturePath, Double averageRating,
            int reviewCount, List<Group> groups, List<Review> reviews) {
        this.id = id;
        this.email = email;
        this.username = username;
        this.password = password;
        this.profilePicturePath = profilePicturePath;
        this.averageRating = averageRating;
        this.reviewCount = reviewCount;
        this.groups = groups;
        this.reviews = reviews;
    }

    public Provider(String email, String username, String password, String profilePicturePath, Double averageRating,
            int reviewCount, List<Group> groups, List<Review> reviews) {

        this.email = email;
        this.username = username;
        this.password = password;
        this.profilePicturePath = profilePicturePath;
        this.averageRating = averageRating;
        this.reviewCount = reviewCount;
        this.groups = groups;
        this.reviews = reviews;
    }

    public Long getId() {
        return id;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEmail() {
        return email;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUsername() {
        return username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPassword() {
        return password;
    }

    public List<Group> getGroups() {
        return groups;
    }

    public void setProfilePicturePath(String profilePicturePath) {
        this.profilePicturePath = profilePicturePath;
    }

    public String getProfilePicturePath() {
        return profilePicturePath;
    }

    public Double getAverageRating() {
        return averageRating;
    }

    public void setAverageRating(Double averageRating) {
        this.averageRating = averageRating;
    }

    public int getReviewCount() {
        return reviewCount;
    }

    public void setReviewCount(int reviewCount) {
        this.reviewCount = reviewCount;
    }

    public List<Review> getReviews() {
        return reviews;
    }

}
