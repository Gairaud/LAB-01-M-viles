package com.ast.airlinesystem.data;

import com.ast.airlinesystem.logic.Ticket;
import com.ast.airlinesystem.logic.Model;
import com.ast.airlinesystem.logic.Routes;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static com.ast.airlinesystem.data.ReservationDao.toReservation;;

public class TicketDao {

    DbConnection db = new DbConnection();
    PreparedStatement ps;
    ResultSet rs;
    Connection con;

    public List ticketList(){
        List<Ticket> list = new ArrayList<>();
        String sql = "select * from ticket t, reservation r, users u where" 
         +" t.reservation = r.res_id and r.userid = u.user_id";
        try{
            con = db.Connect();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()){
                Ticket t = new Ticket();
                //Routes r = new Routes();
                t.setId(Integer.parseInt(rs.getString(1)));
                t.setRow(Integer.parseInt(rs.getString(2)));
                t.setCol(Integer.parseInt(rs.getString(3)));
                t.setReservation(toReservation(rs));
                list.add(t);

                
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public int addTicket(Ticket ticket){
        String insertStatement = "CALL PRC_INS_TICKET(?,?,?)";
        int count = 0;
        try{
            con = db.Connect();
            ps = con.prepareStatement(insertStatement);
            ps.setString(1, String.valueOf(ticket.getRow()));
            ps.setString(2, String.valueOf(ticket.getCol()));
            ps.setString(3, String.valueOf(ticket.getReservation().getId()));
            count = ps.executeUpdate();
            if(count == 0){
                throw new Exception("El ticket ya existe");
            }

        }
        catch (Exception e){

        }
        return count;
    }
}
