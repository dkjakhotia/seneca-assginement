package com.senecaglobal.assignment.service;

/**
* 
* Service class to handling the logic for class distribution  
* 
* @author  Deepak Jakhotia
* @version 1.0
* @since   2018-08-07 
*/

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.senecaglobal.assignment.exception.WorkLoadException;
import com.senecaglobal.assignment.entity.WorkLoadEntity;
import com.senecaglobal.assignment.util.ArrayIndexComparator;

@Component
public class WorkLoadDistributionService {

	Logger logger = LoggerFactory.getLogger(WorkLoadDistributionService.class);
	
	//Does validation on the input data  
	public Map<String, Long> calculateWorkDistribution(WorkLoadEntity tasksEntity) {

		List<Double> workPercentageList = tasksEntity.getWorkDistributionPercentage();
		double workPercentageSum = workPercentageList.stream().collect(Collectors.summingDouble(Double::doubleValue));
		DecimalFormat df = new DecimalFormat("0.00");
		workPercentageSum = Double.parseDouble(df.format(workPercentageSum));

		// Case - Aggregate sum of all the percentage if exceeds 100 throw back error
		// message
		if (workPercentageSum != 100.00) {
			throw new WorkLoadException("Sum of percentage of distribution of tasks is not equal to 100.");
		}

		// Case - Size of vendors list not the same as distribution percentage list
		// throw back error message
		if (tasksEntity.getWorkDistributionPercentage().size() != tasksEntity.getVendorNames().size()) {
			throw new WorkLoadException("Size of vendors and distribution percentage must match");
		}

		return taskDistribution(tasksEntity);

	}

	
	/**
	 * Returns an Map object that has data of the task allocated  
	 * This method always  
	 *
	 * @param  WorkLoadEntity
	 * @return Map<String, Long> with key as vendor name 
	 * and value as the task the vendor is allocated . 
	 * 
	 */
	private Map<String, Long> taskDistribution(WorkLoadEntity tasksEntity) {
		Map<String, Long> workAllocation = new HashMap<String, Long>();
		try {
			List<Double> workPercentageList = tasksEntity.getWorkDistributionPercentage();
			List<String> vendorNames = tasksEntity.getVendorNames();
			int numberOfTasks = tasksEntity.getNumberOfTask();

			Double[] workPercentageArray = new Double[workPercentageList.size()];
			int index = 0;
			for (Double workPecentage : workPercentageList) {
				workPercentageArray[index] = workPecentage;
				index++;
			}

			ArrayIndexComparator comparator = new ArrayIndexComparator(workPercentageArray);
			Integer[] indexes = comparator.createIndexArray();
			Arrays.sort(indexes, comparator);
			index = 0;

			List<Long> tasksAllocation = new ArrayList<Long>();
			if (numberOfTasks <= vendorNames.size()) {
				int sortedIndex = indexes.length - 1;
				if (numberOfTasks == 1) {
					workAllocation.put(vendorNames.get(sortedIndex), 1l);
					return workAllocation;
				}
				long leftOverTask = numberOfTasks;
				for (Double percentageOfWork : workPercentageList) {
					int sortedIndexVal = indexes[sortedIndex];
					long taskCount = Math.round((numberOfTasks * percentageOfWork) / 100);
					if (taskCount <= 0.5 || taskCount == 0) {
						workAllocation.put(vendorNames.get(sortedIndexVal), taskCount);
						return workAllocation;
					} else {
						leftOverTask = leftOverTask - taskCount;
						workAllocation.put(vendorNames.get(sortedIndexVal), taskCount);
						sortedIndex--;
					}

					if (leftOverTask == 0) {
						break;
					}

				}
			} else {
				long leftOverTask = numberOfTasks;
				for (Double percentageOfWork : workPercentageList) {
					long taskCount = Math.round((numberOfTasks * percentageOfWork) / 100);
					tasksAllocation.add(taskCount);
					leftOverTask = leftOverTask - taskCount;

					if (leftOverTask != 0 && leftOverTask / vendorNames.size() == 0) {

						if (leftOverTask < 0) {
							System.out.println("if < leftOverTask " + indexes.length);
							int sortedIndex = indexes.length - 1;
							do {
								int getIndex = indexes[sortedIndex];
								long val = tasksAllocation.get(getIndex);
								tasksAllocation.set(getIndex, val - 1);

								sortedIndex--;
								leftOverTask++;
							} while (leftOverTask < 0);

						} else {
							int sortedIndex = indexes.length - 1;
							do {
								int getIndex = indexes[sortedIndex];
								if (tasksAllocation.size() < getIndex + 1) {
									tasksAllocation.add(getIndex, 1l);
								} else {
									long val = tasksAllocation.get(getIndex);
									tasksAllocation.set(getIndex, val + 1);
								}

								sortedIndex--;
								leftOverTask--;
							} while (leftOverTask > 0);
						}

					}

					if (leftOverTask == 0) {
						break;
					}

				}

			}

			index = 0;
			for (Long taskUpdated : tasksAllocation) {
				workAllocation.put(vendorNames.get(index), taskUpdated);
				index++;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return workAllocation;
	}

}
