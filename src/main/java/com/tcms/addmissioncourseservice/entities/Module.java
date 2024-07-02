package com.tcms.addmissioncourseservice.entities;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
@Entity
@Table(name="module", schema="course_schema")
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class Module {
	@Id
	@GeneratedValue
	private Long moduleId;
	private String mName;
	private String mDiscription;
	private String mStatus;
	private String mShortName;
    private String mFee;

}
