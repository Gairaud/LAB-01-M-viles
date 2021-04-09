package com.ast.airlinesystem.controller;


import java.io.*;
import java.sql.SQLException;
import java.util.List;


import com.ast.airlinesystem.logic.Model;
import com.ast.airlinesystem.logic.AirplaneType;
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
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                } catch (Exception e) {
                    e.printStackTrace();
                break;

        }



        }
    }
}
