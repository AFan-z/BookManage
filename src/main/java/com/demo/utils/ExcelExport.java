package com.demo.utils;

import cn.hutool.poi.excel.ExcelUtil;
import cn.hutool.poi.excel.ExcelWriter;


import java.util.List;

public class ExcelExport {
    public static void exportExcel(List<?> lists, String fileName) {
        // 通过工具类创建writer
        ExcelWriter writer = ExcelUtil.getWriter(fileName);
        writer.write(lists);
        // 关闭writer，释放内存
        writer.close();
    }
}
