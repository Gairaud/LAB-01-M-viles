package com.ast.airlinesystem.data;

import com.ast.airlinesystem.logic.AirplaneType;
import com.ast.airlinesystem.logic.Country;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CountryDao {

    DbConnection db = new DbConnection();
    PreparedStatement ps;
    ResultSet rs;
    Connection con;


    public int addCountry (Country country) throws  Exception{
        String insertStatement = "CALL PRC_INS_COUNTRY(?,?)";
        int count = 0;
        try{
            con = db.Connect();
            ps = con.prepareStatement(insertStatement);
            ps.setString(1, country.getId());
            ps.setString(2, country.getName());

            count = ps.executeUpdate();
            if(count == 0){
                throw new Exception("El pais ya existe");
            }

        }
        catch (Exception e){

        }
        return count;
    }

    public Country getCountry(String id){
        Country country = new Country();
        String getStatement = "SELECT * FROM COUNTRIES WHERE ID =\'"+id+"\'";
        try{
            con = db.Connect();
            ps = con.prepareStatement(getStatement);
            rs = ps.executeQuery();
            while (rs.next()){
                country.setId(rs.getString(1));
                country.setName(rs.getString(2));
            }
        } catch (SQLException ex) {

        }
        return country;
    }

    public List countryList(){
        List<Country> list = new ArrayList<>();
        String sql = "select * from COUNTRIES";
        try{
            con = db.Connect();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()){
                Country at = new Country();
                at.setId(rs.getString(1));
                at.setName(rs.getString(2));
                list.add(at);
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public void updateCountry(Country country){

        String updateStatement = "CALL prc_upd_country(?,?)";
        try{
            con = db.Connect();
            ps = con.prepareStatement(updateStatement);
            ps.setString(1, country.getId());
            ps.setString(2, country.getName());

            ps.executeUpdate(updateStatement);

        }
        catch (Exception e){

        }

    }
}
