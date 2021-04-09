package com.ast.airlinesystem.controller;

import java.io.*;
import java.sql.SQLException;


import com.ast.airlinesystem.logic.Model;
import com.ast.airlinesystem.logic.User;
import jakarta.servlet.http.*;
import com.google.gson.*;



public class userServlet extends HttpServlet {
    private String message;
    private final Gson gsonObject = new Gson();


    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws  IOException {

        //String action = request.getParameter("action");
        switch(request.getServletPath()){

            case "/get-user":
                String userId = request.getParameter(("id"));

                User user = null;
                try {
                    user = Model.instance().getUser("123456789", "kike00");
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
                String userString = gsonObject.toJson(user);
                break;

        }


    }

}
