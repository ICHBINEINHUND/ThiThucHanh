package com.example.demo.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "students")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Student {

    @Id
    @Column(name = "roll_no", length = 20)
    private String rollNo;

    @NotBlank(message = "Full name is required")
    @Column(name = "full_name", length = 50)
    private String fullName;

    @Column(name = "class_name", length = 20)
    private String className;
}
