package com.demo.utils;

import cn.hutool.poi.excel.ExcelUtil;
import cn.hutool.poi.excel.ExcelWriter;
import com.demo.entity.TableView.BookInfo;
import com.demo.entity.TableView.BorrowInfo;
import com.demo.entity.TableView.OperationInfo;
import com.demo.entity.TableView.UserAllInfo;

import java.util.List;

public class ExcelExport {
    public static void exportBook(List<BookInfo> bookList) {
        // 通过工具类创建writer
        ExcelWriter writer = ExcelUtil.getWriter("d:/books.xlsx");
        // 合并单元格后的标题行，使用默认标题样式
        writer.merge(7, "图书信息表");
        // 一次性写出内容，使用默认样式
        writer.write(bookList);
        // 关闭writer，释放内存
        writer.close();
    }

    public static void exportBorrow(List<BorrowInfo> borrowInfos) {
        // 通过工具类创建writer
        ExcelWriter writer = ExcelUtil.getWriter("d:/borrows.xlsx");
        // 合并单元格后的标题行，使用默认标题样式
        writer.merge(8, "借阅信息表");
        // 一次性写出内容，使用默认样式
        writer.write(borrowInfos);
        // 关闭writer，释放内存
        writer.close();
    }

    public static void exportUser(List<UserAllInfo> userAllInfos) {
        // 通过工具类创建writer
        ExcelWriter writer = ExcelUtil.getWriter("d:/users.xlsx");
        // 合并单元格后的标题行，使用默认标题样式
        writer.merge(11, "用户信息表");
        // 一次性写出内容，使用默认样式
        writer.write(userAllInfos);
        // 关闭writer，释放内存
        writer.close();
    }

    public static void exportOperation(List<OperationInfo> operationInfos) {
        // 通过工具类创建writer
        ExcelWriter writer = ExcelUtil.getWriter("d:/operations.xlsx");
        // 合并单元格后的标题行，使用默认标题样式
        writer.merge(6, "操作日志信息表");
        // 一次性写出内容，使用默认样式
        writer.write(operationInfos);
        // 关闭writer，释放内存
        writer.close();
    }
}
