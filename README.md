# README #

This README would normally document whatever steps are necessary to get your application up and running.

### What is this repository for? ###

* A simple REST service which will return details of given Github repository
* [Learn Markdown](https://bitbucket.org/tutorials/markdowndemo)

### How do I get set up? ###

#### Prerequisites ####
Please install JDK 8 and latest version of Maven.

Please install Lombok plugin specific for IDE in order to avoid possible compiler warnings.

* [IntelliJ](https://plugins.jetbrains.com/plugin/6317-lombok-plugin)
* [Eclipse](https://projectlombok.org/setup/eclipse)
* [NetBeans](https://projectlombok.org/setup/netbeans)

#### Summary of set up ####
In order to run the service please execute following 
commands in the root of the project:
###### mvn package ######
###### java -jar target\github-repository-data-1.0.jar ######

Service is available under localhost and port 8080
Swagger documentation: http://localhost:8080/swagger-ui.html
    
#### Configuration ####
No additional configuration required, you can change the
logging level in src\main\resources\logback.xml

#### How to run tests ####
In order to run unit tests please execute following 
command in the root of the project:
 
###### mvn test ######

In order to run end-to-end tests please execute following 
command in the root of the project:

###### mvn verify ######

#### Who do I talk to? ####

Aleksander Golaszewski https://bitbucket.org/GoAlek/