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

Add a book model to store book’s data. [Book.java](https://github.com/Karina-Denisevich/JDBCTemplate-Example/blob/master/src/main/java/com/github/Karina_Denisevich/spring_template/example/book/model/Book.java).

```java
public class Book {

    long bookId;
    String name;
    int year;
    
    //getter and setter methods
}
```
___

###DAO pattern

Book DAO interface [BookDAO.java](https://github.com/Karina-Denisevich/JDBCTemplate-Example/blob/master/src/main/java/com/github/Karina_Denisevich/spring_template/example/book/dao/BookDAO.java).
```java
public interface BookDAO {

    void insert(Book book);

    void insertBatch(List<Book> books);

    void delete(Book book);

    Book getById(Integer id);

    List getAll();
}
```

BookDAO implementation, using JdbcDaoSupport [JdbcTemplateBookDao.java](https://github.com/Karina-Denisevich/JDBCTemplate-Example/blob/master/src/main/java/com/github/Karina_Denisevich/spring_template/example/book/dao/impl/JdbcTemplateBookDao.java).<br />
BookDAO implementation, using NamedParameterJdbcDaoSupport [NamedParameterJdbcTemplateDAO.java](https://github.com/Karina-Denisevich/JDBCTemplate-Example/blob/master/src/main/java/com/github/Karina_Denisevich/spring_template/example/book/dao/impl/NamedParameterJdbcTemplateDAO.java).<br />
BookDAO implementation, using SimpleJdbcDaoSupport [SimpleJdbcTemplateBookDAO.java](https://github.com/Karina-Denisevich/JDBCTemplate-Example/blob/master/src/main/java/com/github/Karina_Denisevich/spring_template/example/book/dao/impl/SimpleJdbcTemplateBookDAO.java).<br />

SimpleJdbcTemplate supports named parameters like in NamedParameterJdbcTemplate, but JdbcTemplate only supports ? style parameters. If you go for JdbcTemplate then you need to assign parameters in the code you have to pass in arguments in an array and they get used in the order in which they appear in the array. And if you go for NamedParameterJdbcTemplate then you need to assign names to the parameter placeholders and pass in a map so the template can match the map names to the placeholders.
___

###Spring bean configuration

Create the Spring bean configuration files for bookDAO, bookSimpleDAO, bookNamedDAO and datasource.

[Spring-Book.xml](https://github.com/Karina-Denisevich/JDBCTemplate-Example/blob/master/src/main/resources/book/Spring-Book.xml).
```xml
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xsi:schemaLocation="http://www.springframework.org/schema/beans
http://www.springframework.org/schema/beans/spring-beans-2.5.xsd">

    <bean id="bookDAO" class="com.github.Karina_Denisevich.spring_template.example.book.dao.impl.JdbcTemplateBookDao">
        <property name="dataSource" ref="dataSource"/>
    </bean>

    <bean id="bookSimpleDAO"
          class="com.github.Karina_Denisevich.spring_template.example.book.dao.impl.SimpleJdbcTemplateBookDAO">
        <property name="dataSource" ref="dataSource"/>
    </bean>

    <bean id="bookNamedDAO"
          class="com.github.Karina_Denisevich.spring_template.example.book.dao.impl.NamedParameterJdbcTemplateDAO">
        <property name="dataSource" ref="dataSource"/>
    </bean>
```
</beans>

[Spring-DataSource.xml](https://github.com/Karina-Denisevich/JDBCTemplate-Example/blob/master/src/main/resources/database/Spring-DataSource.xml).
```xml
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xsi:schemaLocation="http://www.springframework.org/schema/beans
http://www.springframework.org/schema/beans/spring-beans-2.5.xsd">

    <bean id="dataSource" class="org.springframework.jdbc.datasource.DriverManagerDataSource">
        <property name="driverClassName" value="com.mysql.jdbc.Driver"/>
        <property name="url" value="jdbc:mysql://localhost:3306/bookJDBC"/>
        <property name="username" value="root"/>
        <property name="password" value="root"/>
    </bean>

</beans>
```

[Spring-Module.xml](https://github.com/Karina-Denisevich/JDBCTemplate-Example/blob/master/src/main/resources/Spring-Module.xml).
```xml
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xsi:schemaLocation="http://www.springframework.org/schema/beans
http://www.springframework.org/schema/beans/spring-beans-2.5.xsd">

    <import resource="database/Spring-DataSource.xml"/>
    <import resource="book/Spring-Book.xml"/>
```
</beans>
___

###Run project

Run JdbcTemplateApp 
[JDBCTemplateApp.java](https://github.com/Karina-Denisevich/JDBCTemplate-Example/blob/master/src/main/java/com/github/Karina_Denisevich/spring_template/example/common/JDBCTemplateApp.java).

```java
public class JdbcTemplateApp {

    public static void main(String[] args) {

        ApplicationContext context =
                new ClassPathXmlApplicationContext("Spring-Module.xml");

        BookDAO bookDAO = (BookDAO) context.getBean("bookDAO");
        Book book1 = new Book(1, "aaa", 111);
        Book book2 = new Book(2, "bbb", 222);
        Book book3 = new Book(3, "ccc", 333);

        List<Book> books = new ArrayList<>();
        books.add(book1);
        books.add(book2);
        books.add(book3);

        // bookDAO.insertBatch(books);

        bookDAO.delete(book1);

        System.out.println("Book with id =3 : " + bookDAO.getById(3));

        for (Object o : bookDAO.getAll()) {
            System.out.println(o.toString());
        }

    }
}
```
<br />

Run NamedParameterJdbcTemplate [NamedParameterJdbcTemplate.java](https://github.com/Karina-Denisevich/JDBCTemplate-Example/blob/master/src/main/java/com/github/Karina_Denisevich/spring_template/example/common/NamedParameterJdbcTemplate.java).
```java
public class NamedParameterJdbcTemplate {

    public static void main(String[] args) {

        ApplicationContext context = new ClassPathXmlApplicationContext("Spring-Module.xml");

        BookDAO bookNamedDAO = (BookDAO) context.getBean("bookNamedDAO");

        Book book1 = new Book(1, "aaa", 111);
        Book book2 = new Book(2, "bbb", 222);
        Book book3 = new Book(3, "ccc", 333);

        bookNamedDAO.insert(book1);
        bookNamedDAO.insert(book2);
        bookNamedDAO.insert(book3);

        bookNamedDAO.delete(book1);

        System.out.println("Book with id = 3 : " + bookNamedDAO.getById(3));

        for (Object o : bookNamedDAO.getAll()) {
            System.out.println(o.toString());
        }
    }
}
```
<br />

Run SimpleJdbcTemplate [SimpleJDBCTemplateApp.java](https://github.com/Karina-Denisevich/JDBCTemplate-Example/blob/master/src/main/java/com/github/Karina_Denisevich/spring_template/example/common/SimpleJDBCTemplateApp.java).
```java
public class SimpleJdbcTemplateApp {

    public static void main(String[] args) {

        ApplicationContext context = new ClassPathXmlApplicationContext("Spring-Module.xml");

        BookDAO bookSimpleDAO = (BookDAO) context.getBean("bookSimpleDAO");

        Book book1 = new Book(1, "aaa", 111);
        Book book2 = new Book(2, "bbb", 222);
        Book book3 = new Book(3, "ccc", 333);

        List<Book> books = new ArrayList<>();
        books.add(book1);
        books.add(book2);
        books.add(book3);

        // bookSimpleDAO.insertBatch(books);

        bookSimpleDAO.delete(book1);

        System.out.println("Book with id =3 : " + bookSimpleDAO.getById(3));

        for (Object o : bookSimpleDAO.getAll()) {
            System.out.println(o.toString());
        }
    }
}
```
___
