package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.List;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.NoteList;
import seedu.address.model.person.Person;

public class NoteCommand extends Command {

    public static final String COMMAND_WORD = ":note";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds a note to the person identified "
            + "by the index number used in the displayed person list. "
            + "The note will be appended to the person's existing notes.\n"
            + "Parameters: INDEX (must be a positive integer) NOTE\n"
            + "Example: " + COMMAND_WORD + " 1 met him at networking event";

    public static final String MESSAGE_SUCCESS = "Added note to: %1$s";

    private final Index index;
    private final String note;

    public NoteCommand(Index index, String note) {
        this.index = index;
        this.note = note;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        List<Person> lastShownList = model.getFilteredPersonList();

        if (index.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
        }

        Person personToEdit = lastShownList.get(index.getZeroBased());

        NoteList updatedNotes = personToEdit.appendNote(note);

        Person editedPerson = new Person(
                personToEdit.getName(),
                personToEdit.getPhone(),
                personToEdit.getEmail(),
                personToEdit.getAddress(),
                personToEdit.getTags(),
                updatedNotes
        );

        model.setPerson(personToEdit, editedPerson);

        return new CommandResult(String.format(MESSAGE_SUCCESS, editedPerson));
    }
}
