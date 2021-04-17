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

            case "/get-user": {
                BufferedReader reader = request.getReader();
                User user = gsonObject.fromJson(reader, User.class);
                User finalUser = null;
                try {
                    finalUser = Model.instance().getUser(user.getEmail());
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
                String userData = gsonObject.toJson(finalUser);
                PrintWriter out = response.getWriter();
                response.setContentType("application/json");
                response.setCharacterEncoding("UTF-8");
                out.print(userData);
                out.flush();
                break;
            }
            case "/login": {
                BufferedReader reader = request.getReader();
                User user = gsonObject.fromJson(reader, User.class);
                Boolean exist = Model.instance().verify(user.getEmail(), user.getPassword());
                String logSuccess = gsonObject.toJson(exist);
                PrintWriter out = response.getWriter();
                response.setContentType("application/json");
                response.setCharacterEncoding("UTF-8");
                out.print(logSuccess);
                out.flush();
                break;
            }
            case "/add-user":{
                User user = new User();

                user.setPassword(request.getParameter("pswrd"));
                user.setId(Integer.parseInt(request.getParameter("id")));
                user.setName(request.getParameter("name"));
                user.setUserName(request.getParameter("user"));
                user.setLastName(request.getParameter("lastname"));
                user.setAddress(request.getParameter("address"));
                user.setPhone(request.getParameter("phone"));
                user.setEmail(request.getParameter("email"));
                try {
                    Model.instance().addUser(user);
                }catch (Exception e){

                }
            }
        }


    }

}
