package com.ast.airlinesystem.data;

import com.ast.airlinesystem.logic.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserDao {

    DbConnection db = new DbConnection();
    PreparedStatement ps;
    ResultSet rs;
    Connection con;

   public List userList(){
       List<User> list = new ArrayList<>();
       try{

       }
       catch (Exception e) {
           e.printStackTrace();
       }
       return list;
   }
   public int addUser (User user) throws  Exception{
        String insertStatement = "CALL PRC_INS_USER (?,?,?,?,?,?,?,?,?)";
        int count = 0;
            try{
                con = db.Connect();
                ps = con.prepareStatement(insertStatement);
                ps.setString(1, Integer.toString(user.getId()));
                ps.setString(2, user.getUserName());
                ps.setString(3, user.getPassword());
                ps.setString(4, user.getName());
                ps.setString(5, user.getLastName());
                ps.setString(6, user.getEmail());
                ps.setString(7, user.getAddress());
                ps.setString(8, user.getPhone());
                ps.setString(9, Integer.toString(user.getIsAdmin()));
                count = ps.executeUpdate();
                if(count == 0){
                    throw new Exception("El usuario ya existe");
                }

            }
            catch (Exception e){

            }
       return count;
   }

   public User getUser(String userName, String password){
       User user = new User();
       String getStatement = "SELECT * FROM USERS WHERE ID ="+userName;
       try{
           con = db.Connect();
           ps = con.prepareStatement(getStatement);
           rs = ps.executeQuery();
           while (rs.next()){
               user.setUserName(rs.getString("username"));
               user.setPassword(rs.getString("password"));
           }
       } catch (SQLException ex) {

       }
       return user;
   }

   public static User toUser(ResultSet rs){
    
    
    try{
        User user = new User();
        
            user.setId(Integer.parseInt(rs.getString(1)));
            user.setUserName(rs.getString(2));
            user.setPassword(rs.getString(3));
            user.setName(rs.getString(4));
            user.setLastName(rs.getString(5));
            user.setEmail(rs.getString(6));
            user.setAddress(rs.getString(7));
            user.setPhone(rs.getString(8));
            user.setIsAdmin(Integer.parseInt(rs.getString(9)));
            return user;
        
    } catch (SQLException ex) {
            return null;
    }
    
}


}
