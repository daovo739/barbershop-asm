/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import db.ConnectDB;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Booking;


/**
 *
 * @author HHPC
 */
public class BookingDAO {
    private final Connection conn;
    private final Statement stm;
    private ResultSet rs;
    
    public BookingDAO() throws SQLException {
        conn = new ConnectDB().getConn();
        stm = conn.createStatement();
    }
    
    public ArrayList<Booking>  getBookings(String filter, String today){
        String sql = null;
        if(filter == null) {
            sql = "SELECT * FROM booking order by booking_date asc";
        } else {
            if (filter.equalsIgnoreCase("completed")) {
                sql = "SELECT * FROM booking where booking_date < '" + today + "' order by booking_date asc";
            } else if (filter.equalsIgnoreCase("uncompleted")) {
                sql = "SELECT * FROM booking where booking_date >= '" + today + "' order by booking_date asc";
            }
        }
        try {
            ArrayList<Booking> bookings = new ArrayList<>();
            rs = stm.executeQuery(sql);
            while(rs.next()){
                int bookingid = rs.getInt(1);
                String bookingEmail = rs.getString(2);
                String bookingName = rs.getString(3);
                String bookingService = rs.getString(4);
                Timestamp timestamp = rs.getTimestamp(5);
                String bookingNote = rs.getString(6);
                bookings.add(new Booking(bookingid,bookingEmail, bookingName, bookingService, timestamp, bookingNote));
            }  
            return bookings;
        } catch (SQLException ex) {
            Logger.getLogger(CartDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    public boolean  insertBooking(Booking booking){
        try {
            String sql = "Insert into booking (booking_phone, booking_name, booking_service, booking_date, booking_note) values(? , ? , ? , ? , ?)";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, booking.getBookingPhone());
            ps.setString(2, booking.getBookingName());
            ps.setString(3, booking.getBookingService());
            ps.setString(4, booking.getBookingDate());
            ps.setString(5, booking.getBookingNote());
            ps.execute();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(BookingDAO.class.getName()).log(Level.SEVERE, null, ex);
            return  false;
        }
    }
}
