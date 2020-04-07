package com.demo.service.Impl;

import com.demo.entity.RoleAndPermission;
import com.demo.entity.TableView.RoleAndPermissionInfo;
import com.demo.mapper.RoleAndPermissionMapper;
import com.demo.service.MainService;
import com.demo.utils.CurrentUser;
import com.demo.utils.MapperFactory;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Accordion;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.TitledPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Circle;
import org.yu.myorm.core.Exception.NoSuchDataInDBException;
import org.yu.myorm.core.handleErr;

import java.util.ArrayList;
import java.util.List;

public class MainServiceImpl implements MainService {
    private RoleAndPermissionMapper roleAndPermissionMapper = MapperFactory.getRoleAndPermissionMapperInstance();

    @Override
    public void setAvatar(ImageView imageView, Hyperlink hyperlink) {

        Image image = new Image(CurrentUser.getUserAllInfo().getAvatar());

        imageView.setImage(image);
        //将头像显示为圆形
        Circle circle = new Circle();
        circle.setCenterX(20.0);
        circle.setCenterY(20.0);
        circle.setRadius(20.0);
        imageView.setClip(circle);

        //显示管理员姓名
        hyperlink.setText(CurrentUser.getUserAllInfo().getName());
    }

    @Override
    public void switchView(String fileName, Pane pane) throws Exception {
        //清空原有内容
        pane.getChildren().clear();
        AnchorPane anchorPane = new FXMLLoader(getClass().getResource(fileName)).load();
        pane.getChildren().add(anchorPane);
    }

    @Override
    public void switchFunctionView(Accordion functionPane) throws Exception {
        try {
            List<RoleAndPermission> entities = roleAndPermissionMapper.select(CurrentUser.getUserAllInfo().getRole_id());
            for (RoleAndPermission entity : entities){
                TitledPane titledPane = new FXMLLoader(getClass().getResource(entity.getPermissionInfo())).load();
                functionPane.getPanes().add(titledPane);
            }
        } catch (NoSuchDataInDBException dbe) {
            handleErr.printErr(dbe, dbe.getMessage(), false);
        } catch (Exception e3) {
            handleErr.printErr(e3, "EXCEPTION!!!", true);
        }

    }
}
