package com.ast.airlinesystem.logic;

import com.ast.airlinesystem.data.UserDao;

import java.sql.SQLException;

public class Model {

    private static Model uniqueInstance;

    public static Model instance(){
        if (uniqueInstance == null){
            uniqueInstance = new Model();
        }
        return uniqueInstance;
    }

    UserDao user;

    public User getUser(String userName, String password) throws SQLException {
        return user.getUser(userName, password);
    }
}
