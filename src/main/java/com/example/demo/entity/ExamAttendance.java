package com.example.demo.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "exam_attendance")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ExamAttendance {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "exam_id")
    private Integer examId;

    @Column(name = "scheduler_id")
    private Integer schedulerId;

    @Column(name = "student_roll_no", length = 20)
    private String studentRollNo;
}
