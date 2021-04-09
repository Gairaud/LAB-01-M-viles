package com.ast.airlinesystem.data;

import com.ast.airlinesystem.logic.AirplaneType;
import com.ast.airlinesystem.logic.User;

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
        String insertStatement = "EXECUTE PRC_INS_USER (?,?,?,?,?,?,?,?,?)";
        int count = 0;
        try{
            con = db.Connect();
            ps = con.prepareStatement(insertStatement);

            count = ps.executeUpdate();
            if(count == 0){
                throw new Exception("El tipo ya existe");
            }

        }
        catch (Exception e){

        }
        return count;
    }


}
