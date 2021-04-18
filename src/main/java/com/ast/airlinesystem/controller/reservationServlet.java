package com.ast.airlinesystem.controller;

import java.io.*;
import java.sql.SQLException;
import java.util.List;

import com.ast.airlinesystem.logic.Model;
import com.ast.airlinesystem.logic.Reservation;
import com.ast.airlinesystem.logic.User;

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
                    BufferedReader reader = request.getReader();
                    User user = gsonObject.fromJson(reader, User.class);
                    List<Reservation> reservationsList = Model.instance().getReservationsByUser(String.valueOf(user.getId()));
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

            case "/add-reservation":{
                try{
                    Reservation res = new Reservation();
                    res.setId(Integer.parseInt(request.getParameter("id")));
                    res.setSeatQuantity(Integer.parseInt(request.getParameter("seatQuantity")));
                    res.setTotalPrice(Float.parseFloat(request.getParameter("totalPrice")));
                    res.setUser(Model.instance().getUserById(request.getParameter("userid")));
                    Model.instance().addReservation(res);
                }catch (Exception e){
                    e.printStackTrace();
                }
                break;
            }

            
   
        }
    }

}
