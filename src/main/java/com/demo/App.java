package com.demo;

import com.demo.utils.ResourcesConfig;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import org.yaml.snakeyaml.error.YAMLException;
import org.yu.myorm.config.DBConfig;
import org.yu.myorm.config.YMLConfig;
import org.yu.myorm.core.DBConnecter;
import org.yu.myorm.core.Exception.NoSuchDataInDBException;
import org.yu.myorm.core.handleErr;

import java.sql.Connection;
import java.sql.SQLException;


public class App extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        getDBConnect();
        primaryStage.setTitle(ResourcesConfig.BOOK_MANAGE_TITLE);
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(ResourcesConfig.LOGIN_FXML));
        Parent root = fxmlLoader.load();
        Scene scene = new Scene(root);
        scene.getStylesheets().add(ResourcesConfig.CSS_STYLE);
        primaryStage.setMaximized(true);
        primaryStage.getIcons().add(new Image(ResourcesConfig.IMG_LOGO));
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void getDBConnect(){
            Connection conn = null;
            YMLConfig ymlConfig;
            DBConfig dbconf;
            // 获取连接的一个状态
            try {
                ymlConfig = YMLConfig.loadDataFromYML(ResourcesConfig.YML_PATH);
                dbconf = ymlConfig.getDB();
                conn = DBConnecter.getConnection(dbconf);
            } catch (NoSuchDataInDBException dbe) {
                handleErr.printErr(dbe, dbe.getMessage(), false);
            }catch (ClassNotFoundException e) {
                // 数据库驱动类异常处理
                handleErr.printErr(e, "DB Driver Load Failed!", false);
            } catch (SQLException e1) {
                // 数据库连接失败异常处理
                handleErr.printErr(e1, "DB CONNECT/COMMAND FAILED!  state: " + e1.getSQLState() +'\n'+ "VendorErrCode: "+e1.getErrorCode(), false);
            } catch (YAMLException e2) {
                handleErr.printErr(e2, "LOAD OBJECT FROM YAML FAILED!", false);
            } catch (Exception e3) {
                handleErr.printErr(e3, "EXCEPTION!!!", true);
            }

    }


    public boolean showLoginView(){
        boolean flag = false;
        try {
            this.start(new Stage());
            flag = true;
        }catch (Exception e){
            e.printStackTrace();
        }
        return flag;
    }

    public static void main(String[] args) {
        launch(args);
    }

}
