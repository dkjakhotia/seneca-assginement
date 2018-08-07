package com.senecaglobal.assginment.test.controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.verify;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

import com.senecaglobal.assignment.entity.WorkLoadEntity;
import com.senecaglobal.assignment.exception.WorkLoadException;
import com.senecaglobal.assignment.service.WorkLoadDistributionService;

@RunWith(MockitoJUnitRunner.class)
public class WorkLoadDistributionServiceTest {
	
	@InjectMocks
	WorkLoadDistributionService workLoadDistributionService;
	
    @Before
    public void setUp() throws Exception {
         MockitoAnnotations.initMocks(this);
    }
    
    public long iterateMapToGetSum(Map<String, Long> resultMap) {		
		long sumOfMapVal = resultMap.values().stream().collect(Collectors.summingLong(i->i));
		return sumOfMapVal;
	}
    
    @Test
	public void testMapSize() {
		
		WorkLoadEntity tasksEntity = new WorkLoadEntity();
		
		List<Double> vendorTaskAllocationPercentage= new ArrayList<Double>();
		vendorTaskAllocationPercentage.add(10.00);
		vendorTaskAllocationPercentage.add(70.00);
		vendorTaskAllocationPercentage.add(20.00);
		
		List<String> vendorNames = new ArrayList<String>();
		vendorNames.add("VendorA");
		vendorNames.add("VendorB");
		vendorNames.add("VendorC");
		
		tasksEntity.setNumberOfTask(100);
		tasksEntity.setVendorNames(vendorNames);
		tasksEntity.setWorkDistributionPercentage(vendorTaskAllocationPercentage);
				
		Map<String, Long> taskAllocatedNumbers = workLoadDistributionService.calculateWorkDistribution(tasksEntity);
		assertTrue(taskAllocatedNumbers.size() == 3);
		assertThat(taskAllocatedNumbers.get("VendorA"), is(10l));		
		assertTrue(100 == iterateMapToGetSum(taskAllocatedNumbers));
		
	

	}
	
    
    @Test(expected= WorkLoadException.class)
	public void vendorTaskAllocationTotalPercentage() {
		
		WorkLoadEntity tasksEntity = new WorkLoadEntity();
		
		List<Double> vendorTaskAllocationPercentage= new ArrayList<Double>();
		vendorTaskAllocationPercentage.add(10.00);
		vendorTaskAllocationPercentage.add(80.00);
		vendorTaskAllocationPercentage.add(9.90);
		
		List<String> vendorNames = new ArrayList<String>();
		vendorNames.add("VendorA");
		vendorNames.add("VendorB");
		vendorNames.add("VendorC");
		
		tasksEntity.setNumberOfTask(200);
		tasksEntity.setVendorNames(vendorNames);
		tasksEntity.setWorkDistributionPercentage(vendorTaskAllocationPercentage);
				
		workLoadDistributionService.calculateWorkDistribution(tasksEntity);
	

	}
	
	@Test(expected= WorkLoadException.class)
	public void vendorTaskPercentageSizeMisMatch() {
		
		WorkLoadEntity tasksEntity = new WorkLoadEntity();
		
		List<Double> vendorTaskAllocationPercentage= new ArrayList<Double>();
		vendorTaskAllocationPercentage.add(10.00);
		vendorTaskAllocationPercentage.add(80.00);
		
		List<String> vendorNames = new ArrayList<String>();
		vendorNames.add("VendorA");
		vendorNames.add("VendorB");
		vendorNames.add("VendorC");
		
		tasksEntity.setNumberOfTask(200);
		tasksEntity.setVendorNames(vendorNames);
		tasksEntity.setWorkDistributionPercentage(vendorTaskAllocationPercentage);
				
		workLoadDistributionService.calculateWorkDistribution(tasksEntity);
	

	}
	
	@Test(expected= WorkLoadException.class)
	public void vendorSizeTaskPercentageSizeMisMatch() {
		
		WorkLoadEntity tasksEntity = new WorkLoadEntity();
		
		List<Double> vendorTaskAllocationPercentage= new ArrayList<Double>();
		vendorTaskAllocationPercentage.add(10.00);
		vendorTaskAllocationPercentage.add(80.00);
		
		List<String> vendorNames = new ArrayList<String>();
		vendorNames.add("VendorA");
		vendorNames.add("VendorB");
		vendorNames.add("VendorC");
		
		tasksEntity.setNumberOfTask(200);
		tasksEntity.setVendorNames(vendorNames);
		tasksEntity.setWorkDistributionPercentage(vendorTaskAllocationPercentage);
				
		workLoadDistributionService.calculateWorkDistribution(tasksEntity);
	

	}
	
	
	private WorkLoadEntity sampleTestData() {
		
		WorkLoadEntity tasksEntity = new WorkLoadEntity();
		
		
		
		List<Double> vendorTaskAllocationPercentage= new ArrayList<Double>();
		vendorTaskAllocationPercentage.add(10.00);
		vendorTaskAllocationPercentage.add(80.00);
		vendorTaskAllocationPercentage.add(20.00);
		
		List<String> vendorNames = new ArrayList<String>();
		vendorNames.add("VendorA");
		vendorNames.add("VendorB");
		vendorNames.add("VendorC");
		
		tasksEntity.setNumberOfTask(200);
		tasksEntity.setVendorNames(vendorNames);
		tasksEntity.setWorkDistributionPercentage(vendorTaskAllocationPercentage);
		return tasksEntity;
		
		
	}
	
private WorkLoadEntity sampleTestData1() {
		
		WorkLoadEntity tasksEntity = new WorkLoadEntity();
		
		
		
		List<Double> vendorTaskAllocationPercentage= new ArrayList<Double>();
		vendorTaskAllocationPercentage.add(50.00);
		vendorTaskAllocationPercentage.add(30.00);
		vendorTaskAllocationPercentage.add(20.00);
		
		List<String> vendorNames = new ArrayList<String>();
		vendorNames.add("VendorA");
		vendorNames.add("VendorB");
		vendorNames.add("VendorC");
		
		tasksEntity.setNumberOfTask(10);
		tasksEntity.setVendorNames(vendorNames);
		tasksEntity.setWorkDistributionPercentage(vendorTaskAllocationPercentage);
		return tasksEntity;
		
		
	}
	
private WorkLoadEntity sampleTestData2() {
		
		WorkLoadEntity tasksEntity = new WorkLoadEntity();
		
		
		
		List<Double> vendorTaskAllocationPercentage= new ArrayList<Double>();
		vendorTaskAllocationPercentage.add(30.00);
		vendorTaskAllocationPercentage.add(30.00);
		vendorTaskAllocationPercentage.add(40.00);
		
		List<String> vendorNames = new ArrayList<String>();
		vendorNames.add("VendorA");
		vendorNames.add("VendorB");
		vendorNames.add("VendorC");
		
		tasksEntity.setNumberOfTask(3);
		tasksEntity.setVendorNames(vendorNames);
		tasksEntity.setWorkDistributionPercentage(vendorTaskAllocationPercentage);
		return tasksEntity;
		
		
	}
	
	

private WorkLoadEntity sampleTestDataWithTaskOne() {
	
	WorkLoadEntity tasksEntity = new WorkLoadEntity();
	
	
	
	List<Double> vendorTaskAllocationPercentage= new ArrayList<Double>();
	vendorTaskAllocationPercentage.add(40.00);
	vendorTaskAllocationPercentage.add(40.00);
	vendorTaskAllocationPercentage.add(20.00);
	
	List<String> vendorNames = new ArrayList<String>();
	vendorNames.add("VendorA");
	vendorNames.add("VendorB");
	vendorNames.add("VendorC");
	
	tasksEntity.setNumberOfTask(1);
	tasksEntity.setVendorNames(vendorNames);
	tasksEntity.setWorkDistributionPercentage(vendorTaskAllocationPercentage);
	return tasksEntity;
	
	
}
	
	

}
