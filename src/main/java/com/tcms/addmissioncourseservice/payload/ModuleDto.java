package com.tcms.addmissioncourseservice.payload;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ModuleDto {
	private Long moduleId;
    private String mName;
    private String mDiscription;
    private String mStatus;
    private String mShortName;
    private String mFee;
}