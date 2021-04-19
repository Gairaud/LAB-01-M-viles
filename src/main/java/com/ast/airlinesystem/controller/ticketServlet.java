package com.ast.airlinesystem.controller;

import java.io.*;
import java.sql.SQLException;
import java.util.List;

import com.ast.airlinesystem.logic.*;
import com.google.gson.reflect.TypeToken;
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
                    BufferedReader reader = request.getReader();
                    User u = gsonObject.fromJson(reader, User.class);
                    List<Ticket> ticketsList = Model.instance().getTickets(String.valueOf(u.getId()));
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

            case "/add-tickets":{

                try {
                    Ticket ticket = new Ticket();
                    try {
                        BufferedReader reader = request.getReader();
                        // convert JSON array to Java List
                        List<Ticket> tickets = new Gson().fromJson(reader, new TypeToken<List<Ticket>>() {}.getType());
                        int i = 0;
                        Reservation res = new Reservation();

                        User user = new User();
                        user.setId(Integer.parseInt(request.getParameter("id")));

                        res.setUser(user);
                        res.setId(Integer.parseInt(request.getParameter("id")));
                        res.setSeatQuantity(Integer.parseInt(request.getParameter("seats")));
                        res.setTotalPrice(Float.parseFloat(request.getParameter("price")));

                        Model.instance().addReservation(res);

                        int res_id = Model.instance().getReservationId();

                        res.setId(res_id);
                        while (i < tickets.size()) {

                            tickets.get(i).setReservation(res);
                            Model.instance().addTicket(tickets.get(i));
                            // Increase variable count by 1
                            i++;
                        }
                        PrintWriter out = response.getWriter();
                        out.print(tickets);
                        out.flush();

                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                } catch (Exception e) {
                    e.printStackTrace();


                }
                break;
            }

            case "/get-tickets-flight":{
                try {



                    BufferedReader reader = request.getReader();
                    Flight f = gsonObject.fromJson(reader, Flight.class);
                    List<Ticket> ticketsList = Model.instance().flightTickets(Integer.toString(f.getId()));
                    String allTickets = gsonObject.toJson(ticketsList);
                    PrintWriter out = response.getWriter();
                    out.print(allTickets);
                    out.flush();
                } catch (Exception e) {
                    e.printStackTrace();
                    break;

                }
                break;
            }
           
    }

}}
