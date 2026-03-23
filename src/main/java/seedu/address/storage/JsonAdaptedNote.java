package seedu.address.storage;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.person.Note;

public class JsonAdaptedNote {

    private final String content;

    public JsonAdaptedNote(String content) {
        this.content = content;
    }

    public JsonAdaptedNote(Note source) {
        this.content = source.toString();
    }

    public Note toModelType() throws IllegalValueException {
        if (content == null) {
            throw new IllegalValueException("Note cannot be null");
        }

        return new Note(content);
    }
}