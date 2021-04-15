package com.ast.airlinesystem.controller;

import java.io.*;
import java.sql.SQLException;
import java.util.List;

import com.ast.airlinesystem.logic.Model;
import com.ast.airlinesystem.logic.Reservation;

import jakarta.servlet.http.*;
import com.google.gson.*;


public class reservationServlet extends HttpServlet{
    private String message;
    private final Gson gsonObject = new Gson();



    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {

        //String action = request.getParameter("action");
        switch (request.getServletPath()) {

            case "/get-reservations":{

                try {
                    String userId = request.getParameter("id");
                    List<Reservation> reservationsList = Model.instance().getReservationsByUser(userId);
                    String reservations = gsonObject.toJson(reservationsList);
                    PrintWriter out = response.getWriter();
                    out.print(reservations);
                    out.flush();
                } catch (Exception e) {
                    e.printStackTrace();
                    break;

                }
                break;
            }

            
   
        }
    }

}
