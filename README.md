Spring Boot @RestController Example

Description

This project contains the sample code used in for taks distribution. It's a Spring Boot application with one plain Java object WorkLoadEntity and a Spring Controller WorkLoadController with a component WorkLoadDistributionService.

The main goal of the project is to demostrate how to allocate given number of task amongst list of vendors based on the percentage allocation using a rest end point in Spring Boot .

How does it work?

You can use the endpoints behind http://localhost:8080/workload/distribute and pass a JOSN as per the below protocol

{  
   "numberOfTask":100,
   "vendorNames":[
      "VendorA",
      "VendorB",
      "VendorC"
   ],
   "workDistributionPercentage":[
      50,
      30,
      20
   ]
}

