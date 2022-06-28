/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author HHPC
 */
public class Booking {
    
    private int bookingId;
    private String bookingPhone, bookingName, bookingService, bookingDate, bookingNote;

    public Booking(String bookingPhone, String bookingName, String bookingService, String bookingDate, String bookingNote) {
        this.bookingPhone = bookingPhone;
        this.bookingName = bookingName;
        this.bookingService = bookingService;
        this.bookingDate = bookingDate;
        this.bookingNote = bookingNote;
    }

    public Booking(int bookingId, String bookingPhone, String bookingName, String bookingService, Date bookingDate, String bookingNote) {
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
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
            Date date = formatter.parse(bookingDate);
            String newBookingDate = formatter.format(date);
            return newBookingDate;
        } catch (ParseException ex) {
            Logger.getLogger(Booking.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public void setBookingDate(Date bookingDate) {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        String newBookingDate = formatter.format(bookingDate);
        this.bookingDate = newBookingDate;
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

    
}
