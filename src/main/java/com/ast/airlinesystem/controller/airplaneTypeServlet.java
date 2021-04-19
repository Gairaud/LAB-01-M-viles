package com.ast.airlinesystem.controller;


import java.io.*;
import java.sql.SQLException;
import java.util.List;


import com.ast.airlinesystem.logic.Model;
import com.ast.airlinesystem.logic.AirplaneType;
import com.ast.airlinesystem.logic.User;
import jakarta.servlet.http.*;
import com.google.gson.*;

public class airplaneTypeServlet extends HttpServlet {

    private String message;
    private final Gson gsonObject = new Gson();


    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws  IOException {

        //String action = request.getParameter("action");
        switch(request.getServletPath()){

            case "/get-types":
                try {
                    List<AirplaneType> typesList = Model.instance().getTypes();
                    String allTypes = gsonObject.toJson(typesList);
                    PrintWriter out = response.getWriter();
                    out.print(allTypes);
                    out.flush();
                    break;
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                } catch (Exception e) {
                    e.printStackTrace();
                break;
            }

            case "/get-type": {

                BufferedReader reader = request.getReader();
                AirplaneType atId = gsonObject.fromJson(reader, AirplaneType.class);
                AirplaneType finalAt = null;
                try {
                    finalAt = Model.instance().getTypeById(atId.getId());
                } catch (Exception e) {
                    e.printStackTrace();
                }
                PrintWriter out = response.getWriter();
                String type = gsonObject.toJson(finalAt);
                response.setContentType("application/json");
                response.setCharacterEncoding("UTF-8");
                out.print(type);
                out.flush();
                break;
            }
            case "/add-type":{

                BufferedReader reader = request.getReader();
                AirplaneType type = gsonObject.fromJson(reader, AirplaneType.class);

                try {
                    Model.instance().addType(type);
                    break;
                }catch (Exception e){

                }
                break;
            }


        }
    }
}
