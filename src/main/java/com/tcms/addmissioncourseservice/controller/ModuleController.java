package com.tcms.addmissioncourseservice.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tcms.addmissioncourseservice.payload.ModuleDto;
import com.tcms.addmissioncourseservice.services.ModuleService;

@RestController
@RequestMapping("/api/courses/{courseId}/modules")
@CrossOrigin(origins = "*")
public class ModuleController {

	    @Autowired
	    private ModuleService moduleService;
	    @PostMapping("/")
	    public ResponseEntity<ModuleDto> createModule(@PathVariable Long courseId, @RequestBody ModuleDto moduleDto) {
	        return ResponseEntity.ok(moduleService.createModule(courseId, moduleDto));
	    }

	    @GetMapping("/{moduleId}")
	    public ResponseEntity<ModuleDto> getModule(@PathVariable Long courseId, @PathVariable Long moduleId) {
	        return ResponseEntity.ok(moduleService.getModule(courseId, moduleId));
	    }

	    @GetMapping("/")
	    public ResponseEntity<List<ModuleDto>> getAllModules(@PathVariable Long courseId) {
	        return ResponseEntity.ok(moduleService.getAllModules(courseId));
	    }

	    @PutMapping("/{moduleId}")
	    public ResponseEntity<ModuleDto> updateModule(@PathVariable Long courseId, @PathVariable Long moduleId, @RequestBody ModuleDto moduleDto) {
	        return ResponseEntity.ok(moduleService.updateModule(courseId, moduleId, moduleDto));
	    }

	    @DeleteMapping("/{moduleId}")
	    public ResponseEntity<Void> deleteModule(@PathVariable Long courseId, @PathVariable Long moduleId) {
	        moduleService.deleteModule(courseId, moduleId);
	        return ResponseEntity.noContent().build();
	    }
}
