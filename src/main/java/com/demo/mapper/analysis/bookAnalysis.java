package com.demo.mapper.analysis;

import com.demo.entity.analysis.pubYearAls;
import com.demo.entity.analysis.pubHouseAls;
import org.yu.myorm.core.dynproxy.SQL;

import java.util.List;

public interface bookAnalysis {

    @SQL("SELECT distinct publication_year, count(id) as count FROM book" +
            "    group by publication_year" +
            "    order by publication_year")
    List<pubYearAls> select();


    @SQL("SELECT publishing_house, count(id) as count" +
            "    FROM book" +
            "    group by publishing_house")
    List<pubHouseAls> selectPub();

}
