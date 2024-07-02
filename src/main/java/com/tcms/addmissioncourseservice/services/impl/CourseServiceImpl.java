package com.tcms.addmissioncourseservice.services.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tcms.addmissioncourseservice.dao.CourseRepository;
import com.tcms.addmissioncourseservice.dao.ModuleRepository;
import com.tcms.addmissioncourseservice.entities.Course;
import com.tcms.addmissioncourseservice.entities.Module;
import com.tcms.addmissioncourseservice.exceptions.ResourceNotFoundException;
import com.tcms.addmissioncourseservice.payload.CourseDto;
import com.tcms.addmissioncourseservice.payload.ModuleDto;
import com.tcms.addmissioncourseservice.services.CourseService;

@Service
public class CourseServiceImpl implements CourseService {
	
	@Autowired
    private CourseRepository courseRepository;

    @Autowired
    private ModuleRepository moduleRepository;

    @Autowired
    private ModelMapper modelMapper;


	@Override
	public CourseDto createCourse(CourseDto courseDto) {
		Course course = this.modelMapper.map(courseDto, Course.class);
        Course savedCourse = this.courseRepository.save(course);
        return this.modelMapper.map(savedCourse, CourseDto.class);
	}

	@Override
	public CourseDto updateCourse(CourseDto courseDto, Long courseId) {
		Course course = this.courseRepository.findById(courseId)
                .orElseThrow(() -> new ResourceNotFoundException("Course", "Id", courseId));
        course.setImageName(courseDto.getImageName());
        course.setCName(courseDto.getCName());
        course.setCDiscription(courseDto.getCDiscription());
        course.setCStatus(courseDto.getCStatus());
        course.setCShortName(courseDto.getCShortName());
        course.setCFee(courseDto.getCFee());

        Course updatedCourse = this.courseRepository.save(course);
        return this.modelMapper.map(updatedCourse, CourseDto.class);
	}

	@Override
	public void deleteCourse(Long courseId) {
		Course course = this.courseRepository.findById(courseId)
                .orElseThrow(() -> new ResourceNotFoundException("Course", "Id", courseId));
        this.courseRepository.delete(course);
		
	}

	@Override
	public CourseDto getCourse(Long courseId) {
		Course course = this.courseRepository.findById(courseId)
                .orElseThrow(() -> new ResourceNotFoundException("Course", "Id", courseId));
        return this.modelMapper.map(course, CourseDto.class);
	}

	@Override
	public List<CourseDto> getCourses() {
		List<Course> courses = this.courseRepository.findAll();
        return courses.stream()
                .map(course -> this.modelMapper.map(course, CourseDto.class))
                .collect(Collectors.toList());
	}

	@Override
	public List<ModuleDto> getModulesByCourseId(Long courseId) {
		Course course = this.courseRepository.findById(courseId)
                .orElseThrow(() -> new ResourceNotFoundException("Course", "Id", courseId));
        
        List<Module> modules = course.getModules();
        return modules.stream()
                .map(module -> this.modelMapper.map(module, ModuleDto.class))
                .collect(Collectors.toList());
	}

}
