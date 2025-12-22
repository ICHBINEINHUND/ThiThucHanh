package com.example.demo.controller;

import com.example.demo.entity.Exam;
import com.example.demo.service.ExamService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/exams")
@RequiredArgsConstructor
public class ExamController {

    private final ExamService examService;

    @GetMapping
    public String listExams(Model model) {
        List<Exam> exams = examService.findAllExams();
        model.addAttribute("exams", exams);
        return "exam-list";
    }

    @GetMapping("/new")
    public String showNewExamForm(Model model) {
        model.addAttribute("exam", new Exam());
        return "exam-form";
    }

    @PostMapping("/new")
    public String createExam(@Valid @ModelAttribute("exam") Exam exam,
            BindingResult bindingResult,
            Model model,
            RedirectAttributes redirectAttributes) {

        // Check for validation errors
        if (bindingResult.hasErrors()) {
            return "exam-form";
        }

        // Check for unique exam name
        if (!examService.isExamNameUnique(exam.getName(), exam.getId())) {
            bindingResult.rejectValue("name", "error.exam", "An exam with this name already exists");
            return "exam-form";
        }

        // Save exam
        examService.saveExam(exam);
        redirectAttributes.addFlashAttribute("successMessage", "Exam created successfully!");

        return "redirect:/exams";
    }

    @GetMapping("/{id}")
    public String viewExam(@PathVariable Integer id, Model model, RedirectAttributes redirectAttributes) {
        return examService.findExamById(id)
                .map(exam -> {
                    model.addAttribute("exam", exam);
                    return "exam-detail";
                })
                .orElseGet(() -> {
                    redirectAttributes.addFlashAttribute("errorMessage", "Exam not found");
                    return "redirect:/exams";
                });
    }

    @PostMapping("/delete/{id}")
    public String deleteExam(@PathVariable Integer id, RedirectAttributes redirectAttributes) {
        try {
            examService.deleteExam(id);
            redirectAttributes.addFlashAttribute("successMessage", "Exam deleted successfully!");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("errorMessage", "Error deleting exam: " + e.getMessage());
        }
        return "redirect:/exams";
    }
}
