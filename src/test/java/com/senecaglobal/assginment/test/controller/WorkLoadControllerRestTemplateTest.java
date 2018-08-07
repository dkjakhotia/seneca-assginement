package com.senecaglobal.assginment.test.controller;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import com.senecaglobal.assignment.WorkLoadDistributionApplication;
import com.senecaglobal.assignment.entity.WorkLoadEntity;
import com.senecaglobal.assignment.exception.WorkLoadException;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = WorkLoadDistributionApplication.class, webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
public class WorkLoadControllerRestTemplateTest {

	private int port = 8080;

	TestRestTemplate restTemplate = new TestRestTemplate();

	HttpHeaders headers = new HttpHeaders();

	@Test
	public void testRetrieveStudentCourse() throws Exception {

		WorkLoadEntity workLoadEntity = sampleTestData1();
		ResponseEntity<String> response = restTemplate.postForEntity(createURLWithPort("/workload/distribute"),
				workLoadEntity, String.class);
		String expected = "{ \"VendorB\": 3, \"VendorC\": 2,  \"VendorA\": 5}";
		assertTrue(response.getStatusCode() == HttpStatus.OK);
		JSONAssert.assertEquals(expected, response.getBody(), false);
	}

	@Test
	public void testBadRequest() throws Exception {
		WorkLoadEntity workLoadEntity = vendorSizeTaskPercentageSizeMisMatch();
		ResponseEntity<String> response = restTemplate.postForEntity(createURLWithPort("/workload/distribute"),
				workLoadEntity, String.class);
		assertTrue(response.getStatusCode() == HttpStatus.BAD_REQUEST);
	}

	private String createURLWithPort(String uri) {
		return "http://localhost:" + port + uri;
	}

	private WorkLoadEntity sampleTestData1() {

		WorkLoadEntity tasksEntity = new WorkLoadEntity();

		List<Double> vendorTaskAllocationPercentage = new ArrayList<Double>();
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

		List<Double> vendorTaskAllocationPercentage = new ArrayList<Double>();
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

		List<Double> vendorTaskAllocationPercentage = new ArrayList<Double>();
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

	public WorkLoadEntity vendorSizeTaskPercentageSizeMisMatch() {

		WorkLoadEntity tasksEntity = new WorkLoadEntity();

		List<Double> vendorTaskAllocationPercentage = new ArrayList<Double>();
		vendorTaskAllocationPercentage.add(10.00);
		vendorTaskAllocationPercentage.add(80.00);

		List<String> vendorNames = new ArrayList<String>();
		vendorNames.add("VendorA");
		vendorNames.add("VendorB");
		vendorNames.add("VendorC");

		return tasksEntity;

	}

}
