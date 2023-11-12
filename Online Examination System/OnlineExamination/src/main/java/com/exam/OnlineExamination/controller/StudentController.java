package com.exam.OnlineExamination.controller;

import com.exam.OnlineExamination.Model.Student;
import com.exam.OnlineExamination.repo.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
public class StudentController {

    @Autowired
    private StudentRepository studentRepository;

    @GetMapping("/login")
    public String showLoginForm(Model model) {
        model.addAttribute("student", new Student());

        if (model.containsAttribute("error")) {
            model.addAttribute("error", "Invalid credentials");
        }

        return "login";
    }

    @PostMapping("/login")
    public String processLogin(Student student, Model model) {
        Optional<Student> existingStudent = studentRepository.findByUsername(student.getUsername());

        if (existingStudent.isPresent() && existingStudent.get().getPassword().equals(student.getPassword())) {
            // Successful login
            return "redirect:/success";
        } else {
            model.addAttribute("error", true);
            return "redirect:/login?error";
        }
    }

    @GetMapping("/signup")
    public String showSignUpForm(Model model) {
        model.addAttribute("student", new Student());
        return "signup";
    }

    @PostMapping("/signup")
    public String processSignUp(Student student) {
        if (studentRepository.findByUsername(student.getUsername()).isPresent()) {

            return "redirect:/signup?error=usernameExists";
        }


        studentRepository.save(student);


        return "redirect:/success";
    }

    @GetMapping("/success")
    public String showSuccessPage(Model model) {
        model.addAttribute("student",new Student());
        return "success";
    }
    @GetMapping("/scorecard")
    public String showScorecard(Model model) {

        model.addAttribute("students", studentRepository.findAll());

        model.addAttribute("newStudent", new Student());
        return "scorecard";
    }

    @PostMapping("/addStudent")
    public String addStudent(Student newStudent) {

        studentRepository.save(newStudent);

        return "redirect:/scorecard";
    }

    @GetMapping("/student-score")
    public String showStudentScore() {
        return "student-score";
    }

    @PostMapping("/check-score")
    public String checkScore(@RequestParam String rollNumber, Model model) {
        // Assuming you have a method to retrieve a student by rollNumber
        Student student = studentRepository.findByRollNumber(rollNumber);

        if (student == null) {
            // If the student is not found, redirect with invalidSeat parameter
            return "invaildseatnumber";
        }

        // Add the student to the model for displaying the score
        model.addAttribute("student", student);

        // Return the view to display the student's score
        return "student-score";
    }
    }

