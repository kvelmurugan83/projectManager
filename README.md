
# Project Manager
Project Manager Application - Single Page application build using Angular 5 as UI and Spring boot MicroServices is back-end services

##Git Repository
```
https://github.com/kvelmurugan83/projectManager.git
```

## Requirement
1. Docker
2. Java 8
3. Node v8.9.1 above
4. npm 5.5.1 

## Setting up the environment - Mysql 
Go to Project manager API and run the below command to start the mysql server in docker

```
docker-compose up -d
```

### Running Spring boot Micro service using Maven.
```
mvnw package && java -jar target/projectManagerApi-0.0.1-SNAPSHOT.jar
```

### Docker image for Task Manager API Micro service
Go to Project manager API and run the below command to building image for Project Manager API

```
mvnw install dockerfile:build
```

### Starting Project Manager API App
After Building the docker image, To execute the below command to run the project manager API Application in docker 

```
docker run -p 8080:8080 -t kvel/projectManagerApi
```

### Starting Project Manager UI

Install node packages by executing the below command

```
npm install
```

Start the Angular UI App by executing the below command

```
ng serve --port 4200
```

### Application URL 

```
http://localhost:4200
```

### Install Jenkins in Docker container
docker run -u root --rm -d -p 8080:8080 -p 50000:50000 -v jenkins-data:/var/jenkins_home -v /var/run/docker.sock:/var/run/docker.sock jenkinsci/blueocean 
