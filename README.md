# Web Scraper for https://kbdfans.com

The web scraper project is a Spring Boot application which allows a user to scrape the website https://kbdfans.com.
The user is able to view the product information (sku, name, price) from the browser.

## Architecture Overview
Spring Boot is the framework of the project. It allows for easier wiring of the scraper, endpoints, as well as the database.
There are two use cases as listed below:

### Scrape /api/scrape
browser --> controller --> service --> scraper --> repository --> JPA --> h2 database

### Browser Display Products /api/products
browser --> controller --> service --> repository --> JPA --> h2 database

## Prerequisites

Before you begin, ensure you have met the following requirements:

* You have JDK 11 version installed
* You have Maven 3.6.x installed

### IDE Configuration

```spring.datasource.url=jdbc:h2:mem:testdb
spring.datasource.driverClassName=org.h2.Driver
spring.datasource.username=sa
spring.datasource.password=password
spring.jpa.database-platform=org.hibernate.dialect.H2Dialect

spring.h2.console.enabled=true
```

### Run in IntelliJ

To run the project, use the Maven plugin. Alternatively, run from the WebscraperApplication.
After running the project, enter http://localhost:8080/api/scrape into your browser to invoke the endpoints.
Open a new tab and enter http://localhost:8080/api/products to view the products. Use a JSON formatter plugin to view
the results. The products will take several minutes to display as the initial pages may not contain product information.
If you want to view the information from the database console, enter http://localhost:8080/h2-console, and enter the
password "password".

### Using Maven
* `mvn clean spring-boot:run`

#### H2 Database
The H2 Database is a memory database. It stores data as long as the program is running.

## Supported API Endpoints

### GET /api/scrape
To start the scraping process, use this endpoint. Process may take a while due to the amount of content.
### GET /api/products
Retrieve the products extracted by the scraper with this endpoint. Use JSON formatter extension for best results.

## Integrations
* Jsoup

## Technology Stack
* Java 11
* Spring Boot 
* Maven

## Contacts
If you have any questions regarding this project, please locate me at:
* brianzou03@gmail.com
* https://github.com/brianzou03
* https://www.linkedin.com/in/brian-zou-8bbab4203/


## Credits
These tutorials were a helpful guideline to start this project.
https://www.baeldung.com/spring-boot-h2-database
https://mkyong.com/java/jsoup-basic-web-crawler-example/

