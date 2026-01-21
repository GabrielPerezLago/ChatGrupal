package com.gabriel.chat.chatgrupal.views;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class ChatView extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/gabriel/chat/chatgrupal/layouts/InicioLayout.fxml"));
        Parent root = loader.load();

        Scene scene = new Scene(root);
        scene.getStylesheets().add(getClass().getResource("/com/gabriel/chat/chatgrupal/css/styles.css").toExternalForm());

        primaryStage.setTitle("Chat Grupal");
        primaryStage.getIcons().add(new Image(getClass().getResource("/com/gabriel/chat/chatgrupal/icons/icon.png").toExternalForm()));
        primaryStage.setScene(scene);
        primaryStage.show();

    }
}
