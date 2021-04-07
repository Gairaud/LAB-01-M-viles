package com.ast.airlinesystem.logic;

public class Reservation {

    private int id;
    private Ticket ticket;
    private User user;

    public Reservation(int id, Ticket ticket, User user) {
        this.id = id;
        this.ticket = ticket;
        this.user = user;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Ticket getTicket() {
        return ticket;
    }

    public void setTicket(Ticket ticket) {
        this.ticket = ticket;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }


}
