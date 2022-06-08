package com.vc.blog.exceptions;

public class ResourceNotFoundException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	String resourceName;
	String fieldName;
	Long fieldValue;

	public ResourceNotFoundException(String resourceName, String fieldName, Long fieldValue) {
		super(String.format("%s NOT FOUND WITH %s : %s", resourceName, fieldName, fieldValue));
		this.resourceName = resourceName;
		this.fieldName = fieldName;
		this.fieldValue = fieldValue;
	}

	public ResourceNotFoundException() {
		// TODO Auto-generated constructor stub
	}

}
