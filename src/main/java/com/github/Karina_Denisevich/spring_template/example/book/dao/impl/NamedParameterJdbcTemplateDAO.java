package com.github.Karina_Denisevich.spring_template.example.book.dao.impl;

import com.github.Karina_Denisevich.spring_template.example.book.dao.BookDAO;
import com.github.Karina_Denisevich.spring_template.example.book.model.Book;
import com.github.Karina_Denisevich.spring_template.example.book.model.BookRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcDaoSupport;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class NamedParameterJdbcTemplateDAO extends NamedParameterJdbcDaoSupport implements BookDAO {

    @Override
    public void insert(Book book) {

        String sql = "INSERT INTO BOOK " +
                "(BOOK_ID, NAME, YEAR) VALUES (:book_id, :name, :year)";

        Map parameters = new HashMap();
        parameters.put("book_id", book.getBookId());
        parameters.put("name", book.getName());
        parameters.put("year", book.getYear());
        getNamedParameterJdbcTemplate().update(sql, parameters);
    }

    @Override
    public void insertBatch(List<Book> books) {

        //not supported batchUpdate
    }

    @Override
    public void delete(Book book) {

        String SQL = "DELETE FROM BOOK WHERE BOOK_ID = :id";
        SqlParameterSource namedParameters = new MapSqlParameterSource("id", book.getBookId());
        getNamedParameterJdbcTemplate().update(SQL, namedParameters);
    }

    @Override
    public Book getById(Integer id) {

        String sql = "SELECT * FROM BOOK WHERE BOOK_ID = :id";
        SqlParameterSource namedParameters = new MapSqlParameterSource("id", id);
        Book book = (Book) getNamedParameterJdbcTemplate().queryForObject(sql, namedParameters, new BookRowMapper());
        return book;
    }

    @Override
    public List getAll() {

        String sql = "SELECT * FROM BOOK";
        Map parameters = new HashMap();

        List<Book> books = getNamedParameterJdbcTemplate().query(sql, parameters, new BookRowMapper());
        return books;
    }
}
