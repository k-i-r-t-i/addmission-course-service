package com.tcms.addmissioncourseservice.services.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tcms.addmissioncourseservice.dao.AddmissionRepository;
import com.tcms.addmissioncourseservice.dao.CourseRepository;
import com.tcms.addmissioncourseservice.dao.ModuleRepository;
import com.tcms.addmissioncourseservice.entities.Addmission;
import com.tcms.addmissioncourseservice.entities.Course;
import com.tcms.addmissioncourseservice.entities.Module;
import com.tcms.addmissioncourseservice.exceptions.ResourceNotFoundException;
import com.tcms.addmissioncourseservice.payload.AddmissionDto;
import com.tcms.addmissioncourseservice.payload.CourseDto;
import com.tcms.addmissioncourseservice.services.AddmissionService;

@Service
public class AddmissionServiceImpl implements AddmissionService {
	@Autowired
    private AddmissionRepository addmissionRepository;

    @Autowired
    private CourseRepository courseRepository;

    @Autowired
    private ModelMapper modelMapper;

	@Override
	public AddmissionDto createAddmission(AddmissionDto addmissionDto, List<Long> courseIds) {
		Addmission addmission = modelMapper.map(addmissionDto, Addmission.class);
        List<Course> courses = courseRepository.findAllById(courseIds);
        addmission.setStatus("Pending");
        if (courses.size() != courseIds.size()) {
            throw new ResourceNotFoundException("Some courses were not found");
        }

        addmission.setCourses(courses);
        addmission = addmissionRepository.save(addmission);

        return modelMapper.map(addmission, AddmissionDto.class);
	}

	@Override
	public AddmissionDto getAddmission(Long addmissionId) {
		Addmission addmission = addmissionRepository.findById(addmissionId)
	            .orElseThrow(() -> new ResourceNotFoundException("Addmission", "id", addmissionId));

	        return modelMapper.map(addmission, AddmissionDto.class);
	}

	@Override
	public List<AddmissionDto> getAllAddmissions() {
		List<Addmission> addmissions = addmissionRepository.findAll();
        return addmissions.stream()
            .map(addmission -> modelMapper.map(addmission, AddmissionDto.class))
            .collect(Collectors.toList());
	}
	@Transactional
	@Override
	public AddmissionDto updateAddmission(Long addmissionId, AddmissionDto addmissionDto, List<Long> courseIds) {
		Addmission addmission = addmissionRepository.findById(addmissionId)
	            .orElseThrow(() -> new ResourceNotFoundException("Addmission", "id", addmissionId));

	        modelMapper.map(addmissionDto, addmission);

	        List<Course> courses = courseRepository.findAllById(courseIds);

	        if (courses.size() != courseIds.size()) {
	            throw new ResourceNotFoundException("Some courses were not found");
	        }

	        addmission.setCourses(courses);
	        addmission = addmissionRepository.save(addmission);

	        return modelMapper.map(addmission, AddmissionDto.class);
	}

	@Override
	public void deleteAddmission(Long addmissionId) {
		Addmission addmission = addmissionRepository.findById(addmissionId)
	            .orElseThrow(() -> new ResourceNotFoundException("Addmission", "id", addmissionId));

	        addmissionRepository.delete(addmission);
		
	}

	@Override
	public List<CourseDto> findCoursesByAddmissionId(Long addmissionId) {
		Addmission addmission = addmissionRepository.findById(addmissionId)
	            .orElseThrow(() -> new ResourceNotFoundException("Addmission", "id", addmissionId));

	        List<Course> courses = addmission.getCourses();
	        return courses.stream()
	            .map(course -> modelMapper.map(course, CourseDto.class))
	            .collect(Collectors.toList());
	}

	@Override
	public AddmissionDto updateStatus(AddmissionDto addmissionDto, Long addmissionId) {
		Addmission addmission = addmissionRepository.findById(addmissionId)
	            .orElseThrow(() -> new ResourceNotFoundException("Addmission", "id", addmissionId));
		addmission.setStatus(addmissionDto.getStatus());

		Addmission updatedAddmission = this.addmissionRepository.save(addmission);
        return this.modelMapper.map(updatedAddmission, AddmissionDto.class);
	}

	
	
	
}
