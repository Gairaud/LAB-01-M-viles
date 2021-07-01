package com.ast.airlinesystem.controllerB;

import com.ast.airlinesystem.logic.AirplaneType;
import com.ast.airlinesystem.logic.Model;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;


import java.io.IOException;
import java.util.List;

import jakarta.websocket.OnMessage;
import jakarta.websocket.OnOpen;
import jakarta.websocket.Session;
import jakarta.websocket.server.PathParam;
import jakarta.websocket.server.ServerEndpoint;

@ServerEndpoint("/controllertypes")
public class flightController {

    private final Gson gsonObject = new Gson();

    @OnOpen
    public void onOpen(Session session) throws IOException {
        // Get session and WebSocket connection
    }

    @OnMessage
    public void onMessage(Session session, String message) throws Exception {
        JsonParser parser = new JsonParser();
        JsonObject json=parser.parse(message).getAsJsonObject();

        String action = json.get("action").getAsString();

        switch (action){
            case "types-list":
                List<AirplaneType> typesList = Model.instance().getTypes();
                String allTypes= gsonObject.toJson(typesList);
                session.getBasicRemote().sendText(allTypes);
                break;
            default:
                System.out.println("Mapping Error");
        }
    }
}
