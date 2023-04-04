package com.gym.kerabox.entity;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;

import java.time.LocalDate;

@MappedSuperclass
public class BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private LocalDate createdBy;
    private LocalDate updatedBy;
    private String loggedInBy;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public LocalDate getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(LocalDate createdBy) {
        this.createdBy = createdBy;
    }

    public LocalDate getUpdatedBy() {
        return updatedBy;
    }

    public void setUpdatedBy(LocalDate updatedBy) {
        this.updatedBy = updatedBy;
    }

    public String getLoggedInBy() {
        return loggedInBy;
    }

    public void setLoggedInBy(String loggedInBy) {
        this.loggedInBy = loggedInBy;
    }
}
