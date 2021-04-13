package com.ast.airlinesystem.logic;

public class Routes {



    private String id;
    private String duration;
    private City origin;
    private City destination;
    private Airplane airplane;
    private Schedule schedule;

    public Routes(){}
    public Routes(String id, String duration, City origin, City destination, Airplane airplane, Schedule schedule) {
        this.id = id;
        this.duration = duration;
        this.origin = origin;
        this.destination = destination;
        this.airplane = airplane;
        this.schedule = schedule;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public City getOrigin() {
        return origin;
    }

    public void setOrigin(City origin) {
        this.origin = origin;
    }

    public City getDestination() {
        return destination;
    }

    public void setDestination(City destination) {
        this.destination = destination;
    }

    public Airplane getAirplane() {
        return airplane;
    }

    public void setAirplane(Airplane airplane) {
        this.airplane = airplane;
    }

    public Schedule getSchedule() {
        return schedule;
    }

    public void setSchedule(Schedule schedule) {
        this.schedule = schedule;
    }

}
