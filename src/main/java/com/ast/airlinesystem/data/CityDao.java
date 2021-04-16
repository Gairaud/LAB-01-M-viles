package com.ast.airlinesystem.data;

import com.ast.airlinesystem.logic.AirplaneType;
import com.ast.airlinesystem.logic.City;
import com.ast.airlinesystem.logic.Country;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CityDao {

    DbConnection db = new DbConnection();
    PreparedStatement ps;
    ResultSet rs;
    Connection con;

    public List cityList(){
        List<City> list = new ArrayList<>();
        String sql = "SELECT * FROM CITIES c, COUNTRIES p where c.COUNTRY=p.ID";
        try{
            con = db.Connect();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()){
                City at = new City();
                Country Co = new Country();
                at.setId(rs.getString(1));
                at.setName(rs.getString(2));
                Co.setId(rs.getString(3));
                Co.setName(rs.getString(5));
                at.setCountry(Co);
                list.add(at);
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }
    public int addCity (City city) throws  Exception{
        String insertStatement = "EXECUTE PRC_INS_CITY(?,?,?)";
        int count = 0;
        try{
            con = db.Connect();
            ps = con.prepareStatement(insertStatement);
            ps.setString(1, city.getId());
            ps.setString(2, city.getName());
            ps.setString(3, city.getCountry().getId());
            count = ps.executeUpdate();
            if(count == 0){
                throw new Exception("La ciudad ya existe");
            }

        }
        catch (Exception e){

        }
        return count;
    }

    public City getCity(String id){
        City city = new City();
        String getStatement = "SELECT * FROM CITIES c, COUNTRIES p " +
                "where c.ID=\'"+id+"\' and c.COUNTRY=p.ID";
        try{
            con = db.Connect();
            ps = con.prepareStatement(getStatement);
            rs = ps.executeQuery();
            while (rs.next()){
                city.setId(rs.getString(1));
                city.setName(rs.getString(2));
                city.setCountry(new Country(rs.getString(4),rs.getString(5)));
            }
        } catch (SQLException ex) {

        }
        return city;
    }

    public void updateCity(City city){

        String updateStatement = "CALL prc_upd_city(?,?,?)";
        try{
            con = db.Connect();
            ps = con.prepareStatement(updateStatement);
            ps.setString(1, city.getId());
            ps.setString(2, city.getName());
            ps.setString(3,city.getCountry().getId());

            ps.executeUpdate(updateStatement);

        }
        catch (Exception e){

        }

    }

}
