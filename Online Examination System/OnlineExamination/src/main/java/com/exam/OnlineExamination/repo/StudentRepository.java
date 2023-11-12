package com.exam.OnlineExamination.repo;

import com.exam.OnlineExamination.Model.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;
public interface StudentRepository extends JpaRepository<Student, Long> {

    Optional<Student> findByUsername(String username);
    Student findByRollNumber(String rollNumber);
}
