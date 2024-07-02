package com.tcms.addmissioncourseservice.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tcms.addmissioncourseservice.entities.Course;

public interface CourseRepository extends JpaRepository<Course,Long>{

}
