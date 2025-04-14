package service;

import entity.Booking;
import java.util.List;
import exception.InvalidBookingIDException;

public interface IBookingServiceProvider {
    boolean bookEvent(Booking booking);
    boolean cancelBooking(int bookingId) throws InvalidBookingIDException;
    List<Booking> getAllBookings();
    Booking getBookingById(int bookingId) throws InvalidBookingIDException;
}
