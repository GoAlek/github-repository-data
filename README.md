# README #

This README would normally document whatever steps are necessary to get your application up and running.

### What is this repository for? ###

* A simple REST service which will return details of given Github repository
* 1.0
* [Learn Markdown](https://bitbucket.org/tutorials/markdowndemo)

### How do I get set up? ###

##### Prerequisites  #####
Please install JDK 8 and latest version of Maven 

##### Summary of set up  #####
In order to run the service please execute following 
commands in the root of the project:
* mvn package
* java -jar target\github-repository-data-1.0-SNAPSHOT.jar
    
##### Configuration  #####
No additional configuration required, you can change the
logging level in src\main\resources\logback.xml

##### How to run tests  #####
In order to run unit tests please execute following 
command in the root of the project:
* mvn test

In order to run end-to-end tests please execute following 
command in the root of the project:
* mvn verify

### Contribution guidelines ###

* Writing tests
* Code review
* Other guidelines

### Who do I talk to? ###

* Aleksander Golaszewski
* https://bitbucket.org/GoAlek/