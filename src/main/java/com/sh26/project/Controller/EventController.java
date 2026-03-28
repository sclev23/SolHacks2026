package com.sh26.project.Controller;

import com.sh26.project.Model.Event;
import com.sh26.project.Service.EventService;
import com.sh26.project.util.CookieUtil;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/events")
public class EventController {

    @Autowired
    private EventService eventService;

    // GET /events — get all events
    @GetMapping
    public List<Event> getAllEvents(HttpServletRequest request, HttpServletResponse response) {
        String userId = CookieUtil.getOrCreateUserId(request, response);
        return eventService.getAllEvents(userId);
    }

    // GET /events/date/2026-03-28 — get events on a specific date
    @GetMapping("/date/{date}")
    public List<Event> getEventsByDate(@PathVariable @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date, HttpServletRequest request, HttpServletResponse response) {
        String userId = CookieUtil.getOrCreateUserId(request, response);
        return eventService.getEventsByDate(userId, date);
    }

    // GET /events/1 — get a single event by id
    @GetMapping("/{id}")
    public ResponseEntity<Event> getEventById(@PathVariable Long id) {
        return eventService.getEventById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // POST /events — create a new event
    @PostMapping
    public ResponseEntity<Event> createEvent(@RequestBody Event event, HttpServletRequest request, HttpServletResponse response) {
        try {
            String userId = CookieUtil.getOrCreateUserId(request, response);
            return ResponseEntity.ok(eventService.createEvent(userId, event));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    // PUT /events/1 — update an existing event
    @PutMapping("/{id}")
    public ResponseEntity<Event> updateEvent(@PathVariable Long id, @RequestBody Event event, HttpServletRequest request, HttpServletResponse response) {
        try {
            String userId = CookieUtil.getOrCreateUserId(request, response);
            return ResponseEntity.ok(eventService.updateEvent(userId, id, event));
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    // DELETE /events/1 — delete an event
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEvent(@PathVariable Long id) {
        try {
            eventService.deleteEvent(id);
            return ResponseEntity.ok().build();
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }
}
