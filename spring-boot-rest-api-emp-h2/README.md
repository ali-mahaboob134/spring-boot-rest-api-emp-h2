# Spring Boot REST API for Employee CRD operations
	
This is a Spring Boot REST API application for Creating/Retreiving/Deleting the Employee resource
from an in-memory H2 Database.

Supported URLs:

	POST: /v1/Employee/ - returns the Employee Details saved to DB
	GET: /v1/Employee/{emp_id} - returns the existing Employee details based on ID.
	GET: /v1/Employee?city={city} - returns the list of employees based on city.
	DELETE: /v1/Employee/{emp_id} - Deletes the employee from DB.
	
	Server port used - 9090 (*refer application.yml)

Testing: 
	
	Rest clients like postman can be used for testing this application with proper urls. 
	JUnit tests are available under test folder(/src/test/java) which can be used for unit testing.
	
Best Practices:
	
	This application runs on http without any security. 
	As a best practice we can add Spring Security module so that the application runs more securely.


	