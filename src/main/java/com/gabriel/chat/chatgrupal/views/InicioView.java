package com.gabriel.chat.chatgrupal.views;
import com.gabriel.chat.chatgrupal.models.ClienteModel;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class InicioView {

    @FXML
    private TextField JInput;
    @FXML
    private Button JButton;

    private ClienteModel client = new ClienteModel();


    @FXML
    private void onInitialize(ActionEvent event) throws IOException {
        String nombre  = JInput.getText();
        client.setNombre(nombre);

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/gabriel/chat/chatgrupal/layouts/ChatLayout.fxml"));
        Parent root = loader.load();
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.getScene().setRoot(root);
    }
}
