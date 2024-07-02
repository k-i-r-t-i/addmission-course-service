package com.tcms.addmissioncourseservice.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tcms.addmissioncourseservice.entities.Addmission;

public interface AddmissionRepository extends JpaRepository<Addmission,Long>{

}

