package com.github.Karina_Denisevich.spring_template.example.common;

import com.github.Karina_Denisevich.spring_template.example.book.dao.BookDAO;
import com.github.Karina_Denisevich.spring_template.example.book.model.Book;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

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
