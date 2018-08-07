package com.senecaglobal.assignment.entity;

/**
* Data object which validates the JSON input data 
* when passes to the controller .
* @author  Deepak Jakhotia
* @version 1.0
* @since   2018-08-07 
*/

import java.util.List;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;

public class WorkLoadEntity {
	
	
	public WorkLoadEntity() {
	}
	
	public WorkLoadEntity(int numberOfTask, List<String> vendorNames, List<Double> workDistributionPercentage) {
		this.numberOfTask = numberOfTask;
		this.vendorNames = vendorNames;
		this.workDistributionPercentage = workDistributionPercentage;
	}
	
	@NotNull(message="Tasks cannot be null")
	//@NotEmpty(message="Tasks cannot be null")
	@PositiveOrZero(message="Tasks cannot be less than 0")
	private int numberOfTask;	
	@NotEmpty(message="Vendors name list cannot be empty")
	private List<@NotBlank String> vendorNames;
	@NotEmpty(message="Work distribution percentage list cannot be empty")
	private List<@PositiveOrZero (message="Work distribution percentage cannot have a negative value")  Double> workDistributionPercentage;
	
	public int getNumberOfTask() {
		return numberOfTask;
	}
	public void setNumberOfTask(int numberOfTask) {
		this.numberOfTask = numberOfTask;
	}
	public List<String> getVendorNames() {
		return vendorNames;
	}
	public void setVendorNames(List<String> vendorNames) {
		this.vendorNames = vendorNames;
	}
	public List<Double> getWorkDistributionPercentage() {
		return workDistributionPercentage;
	}
	public void setWorkDistributionPercentage(List<Double> workDistributionPercentage) {
		this.workDistributionPercentage = workDistributionPercentage;
	}
	
	
	
	

}
