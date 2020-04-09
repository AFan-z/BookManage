package com.demo.mapper.analysis;

import com.demo.entity.analysis.userNewAls;
import com.demo.entity.analysis.userPieAls;
import org.yu.myorm.core.dynproxy.SQL;

import java.util.List;

public interface userAnalysis {

    @SQL("SELECT b.role_name, year, coalesce(count,0) as count FROM" +
            " (SELECT role.role_name, YEAR(user.create_time) as year0, count(user.id) as count" +
            " FROM user, userinfo, role" +
            " WHERE userinfo.id = user.userinfo_id and role.id = role_id" +
            " group by role.role_name, year0" +
            " order by year0) a" +
            "     RIGHT JOIN (" +
            "            SELECT distinct role_name, YEAR(ua.create_time) as year FROM user ua, role, userinfo WHERE role.id = ua.role_id" +
            "      ) b" +
            "     ON a.role_name = b.role_name and year0=year" +
            "     order by year, role_name" )
    List<userNewAls> selectNewUser();


    @SQL("SELECT role.role_name, count(user.id) as count" +
            "    FROM role, user" +
            "    WHERE user.role_id = role.id" +
            "    group by role.role_name")
    List<userPieAls> selectPercent();

}
