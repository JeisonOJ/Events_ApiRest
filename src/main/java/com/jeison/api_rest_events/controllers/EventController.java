package com.jeison.api_rest_events.controllers;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.jeison.api_rest_events.models.Event;
import com.jeison.api_rest_events.services.IEventService;

@RestController
@RequestMapping("api/event")
public class EventController {

    @Autowired
    private IEventService iEventService;

    @GetMapping({ "", "/" })
    @CrossOrigin(origins = "http://localhost:5173")
    public ResponseEntity<List<Event>> getAllEvents(@RequestParam(defaultValue = "1") int page, @RequestParam(defaultValue = "3") int size) {
        return ResponseEntity.ok(iEventService.getAllPaginated(page - 1,size).toList());
    }
    

    @GetMapping("search/{id}")
    public ResponseEntity<Event> getEventById(@PathVariable String id) {
        return ResponseEntity.ok(iEventService.findById(id));
    }

    @GetMapping("search/")
    @CrossOrigin(origins = "http://localhost:5173")
    public ResponseEntity<List<Event>> getEventByName(@RequestParam(required = false) String name) {
        return ResponseEntity.ok(iEventService.findByName(name));
    }

    @PostMapping("create")
    public ResponseEntity<Event> createEvent(@RequestBody Event event) {
        if (dateValidation(event.getDate()) && capacityValidation(event.getCapacity())) {
            Event eventCreated = iEventService.insert(event);
            return new ResponseEntity<>(eventCreated, HttpStatusCode.valueOf(201));
        }else{
            return ResponseEntity.badRequest().build();
        }
    }

    @PutMapping("update/{id}")
    public ResponseEntity<Event> updateEvent(@PathVariable String id, @RequestBody Event event) {
        if (dateValidation(event.getDate())&& capacityValidation(event.getCapacity())) {
            return ResponseEntity.ok(iEventService.update(id,event));
        }else{
            return ResponseEntity.badRequest().build();
        }
    }

    @DeleteMapping("delete/{id}")
    public ResponseEntity<Event> deleteEvent(@PathVariable String id) {
        iEventService.delete(id);
        return ResponseEntity.noContent().build();
    }

    public boolean dateValidation(LocalDate date){
        LocalDate today = LocalDate.now();
        System.out.println(today);
        if (!date.isBefore(today)) return true;
        
        return false;
    }

    public boolean capacityValidation(int capacity){
        if(capacity<=0)return false;
        return true;
    }

}
