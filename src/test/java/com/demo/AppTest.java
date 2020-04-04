package com.demo;

import com.demo.entity.Book;
import org.junit.Test;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import static org.junit.Assert.assertTrue;

/**
 * Unit test for simple App.
 */
public class AppTest {

    private List<Book> books = new ArrayList<>();

    private  Book book1 = Book.builder().id(1).bookNum("9787115373557").bookName("数学之美(第二版)")
            .publishingHouse("人民邮电出版社").publicationYear("2014").price(24.5).number(20).build();
    private  Book book2 = Book.builder().id(2).bookNum("9787111603702").bookName("Java核心技术 卷I 基础知识（原书第11版）")
            .publishingHouse("机械工业出版社").publicationYear("2019").price(96.9).number(20).build();
    private  Book book3 = Book.builder().id(3).bookNum("9787121198854").bookName("高性能MySQL（第3版）")
            .publishingHouse("电子工业出版社").publicationYear("2013").price(83.2).number(20).build();

    {
        books.add(book1);
        books.add(book2);
        books.add(book3);
    }

    /**
     * Rigorous Test :-)
     */
    @Test
    public void shouldAnswerWithTrue() {
        System.out.println(books.get(0).hashCode());

        Iterator<Book> it = books.iterator();
        System.out.println(books);
        while (it.hasNext()){
            if (it.next().getId() == 1){
                it.remove();
            }
        }
//        for (Book book : books){
//            if (book.getId() == 1){
//                books.remove(book);
//            }
//        }

        System.out.println(books);
        System.out.println(LocalDate.parse("2014-02-28"));
    }
}
