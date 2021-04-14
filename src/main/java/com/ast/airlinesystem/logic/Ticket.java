package com.ast.airlinesystem.logic;

public class Ticket {

    private int row;
    private int col;
    private Routes route;

    public Ticket(int id, int row, int col, Routes route) {
        this.id = id;
        this.row = row;
        this.col = col;
        this.route = route;
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

    public Routes getRoute() {
        return route;
    }

    public void setRoute(Routes route) {
        this.route = route;
    }


}
