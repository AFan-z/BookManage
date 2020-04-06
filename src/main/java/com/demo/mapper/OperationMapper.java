package com.demo.mapper;

import com.demo.entity.Operation;
import com.demo.entity.OperationAllEntity;
import org.yu.myorm.core.dynproxy.SQL;

import java.util.List;

public interface OperationMapper {

    @SQL("SELECT operation_log.id, operation_info, operation_user, job_num, userinfo.name, operation_time " +
            "FROM operation_log, user, userinfo " +
            "WHERE operation_log.operation_user = user.id AND user.userinfo_id = userinfo.id")
    List<OperationAllEntity> select();

    @SQL("SELECT operation_log.id, operation_info, operation_user, job_num, userinfo.name, operation_time " +
            "FROM operation_log, user, userinfo " +
            "WHERE operation_log.operation_user = user.id AND user.userinfo_id = userinfo.id " +
            "AND user.job_num = ?")
    List<OperationAllEntity> select(String job_num);

    @SQL("INSERT INTO operation_log() VALUES (?E)")
    boolean insert(Operation operation);
}
