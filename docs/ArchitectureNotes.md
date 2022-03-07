## Achitectural Notes

This is the list of architectural choices I made for this project. Architectural choices are often debatable. In general I like to use different approaches that seem to make sense and are worth testing.  Testing , in this context, doesn't mean without thorough investigation and security-first mindset. 


	1. DTO (Data Transfer Object) related to rest API should only be used within the Controller class. 
	The service class should not use DTO classes related to the controller. 
	The idea is to make the service class less coupled to the controllers

    2. Only create interfaces when they are used (for example, different implementations). 

	3. Use of service class because of separation of controller DTO classes and additional functionality in future    

	4. Use of separate custom/separate controller exception handler, although in the current state it's not very different 
	from the default. The need for additional or more specific custom exceptions is very likely

	5. Using JSON Web Token (JWT) to secure REST API

	6. The Java Spring backend is using a self-signed certificate. 

	7. Development is done completely in VSCode with Dev Containers (deploy to Docker container) to experience 
	the differences with, for example, Eclipse. The .devcontainer folder is intentionally commited to GIT


## Important Notes

	1. HTTPS is configured with self-signed certificate
	2. Pagination is not used for getAllRecipes
	3. No vulnabilty scan (for example OWASP ZAP scan) is executed. 
	Only dependency analytics using Snyk (based on pom.xml)







