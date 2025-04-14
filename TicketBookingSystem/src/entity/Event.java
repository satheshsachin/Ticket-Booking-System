package entity;

import entity.Venue;

public abstract class Event {
    private int eventId;
    private String eventName;
    private Venue venue;
    private java.sql.Date eventDate;
    private java.sql.Time eventTime;
    private int totalSeats;
    private int availableSeats;
    private double ticketPrice;
    private String eventType;

    public Event(int eventId, String eventName, Venue venue, java.sql.Date eventDate, java.sql.Time eventTime,
                 int totalSeats, int availableSeats, double ticketPrice, String eventType) {
        this.eventId = eventId;
        this.eventName = eventName;
        this.venue = venue;
        this.eventDate = eventDate;
        this.eventTime = eventTime;
        this.totalSeats = totalSeats;
        this.availableSeats = availableSeats;
        this.ticketPrice = ticketPrice;
        this.eventType = eventType;
    }

    public int getEventId() {
        return eventId;
    }

    public String getEventName() {
        return eventName;
    }

    public Venue getVenue() {
        return venue;
    }

    public java.sql.Date getEventDate() {
        return eventDate;
    }

    public java.sql.Time getEventTime() {
        return eventTime;
    }

    public int getTotalSeats() {
        return totalSeats;
    }

    public int getAvailableSeats() {
        return availableSeats;
    }

    public double getTicketPrice() {
        return ticketPrice;
    }

    public String getEventType() {
        return eventType;
    }

    public void displayEventDetails() {
        System.out.println("Event ID: " + eventId);
        System.out.println("Name: " + eventName);
        System.out.println("Date: " + eventDate);
        System.out.println("Time: " + eventTime);
        System.out.println("Venue ID: " + (venue != null ? venue.getVenueId() : "N/A"));
        System.out.println("Total Seats: " + totalSeats);
        System.out.println("Available Seats: " + availableSeats);
        System.out.println("Ticket Price: ₹" + ticketPrice);
        System.out.println("Event Type: " + eventType);
        System.out.println("---------------------------");
    }

    @Override
    public String toString() {
        return "Event [ID=" + eventId + ", Name=" + eventName + ", Venue=" + venue + "]";
    }
}