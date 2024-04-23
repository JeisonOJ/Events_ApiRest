package com.jeison.api_rest_events.services;

import java.util.List;

import com.jeison.api_rest_events.models.Event;

public interface IEventService {

    public List<Event> findAll();
    public Event findById(String id);
    public Event insert(Event event);
    public Event update(String id, Event event);
    public void delete(String id);
}
