package com.ast.airlinesystem.logic;

import com.ast.airlinesystem.data.AirplaneTypeDao;
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
    public Model(){

        user = new UserDao();
        type  = new AirplaneTypeDao();

    }
    public User getUser(String userName, String password) throws SQLException {
        return user.getUser(userName, password);
    }
    public List<AirplaneType> getTypes() throws Exception {
        return type.typesList();
    }
}
