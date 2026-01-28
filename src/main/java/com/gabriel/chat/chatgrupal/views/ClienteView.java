package com.gabriel.chat.chatgrupal.views;

import com.gabriel.chat.chatgrupal.controllers.clients.Cliente;
import com.gabriel.chat.chatgrupal.models.ClienteModel;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;



public class ClienteView {

    private Cliente cliente;

    @FXML
    private VBox chatBox;

    @FXML
    private TextField inputMessage;

    private ClienteModel clienteModel = new ClienteModel();

    @FXML
    public void initialize() {
        cliente = new Cliente(this.clienteModel.getNombre(), this);

        Thread hiloCliente = new Thread(cliente::iniciar);
        hiloCliente.setDaemon(true);
        hiloCliente.start();
    }

    @FXML
    private void onSendMessage() {
        String mensaje = this.inputMessage.getText().trim();

        if(mensaje.isEmpty()){
            return;
        }

        cliente.enviarMensaje(mensaje);
        sendUserMessage(mensaje);

        inputMessage.clear();
    }


    public void sendUserMessage(String message){
        VBox mensaje = createDefaultMessage(message, this.clienteModel.getNombre(), Pos.CENTER_RIGHT, Color.BLUE);
        Platform.runLater(()->chatBox.getChildren().add(mensaje));
    }

    public void sendExternalClientMessage(String nombre, String message) {
        VBox mensaje = createDefaultMessage(message, nombre, Pos.CENTER_LEFT, Color.GRAY);
        Platform.runLater(()->chatBox.getChildren().add(mensaje));
    }


    public VBox createDefaultMessage(String message, String nombre, Pos position, Color color) {
        Label nombreLbl = new Label(nombre);

        Label messageLbl = new Label(message);
        messageLbl.setWrapText(true);
        messageLbl.setMaxWidth(400);

        VBox container = new VBox();
        container.setBackground(new Background( new BackgroundFill(color,  CornerRadii.EMPTY, Insets.EMPTY )));
        container.setAlignment(position);
        container.getChildren().addAll(nombreLbl, messageLbl);

        return container;
    }



}
