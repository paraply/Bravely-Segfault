package com.games.monaden.model.events;

import com.games.monaden.model.Dialog;
import com.games.monaden.model.Point;

import java.io.File;

/**
 * Created by Philip on 2016-05-24.
 * Event for dialogs. When this event is triggered, a dialog should open.
 */
public class DialogEvent implements Event{

    private Dialog dialog;
    private File filepath;
    private Point position;

    public DialogEvent(File filepath, Point position) {
        this.filepath = filepath;
        this.position = position;
    }

    public File getFilepath() {
        return this.filepath;
    }

    public Point getPosition() {
        return this.position;
    }

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
