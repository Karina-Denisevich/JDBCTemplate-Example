package com.github.Karina_Denisevich.spring_template.example.book.dao.impl;

import com.github.Karina_Denisevich.spring_template.example.book.dao.BookDAO;
import com.github.Karina_Denisevich.spring_template.example.book.model.Book;
import com.github.Karina_Denisevich.spring_template.example.book.model.BookRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.simple.ParameterizedBeanPropertyRowMapper;
import org.springframework.jdbc.core.simple.SimpleJdbcDaoSupport;

import java.util.ArrayList;
import java.util.List;

public class SimpleJdbcTemplateBookDAO extends SimpleJdbcDaoSupport implements BookDAO {

    @Override
    public void insert(Book book) {

        String sql = "INSERT INTO BOOK " +
                "(BOOK_ID, NAME, YEAR) VALUES (?, ?, ?)";

        getSimpleJdbcTemplate().update(sql, book.getBookId(),
                book.getName(), book.getYear()
        );
    }

    @Override
    public void insertBatch(List<Book> books) {

        String sql = "INSERT INTO BOOK " +
                "(BOOK_ID, NAME, YEAR) VALUES (?, ?, ?)";

        List<Object[]> parameters = new ArrayList<Object[]>();
        for (Book book : books) {
            parameters.add(new Object[]{book.getBookId(), book.getName(), book.getYear()});
        }
        getSimpleJdbcTemplate().batchUpdate(sql, parameters);
    }

    @Override
    public void delete(Book book) {

        String sql = "DELETE FROM BOOK WHERE BOOK_ID=:id";
        getSimpleJdbcTemplate().update(sql,
                new MapSqlParameterSource("id", book.getBookId()));
    }

    @Override
    public Book getById(Integer id) {

        String sql = "SELECT * FROM book WHERE BOOK_ID=?";
        return getSimpleJdbcTemplate().queryForObject(sql, ParameterizedBeanPropertyRowMapper.newInstance(Book.class), id);
    }

    @Override
    public List getAll() {

        String sql = "SELECT * FROM BOOK";

        List<Book> books =
                getSimpleJdbcTemplate().query(sql, ParameterizedBeanPropertyRowMapper.newInstance(Book.class));

        return books;
    }
}
