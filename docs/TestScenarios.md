## Test scenarios

When starting the Spring backend the following user will be created:  
* **username**: testuser  
* **password**: 5EtH^8e^u6  
* **role**:       User

This user is allowed to GET, UPDATE and ADD recipes, but not DELETE
<br><br>
### **Prerequisites - Get JWT token**
1. Send a POST request to https://localhost:8443/api/auth/login with body  
(content-type: application/json)  
```json
    {
        "username": "testuser",
        "password": "5EtH^8e^u6"
    }
```
2. Get the token (payload.token) from the response body
3. Set **Authorization** Header with **Bearer <token from step 2>** in every request below
<br> <br>
### **Scenario 1**: Post a new recipe using the web service
**Given:**  User is using Postman  
**When:**  a POST request is made to https://localhost:8443/api/recipe with body  
(content-type: application/json)  
```json
{
    "name": "Traditional Pancakes",
    "vegetarian": false,
    "servings": 4,
    "directions": ["1. Cook the meat for 1 hour"],
    "ingredients": [
        {
            "name": "Floour",
            "amount": "500 gr"
        }
    ]
}
```
**Then:** the response body will contain:  
```json
{
    "timestamp": "<current timestamp>",
    "message": "Added new recipe with id 3 successfully",
    "payload": null
}
```
### **Scenario 2**: Get a recipe by id using the web service  
**Given:**  The user is using Postman and recipe with id 2 exist  
**When:**  a GET request is made to https://localhost:8443/api/recipe/2  
**Then:** the response body will contain:   
```json
{
    "timestamp": "<current timestamp>",
    "message": "Get recipe successfully",
    "payload": {
        "id": 1,
        "name": "Recipe1",
        "vegetarian": true,
        "servings": 4,
        "ingredients": [
            {
                "name": "salt",
                "amount": "10 gr"
            },
            {
                "name": "peper",
                "amount": "15 gr"
            },
            {
                "name": "flour",
                "amount": "120 gr"
            }
        ],
        "createdAt": null,
        "updatedAt": null
    }
}
```
### **Scenario 3**: Get all recipes using the web service  
**Given:**  The user is using Postman   
**When:**  a GET request is made to https://localhost:8443/api/recipe  
**Then:** the response body (payload) should contain all recipes
<br> <br>
### **Scenario 4**: Delete a recipe by id using the web service  
**Given:** The user is using Postman and recipe with id 2 exist in db  
**When:** a DELETE request is made to https://localhost:8443/api/recipe/2  
**Then:** the response body will contain
```json
{
    "timestamp": "<current timestamp>",
    "message": "Access is denied"
}
```
### **Scenario 5**: Update a recipe by id using the web service  
**Given:**  The user is using Postman and recipe with id 2 exist  
**When:**  a PUT request is made to https://localhost:8443/api/recipe/2 with body  
(content-type: application/json)  
```json
{
    "id": "2",
    "name": "NewRecipe",
    "vegetarian": true,
    "servings": 3,
    "directions": ["1. Boil water"],
    "ingredients": [
        {
            "id": "20",
            "name": "peper",
            "amount": "30 gr"
        },
        {
            "name": "water",
            "amount": "13 gr"
        }
    ]
}
```
**Then:** the recipe with id 3 should be replaced by the new recipe  
```json
{
    "timestamp": "<current timestamp>",
    "message": "Updated recipe with id 2 successfully",
    "payload": {
        "id": 2,
        "name": "NewRecipe",
        "vegetarian": true,
        "servings": 3,
        "directions": ["1. Boil water"],
        "ingredients": [
            {
                "name": "peper",
                "amount": "30 gr"
            },
            {
                "name": "water",
                "amount": "13 gr"
            }
        ],
        "createdAt": null,
        "updatedAt": "<current timestamp>"
    }
}
```
<br> <br>
### **Scenario 6**: Post a invalid recipe using the web service
**Given:**  The user is using Postman  
**When:**  a POST request is made to https://localhost:8443/api/recipe with  
(content-type: application/json)  
```json
{
    "name": "Cake",
    "servings": 4,
    "directions": ["1. Boil water"],
    "ingredients": [
        {
            "name": "Butter"
        }
    ]
}
```
**Then:** the response body most should contain:   
```json
{
    "timestamp": "<current timestamp>",
    "message": "Validation errors",
    "fieldErrors": {
        "ingredients[0].amount": "must not be empty",
        "vegetarian": "must not be null"
    }
}
```
### **Scenario 7**: Get unexisting recipe (id) using the web service
**Given:**  The user is using Postman  
**When:**  a GET request is made to https://localhost:8443/api/recipe/101   
**Then:** the response body most should contain:   
```json
{
    "timestamp": "<current timestmap>",
    "message": "Recipe not found",
    "statusCode": 404,
    "error": "Not Found"
}
```
### **Scenario 8**: Send a request to an invalid API endpoint using the web service
**Given:**  The user is using Postman  
**When:**  a GET request is made to https://localhost:8443/api/recipe/bar   
**Then:** the response body most should contain:   
```json
{
    "timestamp": "<current timestamp>",
    "status": 400,
    "error": "Bad Request",
    "path": "/api/recipe/bar"
}
```
### **Scenario 9**: Send a request without valid JWT token in the Header using the web service
**Given:**  The user is using Postman without Authorization header or wrong Bearer  
**When:**  a GET request is made to https://localhost:8443/api/recipe/2  
**Then:** the response body most should contain:   
```json
{
    "timestamp": "<current timestamp>",
    "message": "You are not aurorized for this request"
}
```

### Required for scenario 10: Create new user with role admin  
1. Send a POST request to https://localhost:8443/api/auth/register with body  
```json
    {
        "username": "adminuser",
        "password": "5EtH^8e^u6",
        "email": "admintest@user.com",
        "role": [
            "User",
            "Admin"
        ]
    }
```
2. The response body should contain:
```json
    {
        "timestamp": "<current timestamp>",
        "message": "User registered successfully!",
        "payload": null
    }
```
3. Get the new JWT token, follow the **Prerequisites** (top of document) again but replace username with adminuser
<br><br>
### **Scenario 10**: User, with admin role, send DELETE request using the web service
**Given:**  The user is using Postman as a user with Admin role  
**When:**  a DELETE request is made to https://localhost:8443/api/recipe/2  
**Then:** the response body most should contain:   
```json
{
    "timestamp": "<current timestamp>",
    "message": "Deleted recipe with id 2 successfully",
    "payload": null
}
```