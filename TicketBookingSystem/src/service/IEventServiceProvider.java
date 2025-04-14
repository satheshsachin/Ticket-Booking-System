package service;

import entity.Event;
import java.util.List;

public interface IEventServiceProvider {
    List<Event> getAllEvents();
    Event getEventById(int eventId) throws exception.EventNotFoundException;
}
