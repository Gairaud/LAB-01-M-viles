package com.ast.airlinesystem.data;

import com.ast.airlinesystem.logic.AirplaneType;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AirplaneTypeDao {

    DbConnection db = new DbConnection();
    PreparedStatement ps;
    ResultSet rs;
    Connection con;

    public List typesList(){
        List<AirplaneType> list = new ArrayList<>();
        String sql = "select * from airplane_types";
        try{
            con = db.Connect();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()){
                AirplaneType at = new AirplaneType();
                at.setId(rs.getString(1));
                at.setYear(Integer.parseInt(rs.getString(2)));
                at.setModel(rs.getString(3));
                at.setBrand(rs.getString(4));
                at.setPassengersQuantity(Integer.parseInt(rs.getString(5)));
                at.setRowsNumber(Integer.parseInt(rs.getString(6)));
                at.setColumnsNumber(Integer.parseInt(rs.getString(7)));
                list.add(at);
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public int addType (AirplaneType type) throws  Exception{
        String insertStatement = "EXECUTE PRC_INS_AVIONTYPE (?,?,?,?,?,?,?)";
        int count = 0;
        try{
            con = db.Connect();
            ps = con.prepareStatement(insertStatement);
            ps.setString(1, type.getId());
            ps.setString(2,Integer.toString(type.getYear()));
            ps.setString(3,type.getModel());
            ps.setString(4,type.getBrand());
            ps.setString(5,Integer.toString(type.getPassengersQuantity()));
            ps.setString(6,Integer.toString(type.getRowsNumber()));
            ps.setString(7,Integer.toString(type.getColumnsNumber()));
            count = ps.executeUpdate();
            if(count == 0){
                throw new Exception("El tipo ya existe");
            }

        }
        catch (Exception e){

        }
        return count;
    }

    public static AirplaneType toType(ResultSet rs) throws Exception{
        try{
            AirplaneType at = new AirplaneType();
            at.setId(rs.getString("at.id"));
            at.setYear(Integer.parseInt(rs.getString("at.year")));
            at.setModel(rs.getString("at.model"));
            at.setBrand(rs.getString("at.brand"));
            at.setPassengersQuantity(Integer.parseInt(rs.getString("at.passengers_quantity")));
            at.setRowsNumber(Integer.parseInt(rs.getString("at.rows_number")));
            at.setColumnsNumber(Integer.parseInt(rs.getString("at.columns_number")));
            return at;
        }
        catch (Exception e){
            return null;
        }
    }

}
