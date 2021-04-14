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


public class TicketDao {

    DbConnection db = new DbConnection();
    PreparedStatement ps;
    ResultSet rs;
    Connection con;

    public List ticketList(){
        List<Ticket> list = new ArrayList<>();
        String sql = "select * from ticket t, routes r";
        try{
            con = db.Connect();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()){
                Ticket t = new Ticket();
                //Routes r = new Routes();
                list.add(t);
                t.setId(Integer.parseInt(rs.getString(1)));
                t.setRow(Integer.parseInt(rs.getString(2)));
                t.setCol(Integer.parseInt(rs.getString(3)));
                t.setRoute(Model.instance().getRoute(rs.getString(4)));

                
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }
}
