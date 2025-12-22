package com.example.demo.repository;

import com.example.demo.entity.ExamAttendance;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ExamAttendanceRepository extends JpaRepository<ExamAttendance, Integer> {
}
