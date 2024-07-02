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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.tcms.addmissioncourseservice.payload.AddmissionDto;
import com.tcms.addmissioncourseservice.payload.CourseDto;
import com.tcms.addmissioncourseservice.services.AddmissionService;

@RestController
@RequestMapping("/api/addmissions")
@CrossOrigin(origins = "*")
public class AddmissionController {

    @Autowired
    private AddmissionService addmissionService;

    @PostMapping
    public ResponseEntity<AddmissionDto> createAddmission(
            @RequestBody AddmissionDto addmissionDto,
            @RequestParam List<Long> courseIds) {
        AddmissionDto createdAddmission = addmissionService.createAddmission(addmissionDto, courseIds);
        return ResponseEntity.ok(createdAddmission);
    }

    @GetMapping("/{id}")
    public ResponseEntity<AddmissionDto> getAddmission(@PathVariable Long id) {
        AddmissionDto addmissionDto = addmissionService.getAddmission(id);
        return ResponseEntity.ok(addmissionDto);
    }

    @GetMapping
    public ResponseEntity<List<AddmissionDto>> getAllAddmissions() {
        List<AddmissionDto> addmissions = addmissionService.getAllAddmissions();
        return ResponseEntity.ok(addmissions);
    }

    @PutMapping("/{id}")
    public ResponseEntity<AddmissionDto> updateAddmission(
            @PathVariable Long id,
            @RequestBody AddmissionDto addmissionDto,
            @RequestParam List<Long> courseIds) {
        AddmissionDto updatedAddmission = addmissionService.updateAddmission(id, addmissionDto, courseIds);
        return ResponseEntity.ok(updatedAddmission);
    }
    @PutMapping("/{addmissionId}/status")
    public ResponseEntity<AddmissionDto> updateStatus(@PathVariable Long addmissionId, @RequestBody AddmissionDto addmissionDto) {
    	AddmissionDto updatedAddmission = addmissionService.updateStatus(addmissionDto, addmissionId);
        return new ResponseEntity<>(updatedAddmission, HttpStatus.OK);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAddmission(@PathVariable Long id) {
        addmissionService.deleteAddmission(id);
        return ResponseEntity.noContent().build();
    }
    @GetMapping("/{addmissionId}/courses")
    public ResponseEntity<List<CourseDto>> findCoursesByAddmissionId(@PathVariable Long addmissionId) {
        List<CourseDto> courses = addmissionService.findCoursesByAddmissionId(addmissionId);
        return ResponseEntity.ok(courses);
    }
}

