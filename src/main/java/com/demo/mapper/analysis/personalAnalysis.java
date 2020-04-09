package com.demo.mapper.analysis;

import com.demo.entity.analysis.personalOpeAls;
import org.yu.myorm.core.dynproxy.SQL;

import java.util.List;

public interface personalAnalysis {

    @SQL("SELECT DATE_FORMAT(operation_time, \"%H\") AS hours, COUNT(id) AS num " +
            "FROM operation_log " +
            "WHERE TO_DAYS(operation_time) = TO_DAYS(NOW()) " +
            "AND operation_user = ? " +
            "GROUP BY DATE_FORMAT(operation_time, \"%H\");")
    List<personalOpeAls> select(int p_id);

}
