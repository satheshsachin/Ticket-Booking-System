package repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import bean.Concert;
import bean.Movie;
import bean.Sports;
import entity.Booking;
import entity.Event;
import entity.Venue;
import exception.EventNotFoundException;
import exception.InvalidBookingIDException;
import service.IBookingServiceProvider;
import service.IEventServiceProvider;
import util.DBConnUtil;

public class BookingSystemRepositoryImpl implements IEventServiceProvider, IBookingServiceProvider {

    private Connection conn;

    public BookingSystemRepositoryImpl() {
        conn = DBConnUtil.getConnection("db.properties");
    }

    // 🔍 Fetch all events and return correct subclass
    @Override
    public List<Event> getAllEvents() {
        List<Event> list = new ArrayList<>();

        String sql = "SELECT e.*, v.venue_name, v.address " +
                "FROM event e JOIN venu v ON e.venue_id = v.venue_id";




        try (PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                int eventId = rs.getInt("event_id");
                String name = rs.getString("event_name");
                Date date = rs.getDate("event_date");
                Time time = rs.getTime("event_time");
                int total = rs.getInt("total_seats");
                int avail = rs.getInt("available_seats");
                double price = rs.getDouble("ticket_price");
                String type = rs.getString("event_type");

                // Venue from joined data
                Venue venue = new Venue(
                	    rs.getInt("venue_id"),
                	    rs.getString("venue_name"),
                	    rs.getString("address"),
                	    0 // dummy value because capacity column doesn't exist
                	);


                // Map to subclass
                switch (type.toLowerCase()) {
                    case "movie":
                        list.add(new Movie(eventId, name, venue, date, time, total, avail, price, type, "Action", "Director XYZ"));
                        break;
                    case "concert":
                        list.add(new Concert(eventId, name, venue, date, time, total, avail, price, type, "Performer XYZ", "Rock"));
                        break;
                    case "sports":
                        list.add(new Sports(eventId, name, venue, date, time, total, avail, price, type, "Cricket", "Team A vs Team B"));
                        break;
                    default:
                        break;
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return list;
    }

    // ✅ Add booking
    @Override
    public boolean bookEvent(Booking booking) {
        String sql = "INSERT INTO booking (booking_id, event_id, num_tickets, total_cost, booking_date, customer_id) VALUES (?, ?, ?, ?, ?, ?)";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, booking.getBookingId());
            ps.setInt(2, booking.getEventId()); // event_id
            ps.setInt(3, booking.getNumOfTickets()); // num_tickets

            double totalCost = booking.getNumOfTickets() * getEventById(booking.getEventId()).getTicketPrice();
            ps.setDouble(4, totalCost); // total_cost

            ps.setDate(5, booking.getBookingDate()); // booking_date
            ps.setInt(6, booking.getCustomerId()); // customer_id

            return ps.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }




    // ❌ Cancel booking
    @Override
    public boolean cancelBooking(int bookingId) throws InvalidBookingIDException {
        String sql = "DELETE FROM booking WHERE booking_id=?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, bookingId);
            int rows = ps.executeUpdate();
            if (rows == 0) throw new InvalidBookingIDException("Booking ID " + bookingId + " not found.");
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            throw new InvalidBookingIDException("Error cancelling booking ID " + bookingId);
        }
    }

    // 📋 List all bookings
    @Override
    public List<Booking> getAllBookings() {
        List<Booking> list = new ArrayList<>();
        String sql = "SELECT * FROM booking";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Booking b = new Booking(
                    rs.getInt("booking_id"),
                    rs.getInt("customer_id"),
                    rs.getInt("event_id"),
                    rs.getInt("num_tickets"),
                    rs.getDate("booking_date")
                );
                list.add(b);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    // 🔍 Get booking by ID
    @Override
    public Booking getBookingById(int bookingId) throws InvalidBookingIDException {
        String sql = "SELECT * FROM booking WHERE booking_id=?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, bookingId);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return new Booking(
                    rs.getInt("booking_id"),
                    rs.getInt("customer_id"),
                    rs.getInt("event_id"),
                    rs.getInt("num_tickets"),
                    rs.getDate("booking_date")
                );
            } else {
                throw new InvalidBookingIDException("Booking ID " + bookingId + " not found.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new InvalidBookingIDException("Database error while retrieving booking.");
        }
    }

    // 🔍 Get event by ID
    @Override
    public Event getEventById(int eventId) throws EventNotFoundException {
    	String sql = "SELECT e.*, v.venue_name, v.address " +
                "FROM event e JOIN venu v ON e.venue_id = v.venue_id WHERE e.event_id = ?";


        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, eventId);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                String name = rs.getString("event_name");
                Date date = rs.getDate("event_date");
                Time time = rs.getTime("event_time");
                int total = rs.getInt("total_seats");
                int avail = rs.getInt("available_seats");
                double price = rs.getDouble("ticket_price");
                String type = rs.getString("event_type");

                Venue venue = new Venue(
                	    rs.getInt("venue_id"),
                	    rs.getString("venue_name"),
                	    rs.getString("address"),
                	    0  // dummy value for capacity
                	);


                switch (type.toLowerCase()) {
                    case "movie":
                        return new Movie(eventId, name, venue, date, time, total, avail, price, type, "Action", "Director XYZ");
                    case "concert":
                        return new Concert(eventId, name, venue, date, time, total, avail, price, type, "Performer XYZ", "Rock");
                    case "sports":
                        return new Sports(eventId, name, venue, date, time, total, avail, price, type, "Cricket", "Team A vs Team B");
                    default:
                        break;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        throw new EventNotFoundException("Event ID " + eventId + " not found.");
    }
}
