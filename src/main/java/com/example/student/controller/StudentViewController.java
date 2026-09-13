package com.example.student.controller;

import com.example.student.repository.StudentRepository;
import com.example.student.entity.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@Controller
@RequestMapping("/students")
public class StudentViewController {

    @Autowired
    private StudentRepository studentRepository;

    // Xem danh sách
    @GetMapping
    public String showStudentList(Model model) {
        model.addAttribute("students", studentRepository.findAll());
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
}