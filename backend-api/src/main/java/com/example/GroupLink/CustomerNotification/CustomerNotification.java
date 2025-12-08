package com.example.GroupLink.CustomerNotification;

import java.time.LocalDateTime;

import com.example.GroupLink.Customer.Customer;
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
@Table(name = "customer_notifications")
public class CustomerNotification {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String type;

    @Column(nullable = false)
    private String message;

    @Column(nullable = false)
    private boolean read;

    @Column(nullable = false)
    private String timestamp;

    @ManyToOne
    @JoinColumn(name = "customer_id", nullable = false)
    @JsonIgnoreProperties("notifications")
    private Customer customer;

    public CustomerNotification() {
    }

    public CustomerNotification(Customer customer, String type, String message, String timestamp) {
        this.customer = customer;
        this.type = type;
        this.message = message;
        this.read = false;
        this.timestamp = timestamp;
    }

    public CustomerNotification(String type, String message, boolean read, String timestamp) {
        this.type = type;
        this.message = message;
        this.read = read;
        this.timestamp = timestamp;
    }

    public CustomerNotification(Long id, String type, String message, boolean read, String timestamp) {
        this.id = id;
        this.type = type;
        this.message = message;
        this.read = read;
        this.timestamp = timestamp;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public boolean isRead() {
        return read;
    }

    public void setRead(boolean read) {
        this.read = read;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

}
