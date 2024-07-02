package com.tcms.addmissioncourseservice.exceptions;

public class ResourceNotFoundException extends RuntimeException {
	String resourceName;
	String fieldName;
	long fieldValue;
	public ResourceNotFoundException(String resourceName,String fieldName,long fieldValue ) {
		super(String.format("%s not found with %s : %s",resourceName,fieldName,fieldValue ));
		this.resourceName=resourceName;
		this.fieldName=fieldName;
		this.fieldValue=fieldValue;
	}
	public ResourceNotFoundException(String resourceName, String fieldName, String fieldValue) {
        super(String.format("%s not found with %s : %s", resourceName, fieldName, fieldValue));
        this.resourceName = resourceName;
        this.fieldName = fieldName;
        this.fieldValue = Long.parseLong(fieldValue); // Parsing string to long
    }
	public ResourceNotFoundException(String message) {
		super(message); // Calls the superclass constructor with the provided message
	    // Here, you can define default values or parse the message to set fields
	    // For demonstration, we'll use default values:
	    this.resourceName = "UnknownResource";
	    this.fieldName = "UnknownField";
	    try {
	        this.fieldValue = Long.parseLong(message); // Attempt to parse the message to a long
	    } catch (NumberFormatException e) {
	        this.fieldValue = -1; // Default value if parsing fails
	    }
	}
	

}
