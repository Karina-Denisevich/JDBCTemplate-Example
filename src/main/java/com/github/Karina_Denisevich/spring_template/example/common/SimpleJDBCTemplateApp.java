package com.github.Karina_Denisevich.spring_template.example.common;

import com.github.Karina_Denisevich.spring_template.example.book.dao.BookDAO;
import com.github.Karina_Denisevich.spring_template.example.book.model.Book;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.ArrayList;
import java.util.List;

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
