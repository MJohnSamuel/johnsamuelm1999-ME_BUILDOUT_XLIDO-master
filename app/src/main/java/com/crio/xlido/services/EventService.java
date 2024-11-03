package com.crio.xlido.services;

import java.util.List;
import java.util.Optional;
import com.crio.xlido.Repositories.EventRepository;
import com.crio.xlido.Repositories.UserRepository;
import com.crio.xlido.entities.Event;
import com.crio.xlido.entities.User;

public class EventService {

    private EventRepository eventRepository;
    private UserRepository userRepository;

    public EventService(EventRepository eventRepository,UserRepository userRepository){
        this.eventRepository = eventRepository;
        this.userRepository = userRepository;
    }

    public String createEvent(List<String> tokens){
        String name = tokens.get(1);
        Optional<User> user = userRepository.findById(Long.valueOf(tokens.get(2)));
        if(!user.isPresent()){
            return "ERROR: User with an id "+ Long.valueOf(tokens.get(2))  + " does not exist";
        }
        Event event = eventRepository.save(new Event(Long.valueOf(tokens.get(2)),name));
        return event.toString();
    }

    public String deleteEvent(List<String> tokens){
        Optional<Event> event = eventRepository.findById(Long.valueOf(tokens.get(1)));
        if(!event.isPresent()){
            return "ERROR: Event with an id " + tokens.get(1) + " does not exist";
        }
        Optional<User> user = userRepository.findById(Long.valueOf(tokens.get(2)));
        if(!user.isPresent()){
            return "ERROR: User with an id " + tokens.get(2) + " does not exist";
        }
        if(event.get().getUserId().toString().equals(tokens.get(2))){
            eventRepository.delete(event.get());
            return "EVENT_DELETED " + event.get().getEventId();
        }

        return "ERROR: User with an id "+ user.get().getId() + " is not a organizer of Event with an id "+ event.get().getEventId();
    }
}
