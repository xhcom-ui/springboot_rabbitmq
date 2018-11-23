package com.citydo.springboot_amqp.bean;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

public class Book {

    private String bookName;
    private String author;

    //无参构造
    public Book() {
    }


    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public String getAuthor() {
        return author;
    }
    //有参构造
    public Book(String bookName, String author) {
        this.bookName = bookName;
        this.author = author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    @Override
    public String toString() {
        return "Book{" +
                "bookName='" + bookName + '\'' +
                ", author='" + author + '\'' +
                '}';
    }
}
