package com.gabriel.chat.chatgrupal.controllers.client;

import com.gabriel.chat.chatgrupal.controllers.server.ManejadorChats;
import com.gabriel.chat.chatgrupal.models.ChatModel;

public class ChatCli {
    private String nombre ;
    private ChatModel chatModel = new  ChatModel();


    public void creteChat(String nombre) {
        chatModel.setNombreChat(nombre);

        ManejadorChats chat = new ManejadorChats();

        chat.cretateChat(chatModel);
        chat.connectToChat();
    }



}
