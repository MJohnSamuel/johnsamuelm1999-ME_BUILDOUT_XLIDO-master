package com.crio.xlido.Repositories;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;
import com.crio.xlido.entities.Event;

public class EventRepository {

    private AtomicLong id = new AtomicLong(0);
    private final Map<Long , Event> storage = new HashMap<>();

    public Event save(Event entity){
        Event event = new Event(id.incrementAndGet(),entity);
        storage.putIfAbsent(event.getEventId(), event);
        return event;
    }

    public void delete(Event entity){
        storage.remove(entity.getEventId());
    }

    public Optional<Event> findById(Long id){
        return Optional.ofNullable(storage.get(id));
    }

}