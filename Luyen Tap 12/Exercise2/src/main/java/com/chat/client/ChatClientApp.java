package com.chat.client;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import org.java_websocket.client.WebSocketClient;
import org.java_websocket.handshake.ServerHandshake;

import java.net.URI;
import java.net.URISyntaxException;

public class ChatClientApp extends Application {

    private WebSocketClient webSocketClient;
    private TextArea chatArea;
    private TextField inputField;
    private String username;

    @Override
    public void start(Stage primaryStage) {
        username = "User" + (int)(Math.random() * 1000);

        chatArea = new TextArea();
        chatArea.setEditable(false);
        VBox.setVgrow(chatArea, Priority.ALWAYS);

        inputField = new TextField();
        HBox.setHgrow(inputField, Priority.ALWAYS);
        inputField.setOnAction(e -> sendMessage());

        Button sendButton = new Button("Send");
        sendButton.setOnAction(e -> sendMessage());

        HBox inputBox = new HBox(10, inputField, sendButton);
        
        VBox root = new VBox(10, chatArea, inputBox);
        root.setPadding(new Insets(10));

        Scene scene = new Scene(root, 400, 300);
        primaryStage.setTitle("Chat Client - " + username);
        primaryStage.setScene(scene);
        primaryStage.setOnCloseRequest(e -> {
            if (webSocketClient != null) {
                webSocketClient.close();
            }
            Platform.exit();
            System.exit(0);
        });
        primaryStage.show();

        connectToServer();
    }

    private void connectToServer() {
        try {
            webSocketClient = new WebSocketClient(new URI("ws://localhost:8887")) {
                @Override
                public void onOpen(ServerHandshake handshakedata) {
                    Platform.runLater(() -> chatArea.appendText("Connected to server.\n"));
                }

                @Override
                public void onMessage(String message) {
                    Platform.runLater(() -> chatArea.appendText(message + "\n"));
                }

                @Override
                public void onClose(int code, String reason, boolean remote) {
                    Platform.runLater(() -> chatArea.appendText("Disconnected from server.\n"));
                }

                @Override
                public void onError(Exception ex) {
                    Platform.runLater(() -> chatArea.appendText("Error: " + ex.getMessage() + "\n"));
                }
            };
            webSocketClient.connect();
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
    }

    private void sendMessage() {
        String text = inputField.getText();
        if (!text.trim().isEmpty() && webSocketClient != null && webSocketClient.isOpen()) {
            webSocketClient.send(username + ": " + text);
            inputField.clear();
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
