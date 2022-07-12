# AMARIS TEST

## Description
The project is based on a small web service which uses the following technologies:

* Java 11
* Spring Boot
* Database H2 (In-Memory)
* Maven

##How to run
Select the project folder and type on shell: mvn spring-boot:run

##Configuration 
The application is configured to the port 8080, type on your browser 'localhost:8080/hometest/rest/v1/account/balance/14537780'
to check it! For further details check application.properties

## Check some API 
curl --location --request GET 'localhost:8080/hometest/rest/v1/account/balance/14537780'

##To inspect db 
Connect to with standard credential (see application.properties)
http://localhost:8080/hometest/h2/login.do

