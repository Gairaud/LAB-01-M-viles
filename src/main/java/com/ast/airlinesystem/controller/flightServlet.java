package com.ast.airlinesystem.controller;

import java.io.*;
import java.sql.SQLException;
import java.util.List;

import com.ast.airlinesystem.logic.AirplaneType;
import com.ast.airlinesystem.logic.Flight;
import com.ast.airlinesystem.logic.Model;
import com.ast.airlinesystem.logic.Routes;

import jakarta.servlet.http.*;
import com.google.gson.*;

public class flightServlet  extends HttpServlet {

    private String message;
    private final Gson gsonObject = new Gson();
    
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {

    switch (request.getServletPath()) {

        case "/get-flights":{

            try {
                List<Flight> flightList = Model.instance().getFlights();
                String flights = gsonObject.toJson(flightList);
                PrintWriter out = response.getWriter();
                out.print(flights);
                out.flush();
            } catch (Exception e) {
                e.printStackTrace();
                break;

            }
            break;
        }

        case "/add-flight":{
            try{
                Flight f = new Flight();
                BufferedReader reader = request.getReader();
                Flight type = gsonObject.fromJson(reader, Flight.class);

/*              f.setRoute(Model.instance().getRoute(request.getParameter("route")));
                f.setDepartureDate(request.getParameter("departureDate"));
                f.setReturnDate(request.getParameter("returnDate"));
                f.setprice(Float.parseFloat(request.getParameter("price")));              
                f.setAvailableSeats(Integer.parseInt(request.getParameter("seats")));
                f.setPlane(Model.instance().getAirplaneById(request.getParameter("airplane")));*/

                Model.instance().addFlight(type);
            }catch (Exception e){
                e.printStackTrace();
            }
            break;
        }

        case "/delete-flight":{
            try{

                BufferedReader reader = request.getReader();
                Flight type = gsonObject.fromJson(reader, Flight.class);
                Model.instance().deleteF(type);
            }catch (Exception e){
                e.printStackTrace();
            }
            break;
        }

        

    }
}

}
