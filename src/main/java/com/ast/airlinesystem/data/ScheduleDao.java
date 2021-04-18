package com.ast.airlinesystem.data;

import com.ast.airlinesystem.logic.Schedule;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ScheduleDao {
    DbConnection db = new DbConnection();
    PreparedStatement ps;
    ResultSet rs;
    Connection con;


    public List ScheduleList(){
        List<Schedule> list = new ArrayList<>();
        String sql = "SELECT * FROM schedules";
        try{
            con = db.Connect();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()){
                Schedule at = new Schedule();
                at.setId(Integer.parseInt(rs.getString(1)));
                at.setArrivalTime(rs.getString(2));
                at.setDepartureTime(rs.getString(3));
                list.add(at);
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }
    public int addSchedule (Schedule schedule) throws  Exception{
        String insertStatement = "CALL PRC_INS_schedule(?,?,?)";
        int count = 0;
        try{
            con = db.Connect();
            ps = con.prepareStatement(insertStatement);
            ps.setString(1, schedule.getDay());
            ps.setString(2, schedule.getDepartureTime());
            ps.setString(3, schedule.getArrivalTime());
            count = ps.executeUpdate();
            if(count == 0){
                throw new Exception("El usuario ya existe");
            }

        }
        catch (Exception e){

        }
        return count;
    }

    public Schedule getSchedule(String id){
        Schedule schedule = new Schedule();
        String getStatement = "SELECT * FROM schedules where id =\'"+id+"\'";
        try{
            con = db.Connect();
            ps = con.prepareStatement(getStatement);
            rs = ps.executeQuery();
            while (rs.next()){
                schedule.setId(Integer.parseInt(rs.getString(1)));
                schedule.setDepartureTime(rs.getString(2));
                schedule.setArrivalTime(rs.getString(3));
            }
        } catch (SQLException ex) {

        }
        return schedule;
    }

    public void updateSchedule(Schedule schedule){

        String updateStatement = "CALL prc_upd_schedule(?,?,?)";
        try{
            con = db.Connect();
            ps = con.prepareStatement(updateStatement);
            ps.setString(1, Integer.toString(schedule.getId()));
            ps.setString(2, schedule.getDepartureTime());
            ps.setString(3, schedule.getArrivalTime());
            ps.executeUpdate(updateStatement);

        }
        catch (Exception e){

        }

    }
}
