package com.gabriel.chat.chatgrupal.controllers.server;

import com.gabriel.chat.chatgrupal.models.ChatModel;

import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ManejadorChats {
    private Socket socket;
    private List<ChatModel> chats = new ArrayList<>();
    private List<ManejadorCliente> clientes = new ArrayList<ManejadorCliente>();
    private Map<ChatModel, ManejadorCliente> connectionChats = new HashMap<>();

    public  ManejadorChats() {

    }
    public ManejadorChats(Socket socket) {
        this.socket = socket;
    }


    public String connectToChat(ManejadorCliente cliente, String nombreChat) {
        for(ChatModel chat : chats){
            if(chat.getNombreChat().equals(nombreChat)){
                connectionChats.put(chat, cliente);
                String sucessful = "Te has conectado al chat " + nombreChat;
                return sucessful;
            } else {
                String error = "Error: No se a podido establecer conexion con el chat o este no existe.";
                return error;
            }
        }
        return nombreChat;
    }

    public void cretateChat(ChatModel chat) {
        if(!isExistChat(chat)) {
            this.chats.add(chat);
        }
    }

    public List<ChatModel> getChats() {
        return this.chats;
    }

    private boolean isExistChat(ChatModel chat) {
        for(ChatModel c : chats){
            if(c.getNombreChat().equals(chat.getNombreChat())){
                return true;
            }
        }
        return false;
    }





}
