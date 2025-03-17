package com.example.esercizio1.websocket;

import org.springframework.web.socket.handler.TextWebSocketHandler;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.concurrent.CopyOnWriteArrayList;

@Component
public class LibroWebSocketHandler extends TextWebSocketHandler {

    // Lista di sessioni WebSocket connesse
    private final CopyOnWriteArrayList<WebSocketSession> sessions = new CopyOnWriteArrayList<>();

    // Quando un client si connette, aggiungiamo la sua sessione alla lista
    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        sessions.add(session);
    }

    // Quando un client invia un messaggio, lo gestiamo (puoi anche implementare logica per rispondere)
    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
        // Gestisci il messaggio ricevuto (ad esempio, invia una notifica a tutti)
        System.out.println("Messaggio ricevuto: " + message.getPayload());
    }

    // Quando un client si disconnette, rimuoviamo la sua sessione
    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
        sessions.remove(session);
    }

    // Metodo per inviare una notifica a tutti i client connessi
    public void sendNotification(String message) {
        for (WebSocketSession session : sessions) {
            try {
                session.sendMessage(new TextMessage(message));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
