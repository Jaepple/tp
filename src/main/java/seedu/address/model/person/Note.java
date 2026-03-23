package seedu.address.model.person;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

public class Note {

    public static final String MESSAGE_CONSTRAINTS = "Note cannot be blank.";

    public final String value;

    public Note(String value) {
        requireNonNull(value);
        checkArgument(!value.isBlank(), MESSAGE_CONSTRAINTS);
        this.value = value;
    }

    @Override
    public String toString() {
        return value;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) return true;
        if (!(other instanceof Note)) return false;

        Note otherNote = (Note) other;
        return value.equals(otherNote.value);
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }
}