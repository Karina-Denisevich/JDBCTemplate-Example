package com.github.Karina_Denisevich.spring_template.example.book.model;

public class Book {

    long bookId;
    String name;
    int year;

    public Book() {
    }

    public Book(long bookId, String name, int year) {
        this.bookId = bookId;
        this.name = name;
        this.year = year;
    }

    public long getBookId() {
        return bookId;
    }

    public void setBookId(long bookId) {
        this.bookId = bookId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    @Override
    public String toString() {
        return "Book{" +
                "bookId=" + bookId +
                ", name='" + name + '\'' +
                ", year=" + year +
                '}';
    }
}
