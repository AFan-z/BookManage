package com.demo.service.Impl;

import com.demo.entity.Book;
import com.demo.entity.Borrow;
import com.demo.entity.TableView.BorrowInfo;
import com.demo.entity.User;
import com.demo.service.BorrowService;
import com.demo.utils.ComponentUtil;
import com.demo.utils.CurrentUser;
import com.demo.utils.Operate;
import com.demo.utils.ResourcesConfig;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class BorrowServiceImpl implements BorrowService {

    private static BorrowInfo borrowInfo;
    @Override
    public void addButtonToTableView(String text, String theme, TableColumn<BorrowInfo, BorrowInfo> col, Operate operate) {
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
                        case DELETE://删
                            Alert alert = new Alert(Alert.AlertType.INFORMATION);
                            alert.setTitle("提示");
                            alert.setContentText("！！！！");
                            alert.showAndWait();
                            break;
                        case UPDATE://改
                            try {
                                newBorrowStage(ResourcesConfig.EDIT_BORROW_FXML);
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                            break;
                        case SELECT://查
                            break;
                        case RENEW://续借
                            break;
                        case RETURN://归还
                            break;
                    }
                });
            }
        });
    }

    @Override
    public List<BorrowInfo> getBorrowList() throws ParseException {

        List<BorrowInfo> borrowInfoList = new ArrayList<>();
        //假数据
        User user = User.builder()
                .id(1)
                .job_num("2018102146")
                .password("123456")
                .create_time(new Date())
                .login_time(new Date())
                .last_login_time(new Date())
                .login_num(6)
                .userinfo_id(1)
                .build();
        Book book = new Book(1,"sa123456789","测试1","机械工业出版","2020",44.21,10);

        Borrow borrow = Borrow.builder().book_id(1).user_id(1).isReturn(0).renew_num(0).return_time(getnewDate(new Date(), 1)).build();

        BorrowInfo borrowInfo = new BorrowInfo(user,book,borrow);
        borrowInfoList.add(borrowInfo);
        borrowInfoList.add(borrowInfo);
        borrowInfoList.add(borrowInfo);
        borrowInfoList.add(borrowInfo);
        borrowInfoList.add(borrowInfo);
        borrowInfoList.add(borrowInfo);
        borrowInfoList.add(borrowInfo);
        borrowInfoList.add(borrowInfo);
        return borrowInfoList;
    }

    @Override
    public List<BorrowInfo> getBorrowPersonList() throws ParseException {

        List<BorrowInfo> borrowInfoList = new ArrayList<>();
        // TODO 加个判断工号是否为当前登录用户

        //获得当前工号
        //CurrentUser.getUserAllInfo().getJob_num();


        //假数据
        User user = User.builder()
                .id(1)
                .job_num("2018102146")
                .password("123456")
                .create_time(new Date())
                .login_time(new Date())
                .last_login_time(new Date())
                .login_num(6)
                .userinfo_id(1)
                .build();
        Book book = new Book(1,"sa123456789","测试1","机械工业出版","2020",44.21,10);

        Borrow borrow = Borrow.builder().book_id(1).user_id(1).isReturn(0).renew_num(0).return_time(getnewDate(new Date(), 1)).build();

        BorrowInfo borrowInfo = new BorrowInfo(user,book,borrow);
        borrowInfoList.add(borrowInfo);
        borrowInfoList.add(borrowInfo);

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
    public void addBorrow(TextField jobNum, TextField bookNum) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("提示");
        alert.setContentText(jobNum.getText() + "--" + bookNum.getText());
        alert.showAndWait();
    }

    @Override
    public void exitBorrow(ComboBox<String> isReturn, DatePicker returnTime, TextField renewNum) throws ParseException {
        System.out.println(borrowInfo.getReturn_time() + "---" + isReturn.getValue());

        //System.out.println(editDate(returnTime.getValue().toString()));

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("提示");
        alert.setContentText(editDate(returnTime.getValue().toString()).toString());
        alert.showAndWait();
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

    private Date getnewDate(Date olddate, int months) throws ParseException {

        Date date = olddate;
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String data = format.format(date);
        String dataStr[] = data.split("-");

        if ((Integer.parseInt(dataStr[1]) + months) > 12){
            dataStr[0]= String.valueOf(Integer.parseInt(dataStr[0]) + 1);
            dataStr[1]= String.valueOf((Integer.parseInt(dataStr[1]) + months) / 12);;
            String newdata = dataStr[0]+"-"+dataStr[1]+"-"+dataStr[2];
            Date newDate = format.parse(newdata);
            return newDate;
        }

        dataStr[1]= String.valueOf(Integer.parseInt(dataStr[1]) + months);;
        String newdata = dataStr[0]+"-"+dataStr[1]+"-"+dataStr[2];
        Date newDate = format.parse(newdata);
        return newDate;
    }
}
