package com.example.lab4;

import Repos.SqlRepoComanda;
import Repos.SqlRepoTort;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException, SQLException {
        SqlRepoTort repoTort = new SqlRepoTort();
        SqlRepoComanda repoComanda = new SqlRepoComanda();

        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 640, 480);

        HelloController controller1 = fxmlLoader.getController();
        controller1.init(repoTort, repoComanda);


        stage.setTitle("Hello!");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}