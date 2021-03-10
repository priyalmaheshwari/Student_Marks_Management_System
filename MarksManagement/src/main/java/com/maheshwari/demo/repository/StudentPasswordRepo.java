package com.maheshwari.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.maheshwari.demo.model.StudentPassword;

public interface StudentPasswordRepo extends JpaRepository<StudentPassword, Integer>{

}
