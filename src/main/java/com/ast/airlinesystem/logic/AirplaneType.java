package com.ast.airlinesystem.logic;

public class AirplaneType {

    private String id;
    private int year;
    private String model;
    private String brand;
    private int passengersQuantity;
    private int rowsNumber;
    private int columnsNumber;

    public AirplaneType() {
    }

    public AirplaneType(String id, int year, String model, String brand, int passengersQuantity,
                        int rowsNumber, int columnsNumber) {
        this.id = id;
        this.year = year;
        this.model = model;
        this.brand = brand;
        this.passengersQuantity = passengersQuantity;
        this.rowsNumber = rowsNumber;
        this.columnsNumber = columnsNumber;
    }



    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public int getPassengersQuantity() {
        return passengersQuantity;
    }

    public void setPassengersQuantity(int passengersQuantity) {
        this.passengersQuantity = passengersQuantity;
    }

    public int getRowsNumber() {
        return rowsNumber;
    }

    public void setRowsNumber(int rowsNumber) {
        this.rowsNumber = rowsNumber;
    }

    public int getColumnsNumber() {
        return columnsNumber;
    }

    public void setColumnsNumber(int columnsNumber) {
        this.columnsNumber = columnsNumber;
    }

}
