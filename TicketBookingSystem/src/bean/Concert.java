package bean;

import entity.Event;
import entity.Venue;

import java.sql.Date;
import java.sql.Time;

public class Concert extends Event {
    private String performer;
    private String musicType;

    public Concert(int eventId, String eventName, Venue venue, Date eventDate, Time eventTime,
                   int totalSeats, int availableSeats, double ticketPrice, String eventType,
                   String performer, String musicType) {
        super(eventId, eventName, venue, eventDate, eventTime, totalSeats, availableSeats, ticketPrice, eventType);
        this.performer = performer;
        this.musicType = musicType;
    }

    public String getPerformer() {
        return performer;
    }

    public String getMusicType() {
        return musicType;
    }

    @Override
    public void displayEventDetails() {
        super.displayEventDetails();
        System.out.println("Performer: " + performer);
        System.out.println("Music Type: " + musicType);
    }
}