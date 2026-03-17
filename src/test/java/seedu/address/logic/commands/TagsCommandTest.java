package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import seedu.address.model.Model;
import seedu.address.model.ModelManager;

/**
 * Contains integration tests (interaction with the Model) for {@code TagsCommand}.
 */
public class TagsCommandTest {

    @Test
    public void execute_noTags_returnsNoTagsMessage() {
        Model model = new ModelManager(); // empty model

        TagsCommand command = new TagsCommand();
        CommandResult result = command.execute(model);

        assertEquals(TagsCommand.MESSAGE_NO_TAGS, result.getFeedbackToUser());
    }

    @Test
    public void execute_withTags_returnsFormattedTags() {
        ModelManager model = new ModelManager();

        // Manually simulate tags existing
        // (since command depends on model.getFormattedTags())
        model.addTags(seedu.address.testutil.TypicalPersons.ALICE);

        TagsCommand command = new TagsCommand();
        CommandResult result = command.execute(model);

        String feedback = result.getFeedbackToUser();

        // Basic checks (avoid hardcoding exact string unless you want strict test)
        assertEquals(true, feedback.startsWith("Tags:"));
    }
}
