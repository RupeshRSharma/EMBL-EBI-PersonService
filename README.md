# EMBL-EBI-PersonService
Person service test assignment for EMBL EBI

1. This project a Spring boot based microservice, built using **Java 8**, **Spring boot 2.4.2** and **Gradle**
2. Build project on local using command (for MAC only) - **./gradlew clean build** from the respective Micro service parent directory, for eg. `cd ~\rupeshsharma\personservice`
3. Run using command **./gradlew bootRun** (alternatively, use docker build and docker run commands to run with docker)
4. Swagger UI - http://localhost:8080/swagger-ui.html#
5. Check status of application using - http://localhost:8080/actuator/health

### API

1. Create a Person in database with given details - <br><br>

Sample Request - <br>
   `curl -X POST "http://localhost:8080/1.0/persons" -H "accept: */*" -H "Content-Type: application/json" -d "{ \"age\": 33, \"favourite_color\": \"red\", \"first_name\": \"Rupesh\", \"last_name\": \"Sharma\"}"` <br><br>

Sample Response - <br>
   ``{
  "status": "SUCCESS",
  "errors": [],
  "person": {
    "uid": "074a92fc-3f5f-4eca-934e-dda139571bbf",
    "first_name": "Rupesh",
    "last_name": "Sharma",
    "age": 33,
    "favourite_color": "red"
  }
}``<br><br>

2. Update details of a Person in database with given id and properties - <br><br>

Sample Request - <br>
   `curl -X PUT "http://localhost:8080/1.0/persons/074a92fc-3f5f-4eca-934e-dda139571bbf" -H "accept: */*" -H "Content-Type: application/json" -d "{ \"age\": 30, \"first_name\": \"First\", \"last_name\": \"Last\", \"favourite_color\":\"blue\"}"` <br><br>

Sample Response - <br>
   ``{{
  "status": "SUCCESS",
  "errors": []
}``<br><br>

3. Get details of a Person from database with given id - <br><br>

Sample Request - <br>
   `curl -X GET "http://localhost:8080/1.0/persons/074a92fc-3f5f-4eca-934e-dda139571bbf" -H "accept: */*"` <br><br>

Sample Response - <br>
   ``{
  "status": "SUCCESS",
  "errors": [],
  "person": {
    "uid": "074a92fc-3f5f-4eca-934e-dda139571bbf",
    "first_name": "First",
    "last_name": "Last",
    "age": 30,
    "favourite_color": "blue"
  }
}``<br><br>

4. Delete a Person from database with given id - <br><br>

Sample Request - <br>
   `curl -X DELETE "http://localhost:8080/1.0/persons/074a92fc-3f5f-4eca-934e-dda139571bbf" -H "accept: */*"` <br><br>

Sample Response - <br>
   ``{
  "status": "SUCCESS",
  "errors": []
}``<br><br>

5. Get list of Persons from database - <br><br>

Sample Request - <br>
   `curl -X GET "http://localhost:8080/1.0/persons" -H "accept: */*"` <br><br>
  
Sample Response - <br>
   ``{
  "status": "SUCCESS",
  "errors": [],
  "person": [
    {
      "uid": "8f942c65-72b8-4389-8b70-810bbd4a0c9a",
      "first_name": "Rupesh",
      "last_name": "Sharma",
      "age": 33,
      "favourite_color": "red"
    },
    {
      "uid": "4299d396-cdce-4b62-9d1f-ad9bd0a0c989",
      "first_name": "First",
      "last_name": "Name",
      "age": 30,
      "favourite_color": "blue"
    }
  ]
}``<br><br>


##### Notes:
1. No security framework is present to secure the Microservcie. Spring security with oAuth 2.0 using JWT is one way of securing the service.
2. Minimal test cases are present.
3. Exception handling is done as required, not at all places
4. Logging is minimal as needed
5. Configuration is done via application properties file, same can be achieved using spring cloud config for externalization.


#### Task
###### Coding Challenge
Create a simple RESTful API which provides a service for storing, updating,
retrieving and deleting Person entities such as those represented in the JSON
below:
{
"person": [
{
"first_name": "John",
"last_name": "Keynes",
"age": "29",
"favourite_colour": "red"
},
{
"first_name": "Sarah",
"last_name": "Robinson",
"age": "54",
"favourite_colour": "blue"
}
]
}
