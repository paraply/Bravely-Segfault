package com.games.monaden.model.events;

/**
 * Created by Philip on 2016-05-24.
 */
public interface Event {
    Object getEventContent();
    void setEventContent(Object object);
}
