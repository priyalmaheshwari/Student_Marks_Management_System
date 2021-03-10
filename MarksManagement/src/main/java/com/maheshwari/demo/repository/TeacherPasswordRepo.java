package com.maheshwari.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.maheshwari.demo.model.TeacherPassword;

public interface TeacherPasswordRepo extends JpaRepository<TeacherPassword, Integer> {

}
