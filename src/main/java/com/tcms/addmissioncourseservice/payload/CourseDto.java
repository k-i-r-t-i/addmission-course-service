package com.tcms.addmissioncourseservice.payload;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CourseDto {
	private Long courseId;
	private String imageName;
	private String cName;
	private String cDiscription;
	private String cStatus;
	private String cShortName;
    private String cFee;
    private List<ModuleDto> modules = new ArrayList<>();
//    private List<AddmissionDto> admissions;
}














/*
package com.tcms.addmissioncourseservice.payload;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CourseDto {
	private Long courseId;
	private String cName;
	private String cDiscription;
	private String cStatus;
	private String cShortName;
    private String cFee;
    private List<ModuleDto> modules = new ArrayList<>();
//    private List<AddmissionDto> admissions;
}
*/






