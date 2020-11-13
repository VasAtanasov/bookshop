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

#### Usage examples

```text
Bookshop Management System
Main command: author
usage: author [ATL, FN, TTL] [space separated arguments]
	atl - Prints the total number of book copies by author. Orders the results descending by total book copies.
	fn ends_with(string) - Prints the names of those authors, whose first name ends with a given string.
	ttl first_name(string) last_name(string) - Prints the total number of books for author's first and last name.
Main command: book
usage: book [ANS, AR, BTL, ET, IC, PR, RD, RM, RY, TC, TTL] [space separated arguments]
	ans starts_with(string) - Prints the titles of books, which are written by authors, whose last name starts with a given string.
	ar age_restriction(string) - Prints the titles of all books, for which the age restriction matches the given input.
	btl char_count(int) - Prints the number of books, whose title is longer than a given number.
	et edition_type(string) copes(int) - Prints the titles of the given edition type, which have less than the given copies.
	ic date(dd-MM-yyyy)(string) copies(int) - Increases the copies of all books released after a given date with a given number. Prints the total amount of book copies that were added.
	pr lower_price(double) higher_price(double) - Prints the titles and prices of books with given lower and higher price.
	rd date(dd-MM-yyyy)(string) - Prints the title, the edition type and the price of books, which are released before a given date. The date will be in the format dd-MM-yyyy.
	ic copies(int) - Removes from the database those books, which copies are lower than a given number. Prints the number of books that were deleted from the database.
	ry year(int) - Prints the titles of all books that are NOT released in a given year.
	tc title(string) - Prints the titles of books, which contain a given string (regardless of the casing).
	ttl title(string) - Prints information (title, edition type, age restriction and price) for a book by given title.
Main command: help - Prints commands description
Main command: exit - Quits the application
```

### Examples

##### input

```text
book ar teen
```

##### output

```text
bookshop > All Passion Spent
bookshop > Wide Sea
bookshop > Antic Hay
bookshop > Blithe Spirit
bookshop > Brandy of the Damned
bookshop > Bury My Heart at Wounded Knee
bookshop > Cabbages and Kings
bookshop > Cover Her Face
bookshop > The Cricket on the Hearth
...
```

##### input

```text
book rd 12-05-1992
```

##### output

```text
bookshop > All Passion Spent PROMO 7.18
bookshop > As I Lay Dying PROMO 25.81
bookshop > Bury My Heart at Wounded Knee GOLD 3.86
bookshop > A Catskill Eagle NORMAL 15.78
bookshop > Clouds of Witness NORMAL 23.87
bookshop > Consider Phlebas NORMAL 46.43
bookshop > Consider the Lilies PROMO 30.89
bookshop > The Curious Incident of the Dog in the Night-Time NORMAL 23.41
bookshop > Death Be Not Proud GOLD 38.64
bookshop > Dying of the Light PROMO 18.58
bookshop > East of Eden NORMAL 45.43
bookshop > Eyeless in Gaza NORMAL 1.82
...
```

##### input

```text
author atl
```

##### output

```text
bookshop > Rachel Hunter - 220203 books
bookshop > Brenda Griffin - 179001 books
bookshop > Lisa Davis - 176291 books
bookshop > Randy Graham - 175285 books
bookshop > Roger Porter - 166364 books
bookshop > Frances Ross - 153295 books
bookshop > Christina Jordan - 134658 books
bookshop > Russell Howard - 121167 books
bookshop > Amanda Rice - 118918 books
bookshop > Earl Bennett - 116773 books
...
```