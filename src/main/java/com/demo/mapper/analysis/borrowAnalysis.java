package com.demo.mapper.analysis;

import com.demo.entity.analysis.personalBorrowAls;
import com.demo.entity.analysis.pubHouseAls;
import com.demo.entity.analysis.typeBookAls;
import org.yu.myorm.core.dynproxy.SQL;

import java.util.List;

public interface borrowAnalysis {


    @SQL("SELECT book_type.type_name, count(book.id) as count" +
            "    FROM book_type, book, borrow" +
            "    WHERE book_type.id = book.type_id and borrow.book_id = book.id " +
            "    group by book_type.type_name")
    List<personalBorrowAls> selectBorrowType();


    @SQL("SELECT publishing_house, count(id) as count" +
            "    FROM book, borrow WHERE book.id = borrow.book_id " +
            "    group by publishing_house")
    List<pubHouseAls> selectPub();

    @SQL("SELECT type_name, count(book.id) as count" +
            "    FROM book, book_type, borrow" +
            "    WHERE type_id = book_type.id AND book.id = borrow.book_id " +
            "    group by type_name")
    List<typeBookAls> selectTypePie();
}
