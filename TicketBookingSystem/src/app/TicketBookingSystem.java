package app;

import java.sql.Date;
import java.util.List;
import java.util.Scanner;

import entity.Booking;
import entity.Event;
import exception.EventNotFoundException;
import exception.InvalidBookingIDException;
import repository.BookingSystemRepositoryImpl;
import service.IBookingServiceProvider;
import service.IEventServiceProvider;

public class TicketBookingSystem {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        IEventServiceProvider eventService = new BookingSystemRepositoryImpl();
        IBookingServiceProvider bookingService = (IBookingServiceProvider) eventService;

        int choice;

        do {
            System.out.println("\n===== TICKET BOOKING SYSTEM MENU =====");
            System.out.println("1. View All Events");
            System.out.println("2. View Event By ID");
            System.out.println("3. Book Event");
            System.out.println("4. Cancel Booking");
            System.out.println("5. View All Bookings");
            System.out.println("6. View Booking By ID");
            System.out.println("7. Exit");
            System.out.print("Enter your choice: ");

            choice = sc.nextInt();
            sc.nextLine(); // Consume newline

            try {
                switch (choice) {
                    case 1:
                        List<Event> events = eventService.getAllEvents();
                        events.forEach(Event::displayEventDetails);
                        break;

                    case 2:
                        System.out.print("Enter Event ID: ");
                        int eid = sc.nextInt();
                        Event e = eventService.getEventById(eid);
                        e.displayEventDetails();
                        break;

                    case 3:
                        System.out.print("Enter Booking ID: ");
                        int bid = sc.nextInt();
                        System.out.print("Enter Customer ID: ");
                        int cid = sc.nextInt();
                        System.out.print("Enter Event ID: ");
                        int eventid = sc.nextInt();
                        System.out.print("Enter No. of Tickets: ");
                        int tickets = sc.nextInt();
                        sc.nextLine();
                        System.out.print("Enter Booking Date (yyyy-mm-dd): ");
                        Date date = Date.valueOf(sc.nextLine());

                        Booking b = new Booking(bid, cid, eventid, tickets, date);
                        boolean booked = bookingService.bookEvent(b);
                        System.out.println(booked ? "✅ Booking successful!" : "❌ Booking failed.");
                        break;

                    case 4:
                        System.out.print("Enter Booking ID to cancel: ");
                        int cancelId = sc.nextInt();
                        boolean cancelled = bookingService.cancelBooking(cancelId);
                        System.out.println(cancelled ? "✅ Booking cancelled." : "❌ Booking not found.");
                        break;

                    case 5:
                        List<Booking> bookings = bookingService.getAllBookings();
                        bookings.forEach(System.out::println);
                        break;

                    case 6:
                        System.out.print("Enter Booking ID: ");
                        int searchId = sc.nextInt();
                        Booking found = bookingService.getBookingById(searchId);
                        System.out.println(found);
                        break;

                    case 7:
                        System.out.println("Exiting system. 👋");
                        break;

                    default:
                        System.out.println("Invalid choice. Try again.");
                }

            } catch (EventNotFoundException | InvalidBookingIDException ex) {
                System.out.println("❗ " + ex.getMessage());
            } catch (Exception ex) {
                System.out.println("❌ Unexpected error: " + ex.getMessage());
                ex.printStackTrace();
            }

        } while (choice != 7);

        sc.close();
    }
}
