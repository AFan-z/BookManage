package com.demo.mapper.analysis;

import com.demo.entity.analysis.personalBorrowAls;
import com.demo.entity.analysis.personalOpeAls;
import com.demo.entity.analysis.pubHouseAls;
import com.demo.entity.analysis.typeBookAls;
import org.yu.myorm.core.dynproxy.SQL;

import java.util.List;

public interface personalAnalysis {

    @SQL("SELECT DATE_FORMAT(operation_time, \"%H\") AS hours, COUNT(id) AS num " +
            "FROM operation_log " +
            "WHERE TO_DAYS(operation_time) = TO_DAYS(NOW()) " +
            "AND operation_user = ? " +
            "GROUP BY DATE_FORMAT(operation_time, \"%H\");")
    List<personalOpeAls> select(int p_id);


    @SQL("SELECT book_type.type_name, count(book.id) as count" +
            "    FROM book_type, book, borrow" +
            "    WHERE book_type.id = book.type_id and borrow.book_id = book.id and user_id= ? " +
            "    group by book_type.type_name")
    List<personalBorrowAls> selectBorrowType(int userId);



    @SQL("SELECT publishing_house, count(id) as count" +
            "    FROM book, borrow WHERE book.id = borrow.book_id and user_id= ? " +
            "    group by publishing_house")
    List<pubHouseAls> selectPub(int userId);

    @SQL("SELECT type_name, count(book.id) as count" +
            "    FROM book, book_type, borrow" +
            "    WHERE type_id = book_type.id AND book.id = borrow.book_id AND user_id= ? " +
            "    group by type_name")
    List<typeBookAls> selectTypePie(int userId);

}
