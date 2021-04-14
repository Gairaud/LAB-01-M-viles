package com.ast.airlinesystem.data;

import com.ast.airlinesystem.logic.Model;
import com.ast.airlinesystem.logic.Reservation;
import com.ast.airlinesystem.logic.User;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static com.ast.airlinesystem.data.UserDao.toUser;

public class ReservationDao {

    DbConnection db = new DbConnection();
    PreparedStatement ps;
    ResultSet rs;
    Connection con;

    public List reservationsDao(){
        List<Reservation> list = new ArrayList<>();
        String sql = "SELECT * FROM RESERVATIONS r, USERS u where c.userid=u.id";
        try{
            con = db.Connect();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()){
                Reservation r = new Reservation();
                r.setId(Integer.parseInt(rs.getString(1)));
                r.setTotalPrice(Float.parseFloat(rs.getString(3)));
                r.setSeatQuantity(Integer.parseInt(rs.getString(4)));
                r.setUser(toUser(rs));
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

}
