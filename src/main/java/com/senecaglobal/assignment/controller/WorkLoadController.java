package com.senecaglobal.assignment.controller;


/**
* Rest controller to exchange data using JSON format . 
*
* @author  Deepak Jakhotia
* @version 1.0
* @since   2018-08-07 
*/

import java.util.Map;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.senecaglobal.assignment.entity.WorkLoadEntity;
import com.senecaglobal.assignment.service.WorkLoadDistributionService;

@RestController
@RequestMapping("/workload")
public class WorkLoadController {
	
	Logger logger = LoggerFactory.getLogger(WorkLoadController.class);
	
	@Autowired
	WorkLoadDistributionService workLoadDistributionService;
	
	/**
	 * Returns an Map object that has data of the task allocated  
	 * 
	 * @param  WorkLoadEntity mapped to json input 
	 * @return Map<String, Long> with key as vendor name 
	 * and value as the task the vendor is allocated . 
	 * 
	 */
	
	@PostMapping(value = "/distribute", produces=MediaType.APPLICATION_JSON_VALUE)
	public Map<String, Long> distribute(@RequestBody @Valid WorkLoadEntity workLoadEntity) throws Exception {
		logger.info("WorkLoadController "+workLoadEntity.getNumberOfTask());
		Map<String, Long> responseMap = workLoadDistributionService.calculateWorkDistribution(workLoadEntity);
		return responseMap;			
		
	}

}
