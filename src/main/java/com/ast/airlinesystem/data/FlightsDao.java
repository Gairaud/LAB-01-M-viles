package com.ast.airlinesystem.data;

import com.ast.airlinesystem.logic.Flight;
import com.ast.airlinesystem.logic.Flights;
import com.ast.airlinesystem.logic.Model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class FlightsDao {
    
    DbConnection db = new DbConnection();
    PreparedStatement ps;
    ResultSet rs;
    Connection con;

    public List<Flight> flightsList(){
        List<Flights> list = new ArrayList<>();
        String sql = "SELECT * FROM FLIGHTS f";
        try{
            con = db.Connect();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()){
                Flight f = new Flight();
                f.setId(Integer.parseInt(rs.getNString(1)));
                f.setRoute(Model.instance().getRoute(rs.getString(2)));
                f.setDepartureDate(rs.getString(3));
                f.setReturnDate(rs.getString(4));
                f.setprice(Float.parseFloat(rs.getString(5)));
                f.setAvailableSeats(Integer.parseInt(rs.getString(6)));
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

}

