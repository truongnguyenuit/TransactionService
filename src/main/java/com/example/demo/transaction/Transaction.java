package com.example.demo.transaction;

import jakarta.persistence.*;

import java.util.Date;

@Entity
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Long bookId;

    @Column(nullable = false)
    private Long userId;

    @Column(nullable = false)
    private Boolean isReturned;

    @Column(nullable = false)
    private Date createdAt;

    @Column(nullable = false)
    private Date updatedAt;

    public Transaction(Long bookId, Long userId) {
        this.bookId = bookId;
        this.userId = userId;
        this.isReturned = false;
        this.createdAt = new Date();
        this.updatedAt = new Date();
    }

    public Transaction() {

    }

    @Override
    public String toString() {
        return "Transaction{" +
               "id=" + id +
               ", bookId=" + bookId +
               ", userId=" + userId +
               ", isReturned=" + isReturned +
               ", createdAt=" + createdAt +
               ", updatedAt=" + updatedAt +
               '}';
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getBookId() {
        return bookId;
    }

    public void setBookId(Long bookId) {
        this.bookId = bookId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Boolean getReturned() {
        return isReturned;
    }

    public void setReturned(Boolean returned) {
        isReturned = returned;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }
}
