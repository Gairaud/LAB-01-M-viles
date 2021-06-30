package com.ast.airlinesystem.controller;

import java.io.*;
import java.sql.SQLException;
import java.util.List;

import com.ast.airlinesystem.logic.*;

import jakarta.servlet.http.*;
import com.google.gson.*;

public class routesServlet extends HttpServlet{

    private String message;
    private final Gson gsonObject = new Gson();



    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {

        //String action = request.getParameter("action");
        switch (request.getServletPath()) {

            case "/get-routes":{

                try {
                    List<Routes> routesList = Model.instance().getRoutes();
                    String allRoutes = gsonObject.toJson(routesList);
                    PrintWriter out = response.getWriter();
                    out.print(allRoutes);
                    out.flush();
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                } catch (Exception e) {
                    e.printStackTrace();
                    break;

                }
                break;
            }

            case "/get-route":{
                try {
                    String param = request.getParameter("id");
                    Routes route = Model.instance().getRoute(param);
                    String Routes = gsonObject.toJson(route);
                    PrintWriter out = response.getWriter();
                    out.print(Routes);
                    out.flush();
                }catch (SQLException throwables) {
                    throwables.printStackTrace();
                } catch (Exception e) {
                    e.printStackTrace();
                    break;

                }
                break;
            }

            case "/add-route":{
                BufferedReader reader = request.getReader();
                Routes route = gsonObject.fromJson(reader, Routes.class);
                
                try {
                    Model.instance().addRoute(route);
                    break;
                }catch (Exception e){

                }
                break;
            }
            /*case "/upd-route":{
                try {
                    Routes route = new Routes();


                    route.setId(request.getParameter("id"));
                    origin.setId(request.getParameter("Oriid"));
                    destiny.setId(request.getParameter("Desid"));
                    airplane.setId(request.getParameter("Airid"));
                    schedule.setId(Integer.parseInt(request.getParameter("Schid")));

                    route.setOrigin(origin);
                    route.setDestination(destiny);
                    route.setAirplane(airplane);
                    route.setSchedule(schedule);
                    Model.instance().updateRoute(route);
                }catch (Exception e){
                    e.printStackTrace();
                }
            }*/
        }
    }
}
