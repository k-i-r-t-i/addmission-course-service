package com.tcms.addmissioncourseservice.services;

import java.util.List;

import com.tcms.addmissioncourseservice.payload.AddmissionDto;
import com.tcms.addmissioncourseservice.payload.CourseDto;

public interface AddmissionService {
	AddmissionDto createAddmission(AddmissionDto addmissionDto, List<Long> courseIds);
    AddmissionDto getAddmission(Long addmissionId);
    List<AddmissionDto> getAllAddmissions();
    AddmissionDto updateAddmission(Long addmissionId, AddmissionDto addmissionDto, List<Long> courseIds);
    void deleteAddmission(Long addmissionId);
    List<CourseDto> findCoursesByAddmissionId(Long addmissionId);
    //update
    AddmissionDto updateStatus(AddmissionDto addmissionDto, Long addmissionId);
}
