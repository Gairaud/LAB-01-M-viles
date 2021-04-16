package com.ast.airlinesystem.logic;

public class Flight {

    public Flight(int id, Routes route, String departureDate, String returnDate, float price, int availableSeats) {
        this.id = id;
        this.route = route;
        this.departureDate = departureDate;
        this.returnDate = returnDate;
        this.price = price;
        this.availableSeats = availableSeats;
    }
    public Flight() {
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

    public float getprice() {
        return price;
    }

    public void setprice(float price) {
        this.price = price;
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
    private float price;
    private int availableSeats;

    

}
