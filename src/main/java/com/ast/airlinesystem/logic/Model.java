package com.ast.airlinesystem.logic;

import com.ast.airlinesystem.data.*;

import java.sql.SQLException;
import java.util.List;

public class Model {

    private static Model uniqueInstance;

    public static Model instance(){
        if (uniqueInstance == null){
            uniqueInstance = new Model();
        }
        return uniqueInstance;
    }

    UserDao user;
    AirplaneTypeDao type;
    CityDao city;
    CountryDao country;
    ScheduleDao schedule;
    RoutesDao route;
    public Model(){

        user = new UserDao();
        type  = new AirplaneTypeDao();
        country = new CountryDao();
        city = new CityDao();
        schedule = new ScheduleDao();
        route = new RoutesDao();
    }
    //User
    public User getUser(String userName, String password) throws SQLException {
        return user.getUser(userName, password);
    }
    public void addUser(User puser) throws Exception{
        user.addUser(puser);
    }

    public List<AirplaneType> getTypes() throws Exception {
        return type.typesList();
    }

    // Country
    public  List<Country> getCountries() throws Exception{
        return country.countryList();
    }
    public Country getCountry(String id) throws Exception{
        return country.getCountry(id);
    }
    public void addCountry(Country pais) throws Exception{
        country.addCountry(pais);
    }
    public void updateCountry(Country pais) throws Exception{
        country.updateCountry(pais);
    }

    //City
    public  List<City> getCities() throws Exception{
        return city.cityList();
    }
    public City getCity(String id) throws Exception{
        return city.getCity(id);
    }
    public void addCity(City Pcity) throws Exception{
        city.addCity(Pcity);
    }
    public void updateCity(City Pcity) throws Exception{
        city.updateCity(Pcity);
    }

    //Schedule
    public  List<Schedule> getSchedules() throws Exception{
        return schedule.ScheduleList();
    }
    public Schedule getSchedule(String id) throws Exception{
        return schedule.getSchedule(id);
    }
    public void addSchedule(Schedule Pschedule) throws Exception{
        schedule.addSchedule(Pschedule);
    }
    public void updateSchedule(Schedule Pschedule) throws Exception{
        schedule.updateSchedule(Pschedule);
    }

    //Routes
    public  List<Routes> getRoutes() throws Exception{
        return route.RouteList();
    }
    public Routes getRoute(String id) throws Exception{
        return route.getRoute(id);
    }
    public void addRoute(Routes Proute) throws Exception{
        route.addRoute(Proute);
    }
    public void updateRoute(Routes Proute) throws Exception{
        route.updateRoute(Proute);
    }
}
