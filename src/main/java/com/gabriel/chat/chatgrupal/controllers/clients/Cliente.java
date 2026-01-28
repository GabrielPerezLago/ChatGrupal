package com.gabriel.chat.chatgrupal.controllers.clients;

import com.gabriel.chat.chatgrupal.models.ClienteModel;
import com.gabriel.chat.chatgrupal.views.ClienteView;
import javafx.application.Platform;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class Cliente {
    private static final String HOST = "localhost";
    private static final int PORT = 8080;
    private String nombre;

    private ClienteView view = new ClienteView();
    private PrintWriter salida;

    public Cliente(String nombre, ClienteView view) {
        this.nombre = nombre;
        this.view = view;
    }

    public void iniciar() {
        try{
            Socket socket = new Socket(HOST, PORT);

            BufferedReader entrada = new BufferedReader(
                    new InputStreamReader(socket.getInputStream()));

            salida = new PrintWriter(
                    socket.getOutputStream(), true);

            salida.println(this.nombre);

            new Thread(() -> {
                try {
                    String msg;
                    while ((msg = entrada.readLine()) != null) {

                        String mensajeFinal = msg;

                        Platform.runLater(() -> {
                            view.sendExternalClientMessage("Servidor", mensajeFinal);
                        });
                    }
                } catch (IOException e) {
                    Platform.runLater(() ->
                            view.sendExternalClientMessage(
                                    "Sistema",
                                    "Conexión cerrada"
                            )
                    );
                }
            }).start();

            System.out.println("Conectado al chat. Escribe mensajes:");

            /*while (true) {
                System.out.print(">> ");
                String mensaje = scanner.nextLine();
                salida.println(mensaje);

                if (mensaje.equalsIgnoreCase("salir")) {
                    break;
                }
            }*/

        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public void enviarMensaje(String mensaje) {
        if (salida != null){
            salida.println(mensaje);
        }
    }

}
