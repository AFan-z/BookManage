package com.demo.mapper.analysis;

import com.demo.entity.analysis.pubYearAls;
import com.demo.entity.analysis.pubHouseAls;
import com.demo.entity.analysis.typeBookAls;
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


    @SQL("SELECT type_name, count(book.id) as count" +
            "    FROM book, book_type" +
            "    WHERE type_id = book_type.id" +
            "    group by type_name")
    List<typeBookAls> selectTypePie();

}
