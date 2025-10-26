package com.example.GroupLink.GroupMembership;

import com.example.GroupLink.Customer.Customer;
import com.example.GroupLink.Group.Group;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "group_memberships")
public class GroupMembership {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false)
    private String status;

    @Column(nullable = true)
    private String message;

    @ManyToOne
    @JoinColumn(name = "customer_id", nullable = false)
    @JsonIgnoreProperties("groupMemberships")
    private Customer customer;
    
    @ManyToOne
    @JoinColumn(name = "group_id", nullable = false)
    @JsonIgnoreProperties("groupMemberships")
    private Group group;

    public GroupMembership() {
    }

    public GroupMembership(String status, String message) {
        this.status = status;
        this.message = message;
    }

    public GroupMembership(long id, String status, String message) {
        this.id = id;
        this.status = status;
        this.message = message;
    }

    public long getId() {
        return id;
    }
    public void setId(long id) {
        this.id = id;
    }

    public String getStatus() {
        return status;
    }
    public void setStatus(String status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }
    public void setMessage(String message) {
        this.message = message;
    }

    public Customer getCustomer() {
        return customer;
    }
    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Group getGroup() {
        return group;
    }

    public void setGroup(Group group) {
        this.group = group;
    }

}
