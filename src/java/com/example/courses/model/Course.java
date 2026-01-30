package com.example.courses.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import java.io.Serializable;
import java.util.Date;

/**
 * JPA Entity for Course
 * Maps to courses table in jakarta_ws_exam database
 */
@Entity
@Table(name = "courses")
public class Course implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "code", length = 20, nullable = false)
    @NotEmpty(message = "Code must not be empty")
    private String code;

    @Column(name = "title", length = 150, nullable = false)
    @NotEmpty(message = "Title must not be empty")
    private String title;

    @Column(name = "credit", nullable = false)
    @Min(value = 1, message = "Credit must be greater than 0")
    private int credit;

    @Column(name = "created_at", insertable = false, updatable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdAt;

    public Course() {
    }

    public Course(String code, String title, int credit) {
        this.code = code;
        this.title = title;
        this.credit = credit;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getCredit() {
        return credit;
    }

    public void setCredit(int credit) {
        this.credit = credit;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    @Override
    public String toString() {
        return "Course{" +
                "id=" + id +
                ", code='" + code + '\'' +
                ", title='" + title + '\'' +
                ", credit=" + credit +
                ", createdAt=" + createdAt +
                '}';
    }
}
