package com.demo.entity;

import lombok.Builder;
import lombok.Data;
import lombok.experimental.Tolerate;

import java.util.Date;

@Data
@Builder
public class OperationAllEntity {

    /**
     * 主键
     */
    private int id;

    /**
     * 操作内容
     */
    private String operationInfo;

    /**
     * 操作人ID
     */
    private int operationUser;

    /**
     * 操作时间
     */
    private Date operationTime;

    /**
     * 操作人工号
     */
    private String jobNum;

    /**
     * 操作人名
     */
    private String name;

    @Tolerate
    public OperationAllEntity(){}
}
