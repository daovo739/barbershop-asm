/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author HHPC
 */
public class Booking implements Comparable<Date>{
    
    private int bookingId;
    private String bookingPhone, bookingName, bookingService, bookingDate, bookingNote;

    public Booking(String bookingPhone, String bookingName, String bookingService, String bookingDate, String bookingNote) {
        this.bookingPhone = bookingPhone;
        this.bookingName = bookingName;
        this.bookingService = bookingService;
        this.bookingDate = bookingDate;
        this.bookingNote = bookingNote;
    }

    public Booking(int bookingId, String bookingPhone, String bookingName, String bookingService, Timestamp bookingDate, String bookingNote) {
        this.bookingId = bookingId;
        this.bookingPhone = bookingPhone;
        this.bookingName = bookingName;
        this.bookingService = bookingService;
        setBookingDate(bookingDate);
        this.bookingNote = bookingNote;
    }

    public int getBookingId() {
        return bookingId;
    }

    public void setBookingId(int bookingId) {
        this.bookingId = bookingId;
    }

    public String getBookingPhone() {
        return bookingPhone;
    }

    public void setBookingPhone(String bookingPhone) {
        this.bookingPhone = bookingPhone;
    }

    public String getBookingName() {
        return bookingName;
    }

    public void setBookingName(String bookingName) {
        this.bookingName = bookingName;
    }

    public String getBookingService() {
        return bookingService;
    }

    public void setBookingService(String bookingService) {
        this.bookingService = bookingService;
    }

    public String getBookingDate() {
        try {
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Date date = formatter.parse(bookingDate);
            String newBookingDate = formatter.format(date);
            return newBookingDate;
        } catch (ParseException ex) {
            Logger.getLogger(Booking.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    public String getBookingDateAllowDay() {
        try {
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Date date = formatter.parse(bookingDate);
            String newBookingDate = new SimpleDateFormat("EEEE").format(date) + ", " + formatter.format(date);
            return newBookingDate;
        } catch (ParseException ex) {
            Logger.getLogger(Booking.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    public void setBookingDate(Timestamp bookingDate) {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String newBookingDate = formatter.format(bookingDate);
        this.bookingDate = newBookingDate;
    }

    public Date getDate() {
        try {
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Date date = formatter.parse(this.bookingDate);
            return date;
        } catch (ParseException ex) {
            Logger.getLogger(Booking.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    public String getBookingNote() {
        return bookingNote;
    }

    public void setBookingNote(String bookingNote) {
        this.bookingNote = bookingNote;
    }

    @Override
    public String toString() {
        return "Booking{" + "bookingPhone=" + bookingPhone + ", bookingName=" + bookingName + ", bookingService=" + bookingService + ", bookingDate=" + getBookingDate() + ", bookingNote=" + bookingNote + '}';
    }

    public int compareTo() {
        return new Date().compareTo(getDate());
    }

    @Override
    public int compareTo(Date o) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    
}
