package bean;

import entity.Event;
import entity.Venue;

import java.sql.Date;
import java.sql.Time;

public class Sports extends Event {
    private String sportType;
    private String teams;

    public Sports(int eventId, String eventName, Venue venue, Date eventDate, Time eventTime,
                  int totalSeats, int availableSeats, double ticketPrice, String eventType,
                  String sportType, String teams) {
        super(eventId, eventName, venue, eventDate, eventTime, totalSeats, availableSeats, ticketPrice, eventType);
        this.sportType = sportType;
        this.teams = teams;
    }

    public String getSportType() {
        return sportType;
    }

    public String getTeams() {
        return teams;
    }

    @Override
    public void displayEventDetails() {
        super.displayEventDetails();
        System.out.println("Sport Type: " + sportType);
        System.out.println("Teams: " + teams);
    }
}