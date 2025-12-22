package com.example.demo.repository;

import com.example.demo.entity.Exam;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ExamRepository extends JpaRepository<Exam, Integer> {

    boolean existsByName(String name);

    Optional<Exam> findByNameIgnoreCase(String name);

    boolean existsByNameAndIdNot(String name, Integer id);
}
