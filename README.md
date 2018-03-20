# OrphanageProject

## Spring backend project

### Development

When you want run application from console run command

```
mvnw spring-boot:run
```

Application create database and insert all roles and one user with system role

```
username: system
password: admin
```

When want to use debugger, you must uncomment configuration section from pom.xml and run above command. Debbuger works as remote control on port 5005.



### Production

When you want create production file first change and 

```
spring.jpa.hibernate.ddl-auto = none
```

Next run

```
mvnw package
```

###Build with

* [Maven](https://maven.apache.org) - Dependency Management
* [SpringFramework](https://projects.spring.io/spring-framework/) - Framework to build RestApi Application
* [Hibernate](http://hibernate.org) - Tool to create ORM from database

## Desktop App
