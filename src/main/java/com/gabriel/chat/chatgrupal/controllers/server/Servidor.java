package com.gabriel.chat.chatgrupal.controllers.server;

import com.gabriel.chat.chatgrupal.models.ChatModel;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

public class Servidor {
    public static final int PUERTO = 8080;
    public static final int MAX_CLIENTES = 8;
    public String nombreCliente;
    public static final AtomicInteger contadorClientes = new AtomicInteger(0);
    public static Set<ManejadorChats> chats = ConcurrentHashMap.newKeySet();
    public static Set<ManejadorCliente> clientes = ConcurrentHashMap.newKeySet();
    public static ConcurrentHashMap<ManejadorChats, ManejadorCliente> connections = new ConcurrentHashMap<>();
    public static void main(String[] args) {
        ExecutorService pool = Executors.newFixedThreadPool(MAX_CLIENTES);

        System.out.println("Servidor iniciado --> El servidor se ha iniciado correctamente en el puerto : " + PUERTO);


        try(ServerSocket serverSocket = new ServerSocket(PUERTO)){
            while(true){
                System.out.println("Servidor en espera ...");
                Socket socket = serverSocket.accept();

                int idCliente = contadorClientes.incrementAndGet();
                ManejadorChats manejadorChats = new ManejadorChats(socket);
                chats.add(manejadorChats);

                ManejadorCliente manejadorCliente = new ManejadorCliente(socket, idCliente);
                clientes.add(manejadorCliente);
                pool.execute(manejadorCliente);

                connections.put(manejadorChats, manejadorCliente);
                System.out.println("Cliente --> id " + idCliente + " <-- conectado" );
            }
        } catch (IOException ex){
            System.err.println("Error del servidor: " + ex.getMessage());
        }


    }

    public void setNombreCliente(String nombreCliente) {
        this.nombreCliente = nombreCliente;
    }
}
