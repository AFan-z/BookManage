package com.demo.entity.TableView;

import com.demo.entity.OperationAllEntity;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

import java.text.SimpleDateFormat;

public class OperationInfo {
    /**
     * 主键
     */
    private SimpleIntegerProperty id = new SimpleIntegerProperty();

    /**
     * 操作内容
     */
    private SimpleStringProperty operationInfo = new SimpleStringProperty("");

    /**
     * 操作人ID
     */
    private SimpleIntegerProperty operationUser = new SimpleIntegerProperty();

    /**
     * 操作时间
     */
    private SimpleStringProperty operationTime = new SimpleStringProperty("");

    /**
     * 操作人工号
     */
    private SimpleStringProperty jobNum = new SimpleStringProperty("");

    /**
     * 操作人名
     */
    private SimpleStringProperty name = new SimpleStringProperty("");

    public OperationInfo(OperationAllEntity entity){
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        setId(entity.getId());
        setJobNum(entity.getJobNum());
        setName(entity.getName());
        setOperationInfo(entity.getOperationInfo());
        setOperationUser(entity.getOperationUser());
        if (entity.getOperationTime() != null){
            setOperationTime(dateFormat.format(entity.getOperationTime()));
        }

    }

    public OperationInfo(){}


    public int getId() {
        return id.get();
    }

    public SimpleIntegerProperty idProperty() {
        return id;
    }

    public void setId(int id) {
        this.id.set(id);
    }

    public String getOperationInfo() {
        return operationInfo.get();
    }

    public SimpleStringProperty operationInfoProperty() {
        return operationInfo;
    }

    public void setOperationInfo(String operationInfo) {
        this.operationInfo.set(operationInfo);
    }

    public int getOperationUser() {
        return operationUser.get();
    }

    public SimpleIntegerProperty operationUserProperty() {
        return operationUser;
    }

    public void setOperationUser(int operationUser) {
        this.operationUser.set(operationUser);
    }

    public String getOperationTime() {
        return operationTime.get();
    }

    public SimpleStringProperty operationTimeProperty() {
        return operationTime;
    }

    public void setOperationTime(String operationTime) {
        this.operationTime.set(operationTime);
    }

    public String getJobNum() {
        return jobNum.get();
    }

    public SimpleStringProperty jobNumProperty() {
        return jobNum;
    }

    public void setJobNum(String jobNum) {
        this.jobNum.set(jobNum);
    }

    public String getName() {
        return name.get();
    }

    public SimpleStringProperty nameProperty() {
        return name;
    }

    public void setName(String name) {
        this.name.set(name);
    }
}
