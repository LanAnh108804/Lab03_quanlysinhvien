package com.example.demo;

import java.util.ArrayList;
import java.util.List;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
@RequestMapping("/api")
public class DemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }

    // === BÀI 1 ===
    @GetMapping("/hello")
    public String hello() {
        return "Hello Spring Boot API";
    }

    // === BÀI 2 ===
    @GetMapping("/student/{id}")
    public String getStudent(@PathVariable int id) {
        return "Sinh viên có mã: " + id;
    }

    // === BÀI 3.1 ===
    @GetMapping("/greet")
    public String greet(@RequestParam String name) {
        return "Xin chào " + name;
    }

    // === BÀI 3.2 ===
    @GetMapping("/searchStudent")
    public String searchStudent(
            @RequestParam String name,
            @RequestParam(defaultValue = "1") int age) {
        return "Tên=" + name + ", tuổi=" + age;
    }

    // === BÀI 4A ===
    @GetMapping("/student")
    public Student getStudentObject() {
        return new Student(1, "Nguyễn Văn A", 20);
    }

    // === BÀI 4B ===
    @GetMapping("/studentall")
    public List<Student> getStudents() {
        List<Student> list = new ArrayList<>();
        list.add(new Student(1, "A", 20));
        list.add(new Student(2, "B", 21));
        return list;
    }

    // === BÀI 5: RequestHeader ===
    @GetMapping("/getstudent")
    public String getStudentsHeader(
            @RequestHeader(value = "Authorization", defaultValue = "Bearer abc123") String authorization) {
        return "Authorization = " + authorization;
    }
}