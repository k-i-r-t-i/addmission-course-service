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
import com.tcms.addmissioncourseservice.payload.ModuleDto;
import com.tcms.addmissioncourseservice.services.ModuleService;

@Service
public class ModuleServiceImpl implements ModuleService {
	@Autowired
    private CourseRepository courseRepository;

    @Autowired
    private ModuleRepository moduleRepository;

    @Autowired
    private ModelMapper modelMapper;

	@Override
	public ModuleDto createModule(Long courseId, ModuleDto moduleDto) {
		Course course = courseRepository.findById(courseId).orElseThrow(() -> new ResourceNotFoundException("Course", "id", courseId));
        Module module = modelMapper.map(moduleDto, Module.class);
        module = moduleRepository.save(module);
        course.getModules().add(module);
        courseRepository.save(course);
        return modelMapper.map(module, ModuleDto.class);
	}

	@Override
	public ModuleDto getModule(Long courseId, Long moduleId) {
		Course course = courseRepository.findById(courseId).orElseThrow(() -> new ResourceNotFoundException("Course", "id", courseId));
        Module module = course.getModules().stream().filter(m -> m.getModuleId().equals(moduleId)).findFirst().orElseThrow(() -> new ResourceNotFoundException("Module", "id", moduleId));
        return modelMapper.map(module, ModuleDto.class);
	}

	@Override
	public List<ModuleDto> getAllModules(Long courseId) {
		Course course = courseRepository.findById(courseId).orElseThrow(() -> new ResourceNotFoundException("Course", "id", courseId));
        return course.getModules().stream().map(module -> modelMapper.map(module, ModuleDto.class)).collect(Collectors.toList());
	}

	@Override
	public ModuleDto updateModule(Long courseId, Long moduleId, ModuleDto moduleDto) {
		Course course = courseRepository.findById(courseId).orElseThrow(() -> new ResourceNotFoundException("Course", "id", courseId));
        Module module = course.getModules().stream().filter(m -> m.getModuleId().equals(moduleId)).findFirst().orElseThrow(() -> new ResourceNotFoundException("Module", "id", moduleId));
        module.setMName(moduleDto.getMName());
        module.setMDiscription(moduleDto.getMDiscription());
        module.setMStatus(moduleDto.getMStatus());
        module.setMShortName(moduleDto.getMShortName());
        module.setMFee(moduleDto.getMFee());
        module = moduleRepository.save(module);
        return modelMapper.map(module, ModuleDto.class);
	}

	@Override
	public void deleteModule(Long courseId, Long moduleId) {
		Course course = courseRepository.findById(courseId).orElseThrow(() -> new ResourceNotFoundException("Course", "id", courseId));
        Module module = course.getModules().stream().filter(m -> m.getModuleId().equals(moduleId)).findFirst().orElseThrow(() -> new ResourceNotFoundException("Module", "id", moduleId));
        course.getModules().remove(module);
        moduleRepository.delete(module);
        courseRepository.save(course);
		
	}
	

}
