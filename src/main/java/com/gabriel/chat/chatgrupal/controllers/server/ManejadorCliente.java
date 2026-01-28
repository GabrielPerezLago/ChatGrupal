package com.gabriel.chat.chatgrupal.controllers.server;

import com.gabriel.chat.chatgrupal.controllers.client.Cliente;
import com.gabriel.chat.chatgrupal.models.ChatModel;
import com.gabriel.chat.chatgrupal.models.ClienteModel;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class ManejadorCliente implements Runnable {
    private Socket socket;
    private int idCliente;
    private String nombreCliente;
    private ChatModel chat;
    private PrintWriter salida;
    private BufferedReader entrada;

    public ManejadorCliente(Socket socket, int idCliente) {
        this.socket = socket;
        this.idCliente = idCliente;
    }

    @Override
    public void run() {
        try {
            salida = new PrintWriter(socket.getOutputStream(), true);
            entrada = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            this.nombreCliente = entrada.readLine();

            salida.println("Bienvenido " + nombreCliente + " , eres el cliente -> id: " + idCliente);
            manager("El cliente --> " + idCliente + "#" + nombreCliente + " <-- se ha conectado");

            String message;
            while ((message = entrada.readLine()) != null) {
                if(message.equalsIgnoreCase("salir")){
                    break;
                }

                if (message.equals(ClienteModel.class)) {

                }

                manager("Cliente #" + nombreCliente + ": " + message);
            }


        } catch (IOException ex){
            System.err.println("Error cliente: " + idCliente + " Error: " + ex.getMessage());
        } finally {
            try {
                socket.close();
                System.out.println("❌ Cliente #" + idCliente + " desconectado");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void manager(String mensaje) {
        for( ManejadorCliente cli : Servidor.clientes ) {
            cli.salida.println(mensaje);

        }
    }


    public String getChat() {
        return chat;
    }

    public void setChat(String chat) {
        this.chat = chat;
    }
    public String getNombreCliente() {
        return nombreCliente;
    }
}
