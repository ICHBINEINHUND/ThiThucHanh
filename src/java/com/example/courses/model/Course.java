package com.example.courses.model;

import jakarta.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;

@Entity
@Table(name = "courses")
@NamedQueries({
    @NamedQuery(name = "Course.findAll", query = "SELECT c FROM Course c"),
    @NamedQuery(name = "Course.findById", query = "SELECT c FROM Course c WHERE c.id = :id"),
    @NamedQuery(name = "Course.searchByTitle", query = "SELECT c FROM Course c WHERE LOWER(c.title) LIKE LOWER(:keyword)")
})
public class Course implements Serializable {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    
    @Column(name = "code", nullable = false, length = 20)
    private String code;
    
    @Column(name = "title", nullable = false, length = 150)
    private String title;
    
    @Column(name = "credit", nullable = false)
    private int credit;
    
    @Column(name = "created_at")
    private Timestamp createdAt;
    
    public Course() {}
    
    public Course(String code, String title, int credit) {
        this.code = code;
        this.title = title;
        this.credit = credit;
    }
    
    // Getters and Setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    
    public String getCode() { return code; }
    public void setCode(String code) { this.code = code; }
    
    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }
    
    public int getCredit() { return credit; }
    public void setCredit(int credit) { this.credit = credit; }
    
    public Timestamp getCreatedAt() { return createdAt; }
    public void setCreatedAt(Timestamp createdAt) { this.createdAt = createdAt; }
}
