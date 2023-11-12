package com.exam.OnlineExamination.repo;

import com.exam.OnlineExamination.Model.Student;
import com.exam.OnlineExamination.Model.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TeacherRepository extends JpaRepository<Teacher, Long> {

    Optional<Teacher> findByUsername(String username);

}