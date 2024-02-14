				                        Grab A Cycle
                                    

-	Grab a cycle is an application for booking a cycle which is available and unbooking if it is occupied by that user only and admin can perform crud operations for the cycle.

Tech Stack –
Spring Boot and MVC
Thymeleaf
MySQL

Backend – 
-	Spring Boot 
*Two Controllers – Spring MVC and Spring Rest
*Business logic on Service layer
*Security and Authentication
*Exceptions and Errors
*Backend Validation
*Spring Batch (csv file generated)
*Caching on service layer


Frontend – 
-	Thymeleaf
-	Ajax
-	Validation 
-	CSS
-	JS

MySQL –
-	Four tables (user, cycle, role, user_role)
-	Inserted 750000 rows into DB
-	Indexing


User Guide
	- If you want to start the application on your local device then follow these steps
 		-In application.properties change the username and password for mysql connection to your mysql username and password
   		-create a database in your mysql db.
     		-Now select the GrabACycleApplication.java and run the application on port 8091, you can change the port in application.properties
