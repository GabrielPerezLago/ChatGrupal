module com.gabriel.chat.chatgrupal {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;

    exports  com.gabriel.chat.chatgrupal.views;
    opens com.gabriel.chat.chatgrupal.views to javafx.fxml;
}