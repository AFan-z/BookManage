package com.demo.mapper;

import com.demo.entity.Borrow;
import com.demo.entity.BorrowAllInfoEntity;
import org.yu.myorm.core.dynproxy.SQL;
//import org.yu.myorm.core.dynproxy.SQL;

import java.util.Date;
import java.util.List;

public interface BorrowMapper {
    @SQL("SELECT user_id, job_num, book_id, book_num, book_name, is_return, return_time, renew_num" +
            " FROM borrow, user, book" +
            " WHERE borrow.user_id = user.id AND borrow.book_id = book.id")
    List<BorrowAllInfoEntity> select();

    @SQL("SELECT user_id, job_num, book_id, book_num, book_name, is_return, return_time, renew_num" +
            " FROM borrow, user, book" +
            " WHERE borrow.user_id = user.id AND borrow.book_id = book.id" +
            " AND job_num = ?")
    List<BorrowAllInfoEntity> select(String job_num);

    @SQL("SELECT * FROM borrow WHERE user_id = ? AND book_id = ? limit 1")
    Borrow select(int user_id, int book_id);

    @SQL("UPDATE borrow SET renew_time = ? WHERE user_id = ? AND book_id = ?")
    boolean update(Date renew_time, int user_id, int book_id);

    @SQL("INSERT INTO borrow (user_id, book_id, return_time, renew_time) VALUES (?,?,?,?)")
    boolean insert(int user_id, int book_id, Date return_time, Date renew_time);

    @SQL("UPDATE borrow SET is_return = ?, return_time = ?, renew_num = ? WHERE user_id = ? AND book_id = ?")
    boolean update(int is_return, Date return_time, int renew_num, int user_id, int book_id);

    @SQL("UPDATE borrow SET return_time = ?, renew_num = ? WHERE user_id = ? AND book_id = ?")
    boolean update(Date return_time, int renew_num, int user_id, int book_id);

    @SQL("UPDATE borrow SET is_return = ? WHERE user_id = ? AND book_id = ?")
    boolean update(int is_return, int user_id, int book_id);

    @SQL("DELETE FROM borrow WHERE user_id = ? AND book_id = ?")
    boolean delete(int user_id, int book_id);
}
