package com.ast.airlinesystem.logic;

public class Reservation {

    private int id;
    private User user;
    private float totalPrice;
    private int seatQuantity;

    public Flight getFlight() {
        return flight;
    }

    public void setFlight(Flight flight) {
        this.flight = flight;
    }

    private Flight flight;


    public float getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(float totalPrice) {
        this.totalPrice = totalPrice;
    }

    public int getSeatQuantity() {
        return seatQuantity;
    }

    public void setSeatQuantity(int seatQuantity) {
        this.seatQuantity = seatQuantity;
    }

   
    public Reservation(int id, User user,float totalPrice , int seatQuantity, Flight flight) {
        this.id = id;
        this.user = user;
        this.totalPrice = totalPrice;
        this.seatQuantity = seatQuantity;
        this.flight = flight;
    }
    public Reservation(){}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }




    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }


}
