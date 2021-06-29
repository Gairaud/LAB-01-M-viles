package com.ast.airlinesystem.controller;


import java.io.*;
import java.sql.SQLException;
import java.util.List;


import com.ast.airlinesystem.logic.Model;
import com.ast.airlinesystem.logic.Airplane;
import com.ast.airlinesystem.logic.AirplaneType;
import jakarta.servlet.http.*;
import com.google.gson.*;

public class airplaneServlet  extends HttpServlet {
    
    private String message;
    private final Gson gsonObject = new Gson();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws  IOException {

        //String action = request.getParameter("action");
        switch(request.getServletPath()){

            case "/get-planes":
                try {
                    List<Airplane> planesList = Model.instance().getAirplanes();
                    String planes = gsonObject.toJson(planesList);
                    PrintWriter out = response.getWriter();
                    out.print(planes);
                    out.flush();
                    break;
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                } catch (Exception e) {
                    e.printStackTrace();
                break;
                }
            case "/add-planes":{
                BufferedReader reader = request.getReader();
                Airplane plane = gsonObject.fromJson(reader, Airplane.class);
                
                try {
                    Model.instance().addAirplane(plane);
                    break;
                }catch (Exception e){

                }
                break;
            }

            case "/delete-planes":{
                BufferedReader reader = request.getReader();
                Airplane plane = gsonObject.fromJson(reader, Airplane.class);

                try {
                    Model.instance().deleteAirplane(plane);
                    break;
                }catch (Exception e){

                }
                break;
            }
        }



    }
}



