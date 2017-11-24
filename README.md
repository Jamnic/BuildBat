# BuildBat
Web build tool, currently:

* Uses Git to manage versioning of projects
* Uses Apache Maven to build Java projects
* Uses Apache Tomcat to run Java projects
* Uses Windows commandline commands
* All requests can be parametrized with project-specific attributes eg. {projectName} or {warFile}

This repo contains only backend application. Frontend is here https://github.com/Jamnic/BuildBat-web

## Usage

```
mvn -U clean install
cd web
mvn spring-boot:run
```

## Technologies and techniques

* Kotlin
* Spring boot
* RESTful
* Strongly object oriented

## Example requests

Basic use case - register tools (Maven and Tomcat), add a new project, then build, deploy and run it.

```
// Register Maven, which will be used to build project
PUT localhost:10000/maven/configuration {"name": "My Maven configuration", "version": "*your maven version*", "home": "*your maven home*"}

// Pick Tomcat container, which will be used to run app
PUT localhost:10000/tomcat/container {"name": "My Tomcat Container", "home": "*your tomcat home*"}

// Register Tomcat configuration and choose which port and Tomcat container will be used
PUT localhost:10000/tomcat/configuration {"name": "My Tomcat Configuration", "port": "1050", "tomcat": "My Tomcat Container"}

// Register your Java web application
PUT localhost:10000/project/MyProject {"directory": "*your project home directory*", "customParams": {"maven": "My Maven Configuration", "tomcat": "My Tomcat Configuration"}}

// Build it and war package with Maven
POST localhost:10000/maven {"command": "-U clean install war:war", "projectName": "MyProject", "executor": "maven"}

// Copy created war file to Tomcat container (please notice parameters in command)
POST localhost:10000/tomcat {"command": "cmd.exe /c copy \"{warFile}\" \"{home}webapps\\{pathName}.war\"", "projectName": "MyProject", "executor": "tomcat", "tomcatConfiguration": "My Tomcat Configuration"}

// Run application
POST localhost:10000/tomcat {"command": "cmd.exe /c catalina.bat run -config \"{serverXml}\"", "projectName": "MyProject", "executor": "tomcat", "tomcatConfiguration": "My Tomcat Configuration"}
```
