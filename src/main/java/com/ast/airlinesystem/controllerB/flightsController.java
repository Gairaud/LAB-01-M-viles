package com.ast.airlinesystem.controllerB;


import com.ast.airlinesystem.logic.Flight;
import com.ast.airlinesystem.logic.Model;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;


import java.io.IOException;
import java.util.List;

import jakarta.websocket.OnMessage;

import jakarta.websocket.Session;
import jakarta.websocket.server.ServerEndpoint;

@ServerEndpoint("/controllerflights")
public class flightsController {

    private final Gson gsonObject = new Gson();



    @OnMessage
    public void onMessage(Session session, String message) throws Exception {
        JsonParser parser = new JsonParser();
        JsonObject json=parser.parse(message).getAsJsonObject();

        String action = json.get("action").getAsString();

        switch (action){
            case "flights-list":
                List<Flight> flist = Model.instance().getFlights();
                String allF= gsonObject.toJson(flist);
                session.getBasicRemote().sendText(allF);
                break;
            default:
                System.out.println("Mapping Error");
        }
    }
}
