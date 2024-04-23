package com.jeison.api_rest_events.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.jeison.api_rest_events.models.Event;
import com.jeison.api_rest_events.services.IEventService;
import org.springframework.web.bind.annotation.PutMapping;

@Controller
@RequestMapping("api/event")
public class EventController {

    @Autowired
    private IEventService iEventService;

    @GetMapping({ "", "/" })
    public ResponseEntity<List<Event>> getAllEvents() {
        return ResponseEntity.ok(iEventService.findAll());
    }

    @GetMapping("search/{id}")
    public ResponseEntity<Event> getEventById(@PathVariable String id) {
        return ResponseEntity.ok(iEventService.findById(id));
    }

    @PostMapping("create")
    public ResponseEntity<Event> createEvent(@RequestBody Event event) {
        Event eventCreated = iEventService.insert(event);
        return new ResponseEntity<>(eventCreated, HttpStatusCode.valueOf(201));
    }

    @PutMapping("update/{id}")
    public ResponseEntity<Event> putMethodName(@PathVariable String id, @RequestBody Event event) {
        return ResponseEntity.ok(iEventService.update(id, event));
    }

    @DeleteMapping("delete/{id}")
    public ResponseEntity<Event> deleteEvent(@PathVariable String id) {
        iEventService.delete(id);
        return ResponseEntity.noContent().build();
    }

}
