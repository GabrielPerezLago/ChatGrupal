package com.gabriel.chat.chatgrupal.controllers.clients;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class Cliente {
    private static final String HOST = "localhost";
    private static final int PORT = 8080;

    public static void main(String[] args) {
        try (
                Socket socket = new Socket(HOST, PORT);
                BufferedReader entrada = new BufferedReader(
                        new InputStreamReader(socket.getInputStream()));
                PrintWriter salida = new PrintWriter(
                        socket.getOutputStream(), true);
                Scanner scanner = new Scanner(System.in)
                ){

            new Thread(() -> {
                try {
                    String msg;
                    while ((msg = entrada.readLine()) != null) {
                        System.out.println("\n" + msg);
                        System.out.print(">> ");
                    }
                } catch (IOException e) {
                    System.out.println("Conexión cerrada");
                }
            }).start();

            System.out.println("Conectado al chat. Escribe mensajes:");

            while (true) {
                System.out.print(">> ");
                String mensaje = scanner.nextLine();
                salida.println(mensaje);

                if (mensaje.equalsIgnoreCase("salir")) {
                    break;
                }
            }

        } catch (IOException ex) {
            System.err.println(ex);
        }
    }
}
