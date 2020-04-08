package com.demo.service.Impl;

import com.demo.entity.Borrow;
import com.demo.entity.BorrowAllInfoEntity;
import com.demo.entity.Operation;
import com.demo.entity.TableView.BorrowInfo;
import com.demo.mapper.BookMapper;
import com.demo.mapper.BorrowMapper;
import com.demo.mapper.OperationMapper;
import com.demo.mapper.UserMapper;
import com.demo.service.BorrowService;
import com.demo.utils.*;
import com.demo.utils.enumeration.Operate;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import org.yaml.snakeyaml.error.YAMLException;
import org.yu.myorm.core.Exception.NoSuchDataInDBException;
import org.yu.myorm.core.handleErr;
//import org.yu.myorm.core.Exception.NoSuchDataInDBException;
//import org.yu.myorm.core.handleErr;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class BorrowServiceImpl implements BorrowService {

    private static ObservableList<BorrowInfo> borrowDataInfo;
    private static TableView<BorrowInfo> borrowTableInfo;
    private static BorrowInfo borrowInfo;
    private BorrowMapper borrowMapper = MapperFactory.getBorrowMapperInstance();
    private UserMapper userMapper = MapperFactory.getUserMapperInstance();
    private BookMapper bookMapper = MapperFactory.getBookMapperInstance();
    private OperationMapper operationMapper = MapperFactory.getOperationMapperInstance();


    @Override
    public void addButtonToTableView(String text, String theme, TableColumn<BorrowInfo, BorrowInfo> col,
                                     Operate operate, ObservableList<BorrowInfo> borrowData, TableView<BorrowInfo> borrowTable) {

        //操作列的相关设置
        col.setCellValueFactory(param -> new ReadOnlyObjectWrapper<>(param.getValue()));

        col.setCellFactory(param -> new TableCell<BorrowInfo, BorrowInfo>() {
            //通过ComponentUtil工具类的静态方法，传入按钮文字和样式，获得一个按钮对象
            private final Button editButton = ComponentUtil.getButton(text, theme);

            @Override
            protected void updateItem(BorrowInfo borrow, boolean empty) {
                super.updateItem(borrow, empty);
                if (borrow == null) {
                    setGraphic(null);
                    return;
                }
                setGraphic(editButton);
                editButton.setOnAction(event ->{
                    borrowInfo = borrow;
                    switch (operate){
                        case ADD://增
                            break;
                        case DELETE:// TODO 删
                            deleteBorrow();
                            borrowData.remove(borrow);
                            break;
                        case UPDATE://改
                            try {
                                borrowDataInfo = borrowData;
                                borrowTableInfo = borrowTable;
                                newBorrowStage(ResourcesConfig.EDIT_BORROW_FXML);
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                            break;
                        case SELECT://查
                            break;
                        case RENEW://续借
                            try {
                                renewBook();
                                borrowTable.getItems().removeAll(borrowData);
                                borrowData.addAll(getBorrowList());
                                borrowTable.setItems(borrowData);
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                            break;
                        case PERSONAL_RENEW: //个人续借
                            try {
                                renewBook();
                                borrowTable.getItems().removeAll(borrowData);
                                borrowData.addAll(getBorrowPersonList());
                                borrowTable.setItems(borrowData);
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                            break;
                        case RETURN://归还
                            try {
                                borrowMapper.update(1,borrowInfo.getUser_id(),borrowInfo.getBook_id());
//                                borrowData.remove(borrow);
//                                borrow.setIsReturn("是");
//                                borrowData.add(borrow);
                                borrowTable.getItems().removeAll(borrowData);
                                borrowData.addAll(getBorrowList());
                                borrowTable.setItems(borrowData);
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                            break;
                    }
                });
            }
        });
    }

    private boolean renewBook() throws ParseException {
        boolean flag = false;
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = format.parse(borrowInfo.getReturn_time());
        try {
            Borrow entity = borrowMapper.select(borrowInfo.getUser_id(), borrowInfo.getBook_id());
            if (1 == entity.getIsReturn()){
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("提示");
                alert.setHeaderText("续借失败！");
                alert.setContentText("图书已归还，无法续借！！！");
                alert.showAndWait();
                return false;
            }
            if ((new Date().before(entity.getRenewTime()))){
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("提示");
                alert.setHeaderText("续借失败！");
                alert.setContentText("只能在归还时间前 10 天内续借！！！");
                alert.showAndWait();
                return false;
            }
            boolean b1 = borrowMapper.update(getnewDateForDays(entity.getReturnTime(), 20),borrowInfo.getUser_id(),borrowInfo.getBook_id());
            boolean b = borrowMapper.update(getnewDateForDays(date, 30), borrowInfo.getRenew_num() + 1, borrowInfo.getUser_id(), borrowInfo.getBook_id());

            //操作日志
            Operation operation = Operation.builder()
                    .operationInfo("续借图书，图书编号为：" + borrowInfo.getBook_num() )
                    .operationTime(new Date())
                    .operationUser(CurrentUser.getUserAllInfo().getId())
                    .build();
            operationMapper.insert(operation);

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("提示");
            alert.setHeaderText("续借成功！");
            alert.setContentText("请在： " + format.format(getnewDateForDays(date, 30)) + " 前归还图书！！！");
            alert.showAndWait();
            flag = b && b1;

        } catch (NoSuchDataInDBException dbe) {
            handleErr.printErr(dbe, dbe.getMessage(), false);
        } catch (Exception e3) {
            handleErr.printErr(e3, "EXCEPTION!!!", true);
        }
        return flag;
    }

    @Override
    public List<BorrowInfo> getBorrowList() {

        List<BorrowInfo> borrowInfoList = new ArrayList<>();

        try {
            List<BorrowAllInfoEntity> borrowAllInfoEntities = borrowMapper.select();

            for (BorrowAllInfoEntity entity : borrowAllInfoEntities){
                BorrowInfo borrowInfo = new BorrowInfo(entity);
                borrowInfoList.add(borrowInfo);
            }
        } catch (NoSuchDataInDBException dbe) {
            handleErr.printErr(dbe, dbe.getMessage(), false);
        } catch (Exception e3) {
            handleErr.printErr(e3, "EXCEPTION!!!", true);
        }

        return borrowInfoList;
    }

    @Override
    public List<BorrowInfo> getBorrowPersonList() throws ParseException {

        List<BorrowInfo> borrowInfoList = new ArrayList<>();
        try {
            List<BorrowAllInfoEntity> borrowAllInfoEntities = borrowMapper.selectByJobNum(CurrentUser.getUserAllInfo().getJob_num());

            for (BorrowAllInfoEntity entity : borrowAllInfoEntities){
                BorrowInfo borrowInfo = new BorrowInfo(entity);
                borrowInfoList.add(borrowInfo);
            }
        } catch (NoSuchDataInDBException dbe) {
            handleErr.printErr(dbe, dbe.getMessage(), false);
        } catch (Exception e3) {
            handleErr.printErr(e3, "EXCEPTION!!!", true);
        }

        return borrowInfoList;
    }

    @Override
    public List<BorrowInfo> selectBorrowByJobNum(String jobNum) {
        List<BorrowInfo> borrowInfoList = new ArrayList<>();
        try {
            List<BorrowAllInfoEntity> borrowAllInfoEntities = borrowMapper.selectByJobNum(jobNum);

            for (BorrowAllInfoEntity entity : borrowAllInfoEntities){
                BorrowInfo borrowInfo = new BorrowInfo(entity);
                borrowInfoList.add(borrowInfo);
            }
        } catch (NoSuchDataInDBException dbe) {
            handleErr.printErr(dbe, dbe.getMessage(), false);
        } catch (Exception e3) {
            handleErr.printErr(e3, "EXCEPTION!!!", true);
        }
        return borrowInfoList;
    }

    @Override
    public List<BorrowInfo> selectBorrowByBookNum(String bookNum) {
        List<BorrowInfo> borrowInfoList = new ArrayList<>();
        try {
            List<BorrowAllInfoEntity> borrowAllInfoEntities = borrowMapper.selectByBookNum(bookNum);

            for (BorrowAllInfoEntity entity : borrowAllInfoEntities){
                BorrowInfo borrowInfo = new BorrowInfo(entity);
                borrowInfoList.add(borrowInfo);
            }
        } catch (NoSuchDataInDBException dbe) {
            handleErr.printErr(dbe, dbe.getMessage(), false);
        } catch (Exception e3) {
            handleErr.printErr(e3, "EXCEPTION!!!", true);
        }
        return borrowInfoList;
    }

    @Override
    public void newBorrowStage(String fxml) throws Exception {
        Stage stage = new Stage();
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(fxml));
        AnchorPane root = fxmlLoader.load();
        Scene scene = new Scene(root);
        scene.getStylesheets().add(ResourcesConfig.CSS_STYLE);
        stage.setTitle(ResourcesConfig.BOOK_MANAGE_TITLE);
        stage.getIcons().add(new Image(ResourcesConfig.IMG_LOGO));
        //界面大小不可变
        stage.setResizable(false);
        stage.setScene(scene);
        stage.show();
    }

    @Override
    public void newBorrowStage(String fxml, ObservableList<BorrowInfo> borrowData, TableView<BorrowInfo> borrowTable) throws Exception {
        borrowDataInfo = borrowData;
        borrowTableInfo = borrowTable;
        newBorrowStage(fxml);
    }

    @Override
    public boolean addBorrow(int userId, int bookId) throws ParseException {
        boolean flag = false;
        if (userId == 0 || bookId == 0){
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("提示信息");
            alert.setHeaderText("请补全信息！！！");
            alert.showAndWait();
            return flag;
        }

        try {
            boolean b = borrowMapper.insert(userId, bookId, getnewDateForDays(new Date(), 30), getnewDateForDays(new Date(), 20));

            if (b){
                //操作日志
                Operation operation = Operation.builder()
                        .operationInfo("添加借阅信息，用户ID号："+ userId + ",图书ID号为：" + bookId)
                        .operationTime(new Date())
                        .operationUser(CurrentUser.getUserAllInfo().getId())
                        .build();
                operationMapper.insert(operation);
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("提示");
                alert.setHeaderText("已添加！");
                alert.setContentText("添加借阅信息成功！！！");
                alert.showAndWait();
                //刷新数据
                borrowTableInfo.getItems().removeAll(borrowDataInfo);
                borrowDataInfo.addAll(getBorrowList());
                borrowTableInfo.setItems(borrowDataInfo);
            } else {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("提示");
                alert.setHeaderText("添加失败！");
                alert.setContentText("添加借阅信息失败！！！");
                alert.showAndWait();
            }
            flag = b;
        } catch (NoSuchDataInDBException dbe) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("提示");
            alert.setHeaderText("添加失败！");
            alert.setContentText("借阅信息已存在，请更改！！！");
            alert.showAndWait();
            handleErr.printErr(dbe, dbe.getMessage(), false);
        } catch (Exception e3) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("提示");
            alert.setHeaderText("添加失败！");
            alert.setContentText("借阅信息已存在，请更改！！！");
            alert.showAndWait();
            handleErr.printErr(e3, "EXCEPTION!!!", true);
        }

        return flag;
    }

    @Override
    public boolean exitBorrow(ComboBox<String> isReturn, DatePicker returnTime, TextField renewNum) throws ParseException {
        boolean flag = false;

        if (isReturn == null){
            isReturn.setValue(borrowInfo.getIsReturn());
        }
        if (returnTime.getValue() == null){
            String dataStr[] = borrowInfo.getReturn_time().split(" ");
            returnTime.setValue(LocalDate.parse(dataStr[0]));
        }
        if (renewNum.getText().equals("")){
            renewNum.setText(borrowInfo.getRenew_num()+"");
        }

        int is_return = 0;
        if (isReturn.getValue() == null){
            isReturn.setValue(borrowInfo.getIsReturn());
        }
        if (isReturn.getValue().equals("是")){
            is_return = 1;
        }

        try {
            boolean b = borrowMapper.update(is_return, editDate(returnTime.getValue().toString()), Integer.parseInt(renewNum.getText()), borrowInfo.getUser_id(), borrowInfo.getBook_id());
            if (b){
                //操作日志
                Operation operation = Operation.builder()
                        .operationInfo("修改借阅信息，用户工号："+ borrowInfo.getJob_num() + "图书编号为：" + borrowInfo.getBook_num() )
                        .operationTime(new Date())
                        .operationUser(CurrentUser.getUserAllInfo().getId())
                        .build();
                operationMapper.insert(operation);
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("提示");
                alert.setHeaderText("已修改！");
                alert.setContentText("修改借阅信息成功！！！");
                alert.showAndWait();
                Stage stage = (Stage) isReturn.getScene().getWindow();
                stage.close();
                //刷新数据
                borrowTableInfo.getItems().removeAll(borrowDataInfo);
                borrowDataInfo.addAll(getBorrowList());
                borrowTableInfo.setItems(borrowDataInfo);
            } else {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("提示");
                alert.setHeaderText("修改失败！");
                alert.setContentText("修改借阅信息失败！！！");
                alert.showAndWait();
                Stage stage = (Stage) isReturn.getScene().getWindow();
                stage.close();
            }
            flag = b;
        } catch (NoSuchDataInDBException dbe) {
            handleErr.printErr(dbe, dbe.getMessage(), false);
        } catch (Exception e3) {
            handleErr.printErr(e3, "EXCEPTION!!!", true);
        }

        return flag;
    }

    @Override
    public boolean deleteBorrow() {
        boolean flag = false;
        try {
            boolean b = borrowMapper.delete(borrowInfo.getUser_id(), borrowInfo.getBook_id());
            if (b) {
                //操作日志
                Operation operation = Operation.builder()
                        .operationInfo("删除借阅信息，用户工号："+ borrowInfo.getJob_num() + "图书编号为：" + borrowInfo.getBook_num() )
                        .operationTime(new Date())
                        .operationUser(CurrentUser.getUserAllInfo().getId())
                        .build();
                operationMapper.insert(operation);
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("提示信息");
                alert.setHeaderText("已删除！");
                alert.setHeaderText("删除图书成功!!!");
                alert.showAndWait();
            }else {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("提示信息");
                alert.setHeaderText("删除失败！");
                alert.setHeaderText("删除图书失败!!!");
                alert.showAndWait();
            }
            flag = b;
        } catch (NoSuchDataInDBException dbe) {
            handleErr.printErr(dbe, dbe.getMessage(), false);
        } catch (Exception e3) {
            handleErr.printErr(e3, "EXCEPTION!!!", true);
        }
        return flag;
    }

    private Date editDate(String date) throws ParseException {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String dateStr1[] = borrowInfo.getReturn_time().split("-");
        String dateStr2[] = dateStr1[2].split(" ");
        String dateStr3[] = date.split("-");
        String newdate = dateStr3[0]+"-"+dateStr3[1]+"-"+dateStr3[2] + " " + dateStr2[1];
        Date newDate = format.parse(newdate);
        return newDate;
    }

//    private Date getnewDateForMonths(Date olddate, int months) throws ParseException {
//
//        Date date = olddate;
//        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//        String data = format.format(date);
//        String dataStr[] = data.split("-");
//
//        if ((Integer.parseInt(dataStr[1]) + months) > 12){
//            dataStr[0]= String.valueOf(Integer.parseInt(dataStr[0]) + 1);
//            dataStr[1]= String.valueOf((Integer.parseInt(dataStr[1]) + months) / 12);;
//            String newdata = dataStr[0]+"-"+dataStr[1]+"-"+dataStr[2];
//            Date newDate = format.parse(newdata);
//            return newDate;
//        }
//
//        dataStr[1]= String.valueOf(Integer.parseInt(dataStr[1]) + months);;
//        String newdata = dataStr[0]+"-"+dataStr[1]+"-"+dataStr[2];
//        Date newDate = format.parse(newdata);
//        return newDate;
//    }


    private Date getnewDateForDays(Date olddate, int days) throws ParseException {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Calendar ca = Calendar.getInstance();
        ca.setTime(olddate);
        ca.add(Calendar.DATE, days);
        olddate = ca.getTime();
        String newdata = format.format(olddate);
        Date newDate = format.parse(newdata);
        return newDate;
    }
}
