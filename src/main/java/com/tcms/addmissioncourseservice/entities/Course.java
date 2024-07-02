
package com.tcms.addmissioncourseservice.entities;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@Entity
@Table(name="course", schema="course_schema")
public class Course {
	@Id
	@GeneratedValue
	private Long courseId;
	private String imageName;
	private String cName;
	private String cDiscription;
	private String cStatus;
	private String cShortName;
    private String cFee;
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(name="course_module",
    schema = "course_schema",
    joinColumns =@JoinColumn(name="cid",referencedColumnName="courseId"),
    inverseJoinColumns= @JoinColumn(name="mid",referencedColumnName="moduleId")
    		)
    private List<Module> modules=new ArrayList<>();
    
//    @ManyToMany(cascade = CascadeType.ALL)//category
//    private List<Addmission> admissions = new ArrayList<>();

}













/*
package com.tcms.addmissioncourseservice.entities;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@Entity
@Table(name="course", schema="course_schema")
public class Course {
	@Id
	@GeneratedValue
	private Long courseId;
	private String cName;
	private String cDiscription;
	private String cStatus;
	private String cShortName;
    private String cFee;
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(name="course_module",
    schema = "course_schema",
    joinColumns =@JoinColumn(name="cid",referencedColumnName="courseId"),
    inverseJoinColumns= @JoinColumn(name="mid",referencedColumnName="moduleId")
    		)
    private List<Module> modules=new ArrayList<>();
    
//    @ManyToMany(cascade = CascadeType.ALL)//category
//    private List<Addmission> admissions = new ArrayList<>();

}
*/




