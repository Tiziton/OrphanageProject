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

When you want to create production file first change and 

```
spring.jpa.hibernate.ddl-auto = none
```

Next runs

```
mvnw package
```

When wanna run server:

```
java -jar orphanage-1.0.war 
--spring.datasource.url=jdbc:sqlserver://localhost;databaseName=databaseName
--spring.datasource.username=username 
--spring.datasource.password=password
```

When we wanna create a database, should be added one more parameter 

```
--spring.jpa.hibernate.ddl-auto=create 
```

### Build with

* [Maven](https://maven.apache.org) - Dependency Management
* [SpringFramework](https://projects.spring.io/spring-framework/) - Framework to build RestApi Application
* [Hibernate](http://hibernate.org) - Tool to create ORM from database
