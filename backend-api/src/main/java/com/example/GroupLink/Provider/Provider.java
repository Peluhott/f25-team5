package com.example.GroupLink.Provider;

import java.util.List;

import com.example.GroupLink.Group.Group;
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
    private String profilePicturePath;

    public Provider() {
    }

    public Provider(long providerID, String email, String username, String password, String profilePicturePath) {
        this.id = providerID;
        this.email = email;
        this.username = username;
        this.password = password;
        this.profilePicturePath = profilePicturePath;
    }

    public Provider(String email, String username, String password, String profilePicturePath) {

        this.email = email;
        this.username = username;
        this.password = password;
        this.profilePicturePath = profilePicturePath;
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
}
