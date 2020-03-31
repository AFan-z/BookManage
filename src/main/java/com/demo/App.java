package com.demo;

import com.demo.utils.ResourcesConfig;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class App extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
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

    public static void main(String[] args) {
        launch(args);
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
}
