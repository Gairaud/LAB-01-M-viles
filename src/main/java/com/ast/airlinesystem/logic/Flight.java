package com.ast.airlinesystem.logic;

public class Flight {

    public Flights(int id, Routes route, String departureDate, String returnDate, float number, int availableSeats) {
        this.id = id;
        this.route = route;
        this.departureDate = departureDate;
        this.returnDate = returnDate;
        this.number = number;
        this.availableSeats = availableSeats;
    }
    public Flights() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Routes getRoute() {
        return route;
    }

    public void setRoute(Routes route) {
        this.route = route;
    }

    public String getDepartureDate() {
        return departureDate;
    }

    public void setDepartureDate(String departureDate) {
        this.departureDate = departureDate;
    }

    public String getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(String returnDate) {
        this.returnDate = returnDate;
    }

    public float getNumber() {
        return number;
    }

    public void setNumber(float number) {
        this.number = number;
    }

    public int getAvailableSeats() {
        return availableSeats;
    }

    public void setAvailableSeats(int availableSeats) {
        this.availableSeats = availableSeats;
    }

    private int id;
    private Routes route; 
    private String departureDate;
    private String returnDate;
    private float number;
    private int availableSeats;

    

}
