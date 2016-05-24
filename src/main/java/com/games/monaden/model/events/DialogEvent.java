package com.games.monaden.model.events;

import com.games.monaden.model.Dialog;
import com.games.monaden.model.Event;

/**
 * Created by Philip on 2016-05-24.
 * Event for dialogs. When this event is triggered, a dialog should open.
 */
public class DialogEvent implements Event{

    Dialog dialog;

    @Override
    public Object getEventContent() {
        return dialog;
    }

    @Override
    public void setEventContent(Object object) {
        if (object instanceof Dialog) {
            this.dialog = (Dialog) object;
        } else {
            throw new IllegalArgumentException("DialogEvent.setEventContent() expected dialog object.");
        }
    }
}
