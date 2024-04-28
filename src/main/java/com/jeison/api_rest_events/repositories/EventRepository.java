package com.jeison.api_rest_events.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.jeison.api_rest_events.models.Event;

@Repository
public interface EventRepository extends JpaRepository<Event,String>{

    public List<Event> findByNameContaining(String name);

}
