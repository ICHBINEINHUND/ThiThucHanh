package com.example.demo.service;

import com.example.demo.entity.Exam;
import com.example.demo.repository.ExamRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ExamService {

    private final ExamRepository examRepository;

    public List<Exam> findAllExams() {
        return examRepository.findAll(Sort.by(Sort.Direction.DESC, "createdAt"));
    }

    public Optional<Exam> findExamById(Integer id) {
        return examRepository.findById(id);
    }

    @Transactional
    public Exam saveExam(Exam exam) {
        return examRepository.save(exam);
    }

    @Transactional
    public void deleteExam(Integer id) {
        examRepository.deleteById(id);
    }

    public boolean isExamNameUnique(String name, Integer excludeId) {
        if (excludeId == null) {
            return !examRepository.existsByName(name);
        } else {
            return !examRepository.existsByNameAndIdNot(name, excludeId);
        }
    }
}
