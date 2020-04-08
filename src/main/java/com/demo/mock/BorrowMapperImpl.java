package com.demo.mock;

import com.demo.entity.Borrow;
import com.demo.entity.BorrowAllInfoEntity;
import com.demo.mapper.BorrowMapper;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class BorrowMapperImpl implements BorrowMapper {
    private List<BorrowAllInfoEntity> borrows = new ArrayList<>();

    private BorrowAllInfoEntity entity1 = BorrowAllInfoEntity.builder().userId(1).bookId(1).jobNum("2020032301")
            .bookNum("9787115373557").bookName("数学之美(第二版)").returnTime(new Date()).isReturn(0).renewNum(0).build();
    private BorrowAllInfoEntity entity2 = BorrowAllInfoEntity.builder().userId(2).bookId(2).jobNum("2020032302")
            .bookNum("9787111603702").bookName("Java核心技术 卷I 基础知识（原书第11版）").returnTime(new Date()).isReturn(0).renewNum(0).build();
    private BorrowAllInfoEntity entity3 = BorrowAllInfoEntity.builder().userId(3).bookId(3).jobNum("2020032303")
            .bookNum("9787121198854").bookName("高性能MySQL（第3版）").returnTime(new Date()).isReturn(0).renewNum(0).build();

    {
        borrows.add(entity1);
        borrows.add(entity2);
        borrows.add(entity3);
    }

    @Override
    public List<BorrowAllInfoEntity> select() {
        return borrows;
    }


    @Override
    public List<BorrowAllInfoEntity> selectByBookNum(String jobNum, String book_num) {
        return null;
    }

    @Override
    public List<BorrowAllInfoEntity> selectByJobNum(String job_num) {
        List<BorrowAllInfoEntity> list = new ArrayList<>();
        for (BorrowAllInfoEntity entity : list){
            if (entity.getJobNum().equals(job_num)){
                list.add(entity);
            }
        }
        return list;
    }

    @Override
    public Borrow select(int user_id, int book_id) {
        return null;
    }

    @Override
    public boolean update(Date renew_time, int user_id, int book_id) {
        return false;
    }


    @Override
    public boolean insert(int user_id, int book_id, Date return_time, Date renew_time) {
        BorrowAllInfoEntity entity = BorrowAllInfoEntity.builder().userId(user_id).bookId(book_id).jobNum("2020032303")
                .bookNum("9787121198854").returnTime(new Date()).isReturn(0).renewNum(0).build();
        borrows.add(entity);
        return true;
    }

    @Override
    public boolean update(int is_return, Date return_time, int renew_num, int user_id, int book_id) {
        for (BorrowAllInfoEntity entity : borrows){
            if (entity.getUserId() == user_id && entity.getBookId() == book_id){
                entity.setIsReturn(is_return);
                entity.setReturnTime(return_time);
                entity.setRenewNum(renew_num);
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean update(Date return_time, int renew_num, int user_id, int book_id) {
        for (BorrowAllInfoEntity entity : borrows){
            if (entity.getUserId() == user_id && entity.getBookId() == book_id){
                entity.setReturnTime(return_time);
                entity.setRenewNum(renew_num);
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean update(int is_return, int user_id, int book_id) {
        for (BorrowAllInfoEntity entity : borrows){
            if (entity.getUserId() == user_id && entity.getBookId() == book_id){
                entity.setIsReturn(is_return);
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean delete(int user_id, int book_id) {
        return false;
    }
}
