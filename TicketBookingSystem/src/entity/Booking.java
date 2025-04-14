package entity;

import java.sql.Date;

public class Booking {
    private int bookingId;
    private int customerId;
    private int eventId;
    private int numOfTickets;
    private Date bookingDate;

    public Booking(int bookingId, int customerId, int eventId, int numOfTickets, Date bookingDate) {
        this.bookingId = bookingId;
        this.customerId = customerId;
        this.eventId = eventId;
        this.numOfTickets = numOfTickets;
        this.bookingDate = bookingDate;
    }

    public int getBookingId() {
        return bookingId;
    }

    public int getCustomerId() {
        return customerId;
    }

    public int getEventId() {
        return eventId;
    }

    public int getNumOfTickets() {
        return numOfTickets;
    }

    public Date getBookingDate() {
        return bookingDate;
    }

    @Override
    public String toString() {
        return "Booking [ID=" + bookingId + ", CustomerID=" + customerId + ", EventID=" + eventId + 
               ", Tickets=" + numOfTickets + ", Date=" + bookingDate + "]";
    }
}