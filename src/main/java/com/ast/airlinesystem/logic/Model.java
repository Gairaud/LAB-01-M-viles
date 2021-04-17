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
    TicketDao ticket;
    ReservationDao reservation;
    FlightsDao flight;
    AirplaneDao airplane;
    public Model(){

        user = new UserDao();
        type  = new AirplaneTypeDao();
        airplane = new AirplaneDao();
        country = new CountryDao();
        city = new CityDao();
        schedule = new ScheduleDao();
        route = new RoutesDao();
        ticket = new TicketDao();
        reservation = new ReservationDao();
        flight = new FlightsDao();
    }
    //User
    public User getUser(String userName, String password) throws SQLException {
        return user.getUser(userName, password);
    }
    public void addUser(User puser) throws Exception{
        user.addUser(puser);
    }
    public Boolean verify(String email, String pass){
        return user.verify(email,pass);
    }
    public User getUserById(String id){
        return user.getUserById(id);
    }

    //Airplanes 
    public List<Airplane> getAirplanes() throws Exception{
        return airplane.getAirplanesList();
    }
    public void addAirplane(Airplane a) throws Exception{
        airplane.addAirplane(a);
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

    //Reservations 
    public List<Reservation> getReservationsByUser(String id){
        return reservation.getReservationsByUser(id);
    }
    public void addReservation(Reservation res) throws Exception{
        reservation.addReservation(res);
    }
    public Reservation getReservationById(String id){
        return reservation.getReservationsById(id);
    }

    //Ticket 
    public void addTicket(Ticket t){
        ticket.addTicket(t);
    }

    public List<Ticket> getTickets(){
        return ticket.ticketList();
    }

    //Airplane Types 
    public List<AirplaneType> getTypes() throws Exception {
        return type.typesList();
    }
    public AirplaneType getTypeById(String id)  throws Exception{
        return type.getTypeById(id);
    }
    //Flight

    public List<Flight> getFlights() throws Exception {
        return flight.flightsList();
    }
    public void addFlight(Flight f) throws Exception {
        flight.addFlight(f);
    }
}
