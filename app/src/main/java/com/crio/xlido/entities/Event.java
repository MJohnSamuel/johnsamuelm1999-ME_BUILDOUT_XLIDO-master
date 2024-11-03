package com.crio.xlido.entities;

public class Event {
    private Long userId;
    private final Long eventId;
    private String name;


    public Event(long userId, String name) {
        this.eventId = null;
        this.userId = userId;
        this.name = name;
    }

    public Long getUserId() {
        return userId;
    }

    public String getName() {
        return name;
    }

    public Long getEventId() {
        return eventId;
    }

    public Event(long eventId,Event entity){
        this.eventId = eventId;
        this.userId = entity.userId;
        this.name = entity.name;
    }

    
    @Override
    public String toString(){
        return "Event ID: " + eventId;
    }
}
