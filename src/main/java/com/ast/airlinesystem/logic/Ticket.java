package com.ast.airlinesystem.logic;

public class Ticket {

    private int row;
    private int col;
    private Reservation reservation;

    public Ticket(int id, int row, int col, Reservation reservation) {
        this.id = id;
        this.row = row;
        this.col = col;
        this.reservation = reservation;
    }

    public Ticket(){

    }
    private int id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getRow() {
        return row;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public int getCol() {
        return col;
    }

    public void setCol(int col) {
        this.col = col;
    }

    public Reservation getReservation() {
        return reservation;
    }

    public void setReservation(Reservation reservation) {
        this.reservation = reservation;
    }


}
