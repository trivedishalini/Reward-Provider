# Reward-Provider
 Reward Provider Backend Application

# Tools and technologies used
* Spring Boot - 2.7.5
* JDK - 11
* Spring Framework - 2.7.5
* Hibernate - 5.2.17. Final
* JPA
* Maven - 3.8.6
* Intellij
* H2 Embedded Database

# Local Setup:

# Prerequisites:

* Fork and clone the [Reward-Provider] (https://github.com/trivedishalini/Reward-Provider.git) project
* Install Intellij
* Install java 11
* Install maven 3.6.3 optional (use link https://www.digitalocean.com/community/tutorials/install-maven-mac-os)

# Steps to Run application from Intellij Editor:
* Reload dependencies by right click on pom.xml
* Then spring boot application has an entry point Java class called RewardProviderApplication.java with the public static void main(String[] args) method, which you can run to start the application.

# Steps to Run application from Terminal or Command Prompt:
* Go to the folder where Reward-Provider repository cloned from terminal
* To reload project dependencies ,execute command "mvn clean install" (Install maven first using link provided above)
* To run application ,execute command "mvn spring-boot:run"