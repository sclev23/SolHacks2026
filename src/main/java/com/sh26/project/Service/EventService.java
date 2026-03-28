package com.sh26.project.Service;

import com.sh26.project.Model.Event;
import com.sh26.project.Repository.EventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class EventService {

    @Autowired
    private EventRepository eventRepository;

    // Get every event, sorted by date then start time
    public List<Event> getAllEvents() {
        return eventRepository.findAllByOrderByDateAscStartTimeAsc();
    }

    // Get all events on a specific date
    public List<Event> getEventsByDate(LocalDate date) {
        return eventRepository.findByDate(date);
    }

    // Get a single event by its ID
    public Optional<Event> getEventById(Long id) {
        return eventRepository.findById(id);
    }

    // Create a new event
    public Event createEvent(Event event) {
        validateEvent(event);
        return eventRepository.save(event);
    }

    // Update an existing event
    public Event updateEvent(Long id, Event updatedEvent) {
        // Check the event exists first
        eventRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Event not found with id: " + id));

        validateEvent(updatedEvent);
        updatedEvent.setId(id);
        return eventRepository.save(updatedEvent);
    }

    // Delete an event
    public void deleteEvent(Long id) {
        eventRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Event not found with id: " + id));
        eventRepository.deleteById(id);
    }

    // --- Validation ---
    private void validateEvent(Event event) {
        if (event.getTitle() == null || event.getTitle().isBlank()) {
            throw new IllegalArgumentException("Event must have a title");
        }
        if (event.getDate() == null) {
            throw new IllegalArgumentException("Event must have a date");
        }
        if (event.getStartTime() == null || event.getEndTime() == null) {
            throw new IllegalArgumentException("Event must have a start and end time");
        }
        if (!event.getEndTime().isAfter(event.getStartTime())) {
            throw new IllegalArgumentException("End time must be after start time");
        }
    }
}
