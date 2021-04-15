package com.ast.airlinesystem.controller;

import java.io.*;
import java.sql.SQLException;
import java.util.List;

import com.ast.airlinesystem.logic.Ticket;
import com.ast.airlinesystem.logic.Model;

import jakarta.servlet.http.*;
import com.google.gson.*;


public class ticketServlet extends HttpServlet{
    private String message;
    private final Gson gsonObject = new Gson();



    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {

        //String action = request.getParameter("action");
        switch (request.getServletPath()) {

            case "/get-tickets":{

                try {
                    List<Ticket> ticketsList = Model.instance().getTickets();
                    String allTickets = gsonObject.toJson(ticketsList);
                    PrintWriter out = response.getWriter();
                    out.print(allTickets);
                    out.flush();
                } catch (Exception e) {
                    e.printStackTrace();
                    break;

                }
            }

            case "/add-ticket":{

                try {
                   Ticket ticket = new Ticket();
                   ticket.setId(Integer.parseInt(request.getParameter("id")));
                   ticket.setCol(Integer.parseInt(request.getParameter("col")));
                   ticket.setRow(Integer.parseInt(request.getParameter("row")));
                   ticket.setReservation(Model.instance().getReservationById(request.getParameter("reservation")));
                   Model.instance().addTicket(ticket);
                } catch (Exception e) {
                    e.printStackTrace();
                    

                }
                break;
            }
            
           
    }

}}
