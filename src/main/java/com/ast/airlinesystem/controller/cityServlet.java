package com.ast.airlinesystem.controller;

import java.io.*;
import java.sql.SQLException;
import java.util.List;

import com.ast.airlinesystem.logic.City;
import com.ast.airlinesystem.logic.Country;
import com.ast.airlinesystem.logic.Model;

import jakarta.servlet.http.*;
import com.google.gson.*;


public class cityServlet extends HttpServlet{
    private String message;
    private final Gson gsonObject = new Gson();



    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {

        //String action = request.getParameter("action");
        switch (request.getServletPath()) {

            case "/get-cities":{

                try {
                    List<City> citiesList = Model.instance().getCities();
                    String allCountries = gsonObject.toJson(citiesList);
                    PrintWriter out = response.getWriter();
                    out.print(allCountries);
                    out.flush();
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                } catch (Exception e) {
                    e.printStackTrace();
                    break;

                }
                break;
            }

            case "/get-city":{
                try {
                    String param = request.getParameter("id");
                    City city = Model.instance().getCity(param);
                    String City = gsonObject.toJson(city);
                    PrintWriter out = response.getWriter();
                    out.print(City);
                    out.flush();
                }catch (SQLException throwables) {
                    throwables.printStackTrace();
                } catch (Exception e) {
                    e.printStackTrace();
                    break;

                }
                break;
            }

            case "/add-city":{
                try {
                    City city = new City();
                    Country country = new Country();
                    city.setId(request.getParameter("id"));
                    city.setName(request.getParameter("name"));
                    country.setId(request.getParameter("country"));
                    city.setCountry(country);
                    Model.instance().addCity(city);
                }catch (SQLException throwables) {
                    throwables.printStackTrace();
                } catch (Exception e) {
                    e.printStackTrace();
                    break;

                }
                break;
            }
            case "/upd-city":{
                try {
                    City city = new City();
                    Country country = new Country();
                    city.setId(request.getParameter("id"));
                    city.setName(request.getParameter("name"));
                    country.setId(request.getParameter("country"));
                    city.setCountry(country);
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        }
    }

}
