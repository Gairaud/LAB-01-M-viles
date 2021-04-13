package com.ast.airlinesystem.controller;

import java.io.*;
import java.sql.SQLException;
import java.util.List;

import com.ast.airlinesystem.logic.Schedule;
import com.ast.airlinesystem.logic.Model;

import jakarta.servlet.http.*;
import com.google.gson.*;

public class scheduleServlet extends HttpServlet {
    private String message;
    private final Gson gsonObject = new Gson();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {

        //String action = request.getParameter("action");
        switch (request.getServletPath()) {

            case "/get-schedules":{

                try {
                    List<Schedule> schedules = Model.instance().getSchedules();
                    String AllSchedules = gsonObject.toJson(schedules);
                    PrintWriter out = response.getWriter();
                    out.print(AllSchedules);
                    out.flush();
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                } catch (Exception e) {
                    e.printStackTrace();
                    break;

                }
                break;
            }

            case "/get-schedule":{
                try {
                    String param = request.getParameter("id");
                    Schedule schedule = Model.instance().getSchedule(param);
                    String Schedule = gsonObject.toJson(schedule);
                    PrintWriter out = response.getWriter();
                    out.print(Schedule);
                    out.flush();
                }catch (SQLException throwables) {
                    throwables.printStackTrace();
                } catch (Exception e) {
                    e.printStackTrace();
                    break;

                }
                break;
            }

            case "/add-schedule":{
                try {
                    Schedule schedule = new Schedule();

                    schedule.setDay(request.getParameter("day"));
                    schedule.setDepartureTime(request.getParameter("Dtime"));
                    schedule.setArrivalTime(request.getParameter("Atime"));

                    Model.instance().addSchedule(schedule);
                }catch (SQLException throwables) {
                    throwables.printStackTrace();
                } catch (Exception e) {
                    e.printStackTrace();
                    break;

                }
                break;
            }
            case "/upd-schedule":{
                try {
                    Schedule schedule = new Schedule();
                    schedule.setId(Integer.parseInt(request.getParameter("id")));
                    schedule.setDay(request.getParameter("day"));
                    schedule.setDepartureTime(request.getParameter("Dtime"));
                    schedule.setArrivalTime(request.getParameter("Atime"));

                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        }
    }


}
