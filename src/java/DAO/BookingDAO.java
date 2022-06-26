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
    
    public ArrayList<Booking>  getBookings(){
        try {
            ArrayList<Booking> bookings = new ArrayList<>();
            String sql = "SELECT * FROM booking";
            rs = stm.executeQuery(sql);
            while(rs.next()){
                int bookingid = rs.getInt(1);
                String bookingEmail = rs.getString(2);
                String bookingName = rs.getString(3);
                String bookingService = rs.getString(4);
                Date bookingDate = rs.getDate(5);
                String bookingNote = rs.getString(6);
                bookings.add(new Booking(bookingid,bookingEmail, bookingName, bookingService, bookingDate, bookingNote));
            }  
            return bookings;
        } catch (SQLException ex) {
            Logger.getLogger(CartDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    public boolean  insertBooking(Booking booking){
        try {
            String sql = "Insert into booking values(? , ? , ? , ? , ?)";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, booking.getBookingEmail());
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
