package com.tcms.addmissioncourseservice.services;

import java.util.List;

import com.tcms.addmissioncourseservice.payload.ModuleDto;

public interface ModuleService {
	ModuleDto createModule(Long courseId, ModuleDto moduleDto);
	
    ModuleDto getModule(Long courseId, Long moduleId);
    
    List<ModuleDto> getAllModules(Long courseId);
    
    ModuleDto updateModule(Long courseId, Long moduleId, ModuleDto moduleDto);
    
    void deleteModule(Long courseId, Long moduleId);

}

