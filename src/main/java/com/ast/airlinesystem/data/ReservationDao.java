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

    public List getReservationsByUser(String userid){
        List<Reservation> list = new ArrayList<>();
        String sql = "SELECT * FROM RESERVATION r, USERS u where r.userid=u.user_id and u.user_id = \'"+userid+"\'";
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
                list.add(r);
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public int addReservation(Reservation reservation){
        String insertStatement = "CALL PRC_INS_TICKET(?,?,?)";
        int count = 0;
        try{
            con = db.Connect();
            ps = con.prepareStatement(insertStatement);
            ps.setString(1, String.valueOf(reservation.getId()));
            ps.setString(2, String.valueOf(reservation.getUser().getId()));
            ps.setString(3, String.valueOf(reservation.getTotalPrice()));
            ps.setString(4, String.valueOf(reservation.getSeatQuantity()));
            count = ps.executeUpdate();
            if(count == 0){
                throw new Exception("La reservacion ya existe");
            }

        }
        catch (Exception e){

        }
        return count;
    }

    public static Reservation toReservation(ResultSet rs){

        try{
            Reservation res = new Reservation();

            
                res.setId(Integer.parseInt(rs.getString("res_id")));
                res.setUser(toUser(rs));
                res.setTotalPrice(Float.parseFloat(rs.getString("totalPrice")));
                res.setSeatQuantity(Integer.parseInt(rs.getString("seatQuantity")));
                return res;
            
        } catch (SQLException ex) {
                return null;
        }
    }
}
