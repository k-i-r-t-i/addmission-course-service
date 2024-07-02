package com.tcms.addmissioncourseservice.payload;

import java.util.ArrayList;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class AddmissionDto {
    private Long addmissionId;
    private String salutation;
    private String first_name;
    private String last_name;
    private String tel_code;
    private String mobile;
    private String email;
    private String Status;
//    private List<Long> courseIds; // Add this field to store course IDs
    private List<CourseDto> courses;
    
}