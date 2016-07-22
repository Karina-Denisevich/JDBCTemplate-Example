package com.github.Karina_Denisevich.spring_template.example.book.dao;

import com.github.Karina_Denisevich.spring_template.example.book.model.Book;

import java.util.List;

public interface BookDAO {

    void insert(Book book);

    void insertBatch(List<Book> books);

    void delete(Book book);

    Book getById(Integer id);

    List getAll();
}
