package com.jeison.api_rest_events.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jeison.api_rest_events.models.Event;
import com.jeison.api_rest_events.repositories.EventRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class EventService implements IEventService{

    @Autowired
    private final EventRepository eventRepository;

    @Override
    public List<Event> findAll() {
        return eventRepository.findAll();
    }

    @Override
    public Event findById(String id) {
        return eventRepository.findById(id).orElseThrow();
    }

    @Override
    public Event insert(Event event) {
        return eventRepository.save(event);
    }

    @Override
    public Event update(String id, Event event) {
        findById(id);
        event.setId(id);
        return eventRepository.save(event);
    }

    @Override
    public void delete(String id) {
        findById(id);
        eventRepository.deleteById(id);
    }

}
