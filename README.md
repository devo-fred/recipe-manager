## Recipe manager  

Java Spring REST API backend with MariaDB database.  


#### **Prerequisites**  

The Java (Spring) backend app and MariaDB database will be deployed to Docker containers using Docker & Docker Compose.
1. Install Git
2. Install Docker & Docker Compose.  
	- Docker Desktop (Windows & Mac) also include Docker Compose 
	- On Linux you might need to install Docker & Docker Compose separate  
#### **How to run it?**  

1) git clone https://github.com/devo-fred/recipe-manager.git  
2) Inside the root folder of project run:  
`docker-compose up`

After startup 2 example recipes will be created in the MariaDB. During the first start Spring Hibernate will try to deleted the tables which don't exist yet. These errors can be safely ignored and won't show up anymore after following restarts of the Spring backend (without DB restart).

Also, a User is created. For more information see TestScenarios.md in Docs folder.

### API Documentation
Swagger is used for API documentation. URL: https://localhost:8443/swagger-ui/ (last / is required)
