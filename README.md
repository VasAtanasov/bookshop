# Bookshop System


### Techs tools and frameworks used.

 - Maven
 - Java 11
 - Spring Boot
 - Lombok
 - MySQL
 - Liquibase

#### Database for a Bookshop System. 

A bookshop keeps books. A book can have one author and many categories. Each category can be placed on many books. Let's create a class for each main table.

- Book - id, title (between 1..50 symbols), description (optional, up to 1000 symbols), edition type (NORMAL, PROMO or GOLD), price, copies, release date (optional), age restriction (MINOR, TEEN or ADULT)

- Author - id, first name (optional) and last name

- Category - id, name

### How to run the program

Type in the console from the root folder of the project

```shell script
    ./mvnw clean install && ./mvnw spring-boot:run -pl bookshop-app
```

#### Prints the following queries that:

- All books after the year 2000. Prints only their titles.

- All authors with at least one book with release date before 1990. Prints their first name and last name.

- All authors, ordered by the number of their books (descending). Prints their first name, last name and book count.
	
- All books from author George Powell, ordered by their release date (descending), then by book title (ascending). Prints the book's title, release date and copies.
