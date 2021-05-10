# General dependencies overview

A small list of possible dependecies which could be added to the project. Grouped in layers like testing or database related. Some bigger libraries will hold several smaller ones which will be listed under set dependency.

Versions are not provided, because they are very prone to changes.

* ##Parent
  * ###Spring Parent
  
          <groupId>org.springframework.boot</groupId>
          <artifactId>spring-boot-starter-parent</artifactId>
 
  
* ##Spring
  * ###Spring application
  
          <groupId>org.springframework.boot</groupId>
          <artifactId>spring-boot-starter</artifactId>
  
  * ###Spring web application
  
          <groupId>org.springframework.boot</groupId>
          <artifactId>spring-boot-starter-web</artifactId>
  
  * ###Spring Jpa Repositories
  
          <groupId>org.springframework.boot</groupId>
          <artifactId>spring-boot-starter-data-jpa</artifactId>
  
* ##Testing
  * ###Spring testing  (contains assertj-core / mockito-core / mockito-junit-jupiter)
  
          <groupId>org.springframework.boot</groupId>
          <artifactId>spring-boot-starter-test</artifactId>
          <scope>test</scope>
    
  * ###Spring security testing
  
          <groupId>org.springframework.security</groupId>
          <artifactId>spring-security-test</artifactId>
          <scope>test</scope>
  
  * ###Rest assured (for end to end testing)

          <groupId>io.rest-assured</groupId>
          <artifactId>spring-mock-mvc</artifactId>
          <scope>test</scope>

  * ###Selenide (test library to work with frontend)

          <groupId>com.codeborne</groupId>
          <artifactId>selenide</artifactId>
  
* ##Database
  * ###Postgresql driver          

          <groupId>org.postgresql</groupId>
          <artifactId>postgresql</artifactId>
  
  * ###H2 creates database in memory for testing purposes
        
          <groupId>com.h2database</groupId>
          <artifactId>h2</artifactId>
          <scope>test</scope>
  
  * ###Flyway (database migration)
        
          <groupId>org.flywaydb</groupId>
          <artifactId>flyway-core</artifactId>

* ##Security
  * ###Spring Security
  
          <groupId>org.springframework.boot</groupId>
          <artifactId>spring-boot-starter-security</artifactId>

  * ###JWT Tokens

          <groupId>io.jsonwebtoken</groupId>
          <artifactId>jjwt-api</artifactId>

          <groupId>io.jsonwebtoken</groupId>
          <artifactId>jjwt-impl</artifactId>
          <scope>runtime</scope>

          <groupId>io.jsonwebtoken</groupId>
          <artifactId>jjwt-jackson</artifactId>
          <scope>runtime</scope>

* ##Varia
   * ###Lombok (annotations for getters & setters, no argument constructor)
  
         <groupId>org.projectlombok</groupId>
         <artifactId>lombok</artifactId>
         <scope>provided</scope>

   * ###Swagger UI Springfox

         <groupId>io.springfox</groupId>
         <artifactId>springfox-boot-starter</artifactId>
  
   * ###Swagger UI Openapi 

         <groupId>org.springdoc</groupId>
         <artifactId>springdoc-openapi-ui</artifactId>
  
         <groupId>org.springdoc</groupId>
         <artifactId>springdoc-openapi-security</artifactId>

   * ###Moddelmapper (Helper for mapping from and to DTO's)

         <groupId>org.modelmapper</groupId>
         <artifactId>modelmapper</artifactId>



            
      



      