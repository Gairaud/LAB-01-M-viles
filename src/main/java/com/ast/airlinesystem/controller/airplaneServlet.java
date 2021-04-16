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
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                } catch (Exception e) {
                    e.printStackTrace();
                break;
                }
            case "/add-planes":{
                try{
                    Airplane airplane = new Airplane();
                    airplane.setId(request.getParameter("id"));
                    airplane.setType(Model.instance().getTypeById(request.getParameter("typeId")));
                    Model.instance().addAirplane(airplane);
                }catch (Exception e){
                    e.printStackTrace();
                }
                break;
            }

        }



    }
}



