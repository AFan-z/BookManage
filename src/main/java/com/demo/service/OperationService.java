package com.demo.service;

import com.demo.entity.TableView.OperationInfo;

import java.util.List;

public interface OperationService {
    List<OperationInfo> getOperationList();

    List<OperationInfo> getOperationListByJobNum(String jobNum);

    boolean addOperationInfo();
}
