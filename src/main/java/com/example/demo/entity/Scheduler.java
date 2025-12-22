package com.example.demo.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "schedulers")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Scheduler {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "time_slot", length = 20)
    private String timeSlot;

    @Column(name = "location", length = 30)
    private String location;

    @Column(name = "exam_sitter", length = 50)
    private String examSitter;

    @Column(name = "start_at")
    private LocalDateTime startAt;

    @Column(name = "end_at")
    private LocalDateTime endAt;
}
