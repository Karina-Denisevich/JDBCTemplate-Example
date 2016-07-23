# JDBCTemplate-Example

##Getting started

###Dependencies

To get started add Spring and MySQL dependencies in maven [pom.xml](https://github.com/Karina-Denisevich/JDBCTemplate-Example/blob/master/pom.xml#L20-L32) file.
```xml
<!-- Spring framework -->
        <dependency>
            <groupId>org.springframework</groupId>
            <artifactId>spring</artifactId>
            <version>2.5.6</version>
        </dependency>

        <!-- MySQL database driver -->
        <dependency>
            <groupId>mysql</groupId>
            <artifactId>mysql-connector-java</artifactId>
            <version>5.1.9</version>
        </dependency>
```
___

###Book table

I used MySQL database.
```sql
create database if not exists bookJDBC;

use bookJDBC;

create TABLE book(BOOK_ID int(10) unsigned NOT NULL AUTO_INCREMENT,
NAME varchar(100) NOT NULL,
YEAR int(5) unsigned NOT NULL,
PRIMARY KEY (BOOK_ID))
ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;
```
___

###Book model

Add a book model to store book’s data. [Book.java](https://github.com/Karina-Denisevich/JDBCTemplate-Example/blob/master/src/main/java/com/github/Karina_Denisevich/spring_template/example/book/model/Book.java)

```java
public class Book {

    long bookId;
    String name;
    int year;
    
    //getter and setter methods
}
```
___



