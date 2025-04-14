package entity;

public class Venue {
    private int venueId;
    private String name;
    private String location;
    private int capacity;

    public Venue(int venueId, String name, String location, int capacity) {
        this.venueId = venueId;
        this.name = name;
        this.location = location;
        this.capacity = capacity;
    }

    public int getVenueId() {
        return venueId;
    }

    public String getName() {
        return name;
    }

    public String getLocation() {
        return location;
    }

    public int getCapacity() {
        return capacity;
    }

    @Override
    public String toString() {
        return "Venue [ID=" + venueId + ", Name=" + name + ", Location=" + location + ", Capacity=" + capacity + "]";
    }
}