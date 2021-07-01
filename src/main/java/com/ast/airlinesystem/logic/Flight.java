package com.ast.airlinesystem.logic;

public class Flight {



    public Flight(int id, Routes route, String departureDate, String returnDate, int price, int availableSeats
            , Airplane plane) {
        this.id = id;
        this.route = route;
        this.departureDate = departureDate;
        this.returnDate = returnDate;
        this.price = price;
        this.availableSeats = availableSeats;
        this.plane = plane;
    }

    public Flight(Routes route, String departureDate, String returnDate, int price, int availableSeats
            , Airplane plane) {
        this.id = 0;
        this.route = route;
        this.departureDate = departureDate;
        this.returnDate = returnDate;
        this.price = price;
        this.availableSeats = availableSeats;
        this.plane = plane;
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

    public int getprice() {
        return price;
    }

    public void setprice(int price) {
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
    private int price;
    private int availableSeats;
    private Airplane  plane;

    public Airplane getPlane() {
        return plane;
    }

    public void setPlane(Airplane plane) {
        this.plane = plane;
    }



    

}
