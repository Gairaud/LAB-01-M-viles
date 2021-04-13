package com.ast.airlinesystem.logic;

import com.ast.airlinesystem.data.AirplaneTypeDao;
import com.ast.airlinesystem.data.CityDao;
import com.ast.airlinesystem.data.CountryDao;
import com.ast.airlinesystem.data.UserDao;

import java.sql.SQLException;
import java.util.List;

public class Model {

    private static Model uniqueInstance;

    public static Model instance(){
        if (uniqueInstance == null){
            uniqueInstance = new Model();
        }
        return uniqueInstance;
    }

    UserDao user;
    AirplaneTypeDao type;
    CityDao city;
    CountryDao country;

    public Model(){

        user = new UserDao();
        type  = new AirplaneTypeDao();
        country = new CountryDao();
    }
    public User getUser(String userName, String password) throws SQLException {
        return user.getUser(userName, password);
    }
    public List<AirplaneType> getTypes() throws Exception {
        return type.typesList();
    }

    public  List<Country> getCountries() throws Exception{
        return country.countryList();
    }

    public Country getCountry(String id) throws Exception{
        return country.getCountry(id);
    }
}
