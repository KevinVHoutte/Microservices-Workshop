# Chapter 2: Service Registry 
Since we are working with a distributed system, the services don't know of each other existence.
So how do we let the services discover each other for querying data, etc...? 
To tackle this issue, Spring Cloud provides us the ability to register our services with Eureka.
Eureka as a service is comparable to a phone book for your microservices.
Each service registers itself with the service registry and tells the registry where it lives.

## Exercise

### [Documentation Eureka](http://cloud.spring.io/spring-cloud-static/spring-cloud-netflix/1.3.4.RELEASE/)
 
### Eureka Server
Go to [Spring Initializr](https://start.spring.io/), here you will see a project initialization structure. 
Name the project and search & add the Eureka Server dependency.  
* Generate, unzip and add it to your local git folder.
* Open existing eureka server project. 
* Annotate primary class
* Change your application.properties to application.yml.

#### application.yml
* Name your application
* Configure the eureka server uri
* Tell eureka to not register and fetch itself from the server. 
* Have a different port when running.

### Microservice Rental & Movie aka Eureka Clients
* Add following dependencies 

``` maven
<dependencyManagement>
		<dependencies>
			<dependency>
				<groupId>org.springframework.cloud</groupId>
				<artifactId>spring-cloud-dependencies</artifactId>
				<version>Camden.SR5</version>
				<type>pom</type>
				<scope>import</scope>
			</dependency>
		</dependencies>
	</dependencyManagement>
```
Since we started working with Cloud components, we will be needing a BOM to manage our versions for us. 
And add the following:

``` maven
		<dependency>
			<groupId>org.springframework.cloud</groupId>
			<artifactId>spring-cloud-starter-eureka</artifactId>
		</dependency>
```
* Annotate the primary class
* Configure the location of the eureka server in your application.yml
* Put register and fetch of the eureka server on true

If you found the solution, the logs will tell you that they register themselves with Eureka.

## End result
* Go to the Eureka application: localhost:port eureka 
* You should now see an UI Eureka dashboard with both the microservices registered.


## Next step
 ```sh
   git checkout chpt3-ConfigServer
   ```





