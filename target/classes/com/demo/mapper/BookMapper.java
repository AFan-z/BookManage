package com.demo.mapper;

import com.demo.entity.Book;

import java.util.List;

public interface BookMapper {

//    @SQL("SELECT * FROM book")
    List<Book> select();

//    @SQL("SELECT id FROM book WHERE book_num = ?")
    int select(String book_num);

//    @SQL("INSERT INTO book VALUES (?E)")
    boolean insert(Book Entity);

//    @SQL("DELETE FROM book WHERE id = ?")
    boolean delete(int id);

//    @SQL("UPDATE book SET book_num = ?, book_name = ?, publishing_house = ?, publication_year = ?, price = ?, number = ?" +
//            " WHERE id = ?")
    boolean update(String book_num, String book_name, String publishing_house, String publication_year, double price, int number, int id);

}
