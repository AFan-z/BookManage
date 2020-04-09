package com.demo.mapper;

import com.demo.entity.Book;
import com.demo.entity.BookType;
import org.yu.myorm.core.dynproxy.SQL;
//import org.yu.myorm.core.dynproxy.SQL;

import java.util.List;

public interface BookMapper {

    @SQL("SELECT book.id AS id, type_id, type_name, book_num, book_name, publishing_house, " +
            "publication_year, price, number FROM book, book_type" +
            " WHERE book.type_id = book_type.id")
    List<Book> select();

    @SQL("SELECT book.id AS id, type_id, type_name, book_num, book_name, publishing_house, " +
            "publication_year, price, number " +
            "FROM book, book_type WHERE book.type_id = book_type.id " +
            "AND book.id = ? limit 1")
    Book select(int id);

    @SQL("SELECT book.id AS id, type_id, type_name, book_num, book_name, publishing_house, " +
            "publication_year, price, number " +
            "FROM book, book_type WHERE book.type_id = book_type.id" +
            " AND book_num like ?")
    List<Book> select(String book_num);


    @SQL("INSERT INTO book(type_id, book_num, book_name, publishing_house, publication_year, price, number) VALUES (?,?,?,?,?,?,?)")
    boolean insert(int type_id, String book_num, String book_name, String publishing_house, String publication_year, double price, int number);

    @SQL("DELETE FROM book WHERE id = ?")
    boolean delete(int id);

    @SQL("UPDATE book SET book_num = ?, book_name = ?, publishing_house = ?, publication_year = ?, price = ?, number = ?, type_id = ? " +
            " WHERE id = ?")
    boolean update(String book_num, String book_name, String publishing_house, String publication_year, double price, int number, int type_id, int id);

    @SQL("SELECT * FROM book_type")
    List<BookType> selectBookTypeList();
}
