package com.tcms.addmissioncourseservice.services;

import java.util.List;

import com.tcms.addmissioncourseservice.payload.CourseDto;
import com.tcms.addmissioncourseservice.payload.ModuleDto;


 public interface CourseService {
	    //create
		CourseDto createCourse(CourseDto courseDto);
		
	    //update
		CourseDto updateCourse(CourseDto courseDto, Long courseId);
		
	    //delete
		 void deleteCourse(Long courseId);
		
	    //get
		 CourseDto getCourse(Long courseId);
		
	    //get all
		List<CourseDto> getCourses();
		
		List<ModuleDto> getModulesByCourseId(Long courseId);

}

