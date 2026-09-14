package com.example.student.controller;

import com.example.student.repository.StudentRepository;
import com.example.student.entity.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/students")
public class StudentViewController {

    @Autowired
    private StudentRepository studentRepository;

    // Xem danh sách
    @GetMapping
    public String showStudentList(@RequestParam(value = "keyword", required = false) String keyword, Model model) {
        List<Student> students = studentRepository.findAll();

        if (keyword != null && !keyword.trim().isEmpty()) {
            String value = keyword.trim().toLowerCase();
            students = students.stream()
                    .filter(student ->
                            contains(student.getStudentCode(), value)
                                    || contains(student.getFullName(), value)
                                    || contains(student.getEmail(), value)
                                    || contains(student.getPhone(), value)
                                    || contains(student.getClassName(), value))
                    .collect(Collectors.toList());
        }

        model.addAttribute("students", students);
        model.addAttribute("keyword", keyword);
        model.addAttribute("newStudent", new Student());
        return "students";
    }

    // Thêm sinh viên
    @PostMapping("/add")
    public String addStudent(@ModelAttribute("newStudent") Student student) {
        studentRepository.save(student);
        return "redirect:/students";
    }

    // Xóa sinh viên
    @GetMapping("/delete/{id}")
    public String deleteStudent(@PathVariable("id") UUID id) {
        studentRepository.deleteById(id);
        return "redirect:/students";
    }

    private boolean contains(String text, String keyword) {
        return text != null && text.toLowerCase().contains(keyword);
    }
}