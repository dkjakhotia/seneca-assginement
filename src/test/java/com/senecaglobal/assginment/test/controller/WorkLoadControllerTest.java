package com.senecaglobal.assginment.test.controller;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.constraints.NotNull;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.google.gson.Gson;
import com.senecaglobal.assignment.controller.WorkLoadController;
import com.senecaglobal.assignment.entity.WorkLoadEntity;
import com.senecaglobal.assignment.service.WorkLoadDistributionService;

@RunWith(SpringJUnit4ClassRunner.class)

public class WorkLoadControllerTest {
	/*
	
	private MockMvc mockMvc;
	
	
	
	@InjectMocks
	private WorkLoadController workLoadController;
	
	@MockBean
	private WorkLoadDistributionService workLoadDistributionService;
	
	@Before
	public void setup() throws Exception{
		mockMvc = MockMvcBuilders.standaloneSetup(workLoadController).build();
	}
	
	
		
	@Test
	public void testHello() throws Exception{	
		/*
		Mockito.when(
				workLoadDistributionService.testMockMVC()).thenReturn("mocked");*/

	/*
		RequestBuilder requestBuilder = MockMvcRequestBuilders
				.post("/test")
				.accept(MediaType.APPLICATION_JSON);		
		mockMvc.perform(requestBuilder)
		.andExpect(MockMvcResultMatchers.status().isOk());
		//.andExpect(MockMvcResultMatchers.content().string("mocked"));
		
	
	}
	
	
private String buildWorkLoadEntity() {
		
		WorkLoadEntity tasksEntity = new WorkLoadEntity();
		Double[] doubleInit = new Double[]{10.00, 80.00, 10.00};
		String[] str = new String[] {"A","B","C"};		
		List<Double> workDouble = Arrays.asList(doubleInit);
		List<String> workstr = Arrays.asList(str);
		tasksEntity.setNumberOfTask(200);
		tasksEntity.setVendorNames(workstr);
		tasksEntity.setWorkDistributionPercentage(workDouble);
		//return tasksEntity;
		Gson gson = new Gson();
		String strJson = gson.toJson(tasksEntity);
		System.out.println("strJson "+strJson);
		return strJson;
		
	}
	
	*/
	

}
