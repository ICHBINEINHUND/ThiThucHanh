package com.example.courses.model;
import jakarta.persistence.*;
import java.time.LocalDateTime;
@Entity
@Table(name = "courses")
public class Course {
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
  private LocalDateTime createdAt;
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
  public LocalDateTime getCreatedAt() {
    return createdAt;
  }
  public void setCreatedAt(LocalDateTime createdAt) {
    this.createdAt = createdAt;
  }
}
