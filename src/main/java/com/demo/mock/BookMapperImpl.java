package com.demo.mock;

import com.demo.entity.Book;
import com.demo.mapper.BookMapper;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class BookMapperImpl implements BookMapper {

    private List<Book> books = new ArrayList<>();

    private Book book1 = Book.builder().id(1).bookNum("9787115373557").bookName("数学之美(第二版)")
            .publishingHouse("人民邮电出版社").publicationYear("2014").price(24.5).number(20).build();
    private Book book2 = Book.builder().id(2).bookNum("9787111603702").bookName("Java核心技术 卷I 基础知识（原书第11版）")
            .publishingHouse("机械工业出版社").publicationYear("2019").price(96.9).number(20).build();
    private Book book3 = Book.builder().id(3).bookNum("9787121198854").bookName("高性能MySQL（第3版）")
            .publishingHouse("电子工业出版社").publicationYear("2013").price(83.2).number(20).build();

    {
        books.add(book1);
        books.add(book2);
        books.add(book3);
    }
    @Override
    public List<Book> select() {
        return books;
    }

    @Override
    public List<Book> select(String book_num) {
/*        for (Book book : books){
            if (book.getBookNum().equals(book_num)){
                return book.getId();
            }
        }
        return 0;*/
        return null;
    }

    @Override
    public Book select(int id) {
        return null;
    }

    @Override
    public boolean insert(Book Entity) {

        //自增id
        Entity.setId(books.get(books.size()-1).getId() + 1);

        books.add(Entity);
        return true;
    }

    @Override
    public boolean delete(int id) {
        Iterator<Book> it = books.iterator();

        while (it.hasNext()){
            if (it.next().getId() == id){
                it.remove();
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean update(String book_num, String book_name, String publishing_house, String publication_year, double price, int number, int id) {
        for (Book book : books){
            if (book.getId() == id){
                book.setBookNum(book_num);
                book.setBookName(book_name);
                book.setPublishingHouse(publishing_house);
                book.setPublicationYear(publication_year);
                book.setPrice(price);
                book.setNumber(number);
                return true;
            }
        }
        return false;
    }

}
