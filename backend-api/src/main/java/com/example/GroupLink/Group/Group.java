package com.example.GroupLink.Group;

import java.util.ArrayList;
import java.util.List;

import com.example.GroupLink.GroupMembership.GroupMembership;
import com.example.GroupLink.Provider.Provider;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.*;

@Entity
@Table(name = "groups")

public class Group {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "provider_id", nullable = false)
    @JsonIgnoreProperties("groups")
    private Provider provider;

    /*
     * one group to many memberships
     * mappedby group means the foreign key is the group object in memberships
     * 
     */
    @OneToMany(orphanRemoval = true, mappedBy = "group", cascade = CascadeType.ALL)
    @JsonIgnoreProperties("group") // put the properties that it should ignore from membership here
    private List<GroupMembership> groupMemberships = new ArrayList<>(); // change to it match your entity if you need it
                                                                        // to

    @Column(nullable = false)
    private long creatorId;

    @Column(nullable = false)
    private String name;

    private String location;
    private String type;
    private int maxMem;
    private boolean active;
    private String content;

    public List<GroupMembership> getMemberships() {
        return groupMemberships;
    }

    public void addMembership(GroupMembership member) {
        if (member == null) {
            return;
        }
        groupMemberships.add(member); // this adds a member to the list on group side
        member.setGroup(this); // this sets the group on the membership side make sure you have setGroup on
                               // Membership Entity
    }

    public void deleteMembership(GroupMembership member) {
        groupMemberships.remove(member); // this removes member from list on group side
        member.setGroup(null); // this deletes the group from the member side, triggers orphan removal
    }

    public Long getGroupID() {
        return this.id;
    }

    public void setCreatorId(Long creatorId) {
        this.creatorId = creatorId;
    }

    public long getCreatorId() {
        return creatorId;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getLocation() {
        return location;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }

    public void setMaxMem(int maxMem) {
        this.maxMem = maxMem;
    }

    public int getMaxMem() {
        return maxMem;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public boolean getActive() {
        return active;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getContent() {
        return content;
    }

    public Provider getProvider() {
        return provider;
    }
}
