package com.studentmanagement.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "t_mark")
public class Mark {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "subject", length = 50)
    private String subject;

    @Column(name = "core_value")
    private Float scoreValue;

    @ManyToOne
    @JoinColumn(name = "student_id", referencedColumnName = "id")
    private Student student;

    public Mark() {
    }

    public Mark(String subject, Float scoreValue, Student student) {
        this.subject = subject;
        this.scoreValue = scoreValue;
        this.student = student;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public Float getScoreValue() {
        return scoreValue;
    }

    public void setScoreValue(Float scoreValue) {
        this.scoreValue = scoreValue;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }
}
