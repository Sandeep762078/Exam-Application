package com.exam.OnlineExamination.controller;
import com.exam.OnlineExamination.Model.Student;
import com.exam.OnlineExamination.Model.Teacher;
import com.exam.OnlineExamination.repo.TeacherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Optional;

@Controller
public class TeacherController {

    @Autowired
    private TeacherRepository teacherRepository;

    @GetMapping("/teacher-login")
    public String showTeacherLoginForm(Model model) {
        model.addAttribute("teacher", new Teacher());

        if (model.containsAttribute("error")) {
            model.addAttribute("error", "Invalid credentials");
        }
        return "teacher-login";
    }



    @GetMapping("/teacher-signup")
    public String showTeacherSignUpForm(Model model) {
        model.addAttribute("teacher", new Teacher());
        return "teacher-signup";
    }

    @PostMapping("/teacher-signup")
    public String processTeacherSignUp(Teacher teacher) {
        if (teacherRepository.findByUsername(teacher.getUsername()).isPresent()) {
            return "redirect:/teacher-signup?error=usernameExists";
        }

        teacherRepository.save(teacher);
        return "redirect:/teacher-success";
    }

    @GetMapping("/teacher-success")
    public String showTeacherSuccessPage() {
        return "teacher-success";
    }
    @GetMapping("/teacher-paper-checking")
    public String showTeacherPaperCheckingPage() {
        return "teacher-paper-checking";
    }
    @PostMapping("/teacher-login")
    public String teacherLogin(@RequestParam String username, @RequestParam String password, Model model,Teacher teacher) {
        Optional<Teacher> existingTeacher = teacherRepository.findByUsername(teacher.getUsername());

        if (existingTeacher.isPresent() && existingTeacher.get().getPassword().equals(teacher.getPassword())) {
            model.addAttribute("teacher", existingTeacher.get());
            return "redirect:/teacher-success";
        } else {
            model.addAttribute("error", true);
            return "redirect:/teacher-login?error";
        }
    }
    @GetMapping("/exam")
    public String showExamPage() {
        return "exam";
    }



}
