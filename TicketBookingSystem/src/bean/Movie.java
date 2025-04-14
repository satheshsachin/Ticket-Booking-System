package bean;

import entity.Event;
import entity.Venue;

import java.sql.Date;
import java.sql.Time;

public class Movie extends Event {
    private String genre;
    private String director;

    public Movie(int eventId, String eventName, Venue venue, Date eventDate, Time eventTime,
                 int totalSeats, int availableSeats, double ticketPrice, String eventType,
                 String genre, String director) {
        super(eventId, eventName, venue, eventDate, eventTime, totalSeats, availableSeats, ticketPrice, eventType);
        this.genre = genre;
        this.director = director;
    }

    public String getGenre() {
        return genre;
    }

    public String getDirector() {
        return director;
    }

    @Override
    public void displayEventDetails() {
        super.displayEventDetails();
        System.out.println("Genre: " + genre);
        System.out.println("Director: " + director);
    }
}