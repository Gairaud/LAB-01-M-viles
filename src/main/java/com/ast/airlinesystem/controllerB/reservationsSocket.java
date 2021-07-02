package com.ast.airlinesystem.controllerB;


import com.ast.airlinesystem.logic.Reservation;
import com.ast.airlinesystem.logic.Model;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import jakarta.websocket.OnMessage;
import jakarta.websocket.Session;
import jakarta.websocket.server.ServerEndpoint;

import java.io.IOException;
import java.util.List;

@ServerEndpoint("/controllerreservations")
public class reservationsSocket {


    private final Gson gsonObject = new Gson();


    @OnMessage
    public void onMessage(Session session, String message) throws Exception {
        JsonParser parser = new JsonParser();
        JsonObject json = parser.parse(message).getAsJsonObject();

        String action = json.get("action").getAsString();

        switch (action) {
            case "reservation-list":
                List<Reservation> reservationList = Model.instance().getReservations();
                String allReservations = gsonObject.toJson(reservationList);
                session.getBasicRemote().sendText(allReservations);
                break;
            default:
                System.out.println("Mapping Error");
        }

    }
}