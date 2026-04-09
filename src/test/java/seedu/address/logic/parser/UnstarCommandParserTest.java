package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.UnstarCommand;
import seedu.address.logic.parser.exceptions.ParseException;

public class UnstarCommandParserTest {

    private final UnstarCommandParser parser = new UnstarCommandParser();

    @Test
    public void parse_validArgs_returnsUnstarCommand() throws ParseException {
        // Valid index
        Index validIndex = INDEX_FIRST_PERSON;
        // Valid input
        String userInput = "1";

        UnstarCommand expectedCommand = new UnstarCommand(validIndex);
        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_invalidArgs_throwsParseException() {
        // Invalid index
        String userInput = "abc";
        assertParseFailure(parser, userInput, String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                UnstarCommand.MESSAGE_USAGE));

        // Empty input
        userInput = "";
        assertParseFailure(parser, userInput, String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                UnstarCommand.MESSAGE_USAGE));

        // Whitespace input
        userInput = "   ";
        assertParseFailure(parser, userInput, String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                UnstarCommand.MESSAGE_USAGE));

        // Invalid index (zero index)
        userInput = "0";
        assertParseFailure(parser, userInput, String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                UnstarCommand.MESSAGE_USAGE));

        // Invalid index (negative index)
        userInput = "-1";
        assertParseFailure(parser, userInput, String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                UnstarCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_missingParts_failure() {
        // No index specified
        assertParseFailure(parser, "", String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                UnstarCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_invalidPreamble_failure() {
        // Random string before index
        assertParseFailure(parser, "1 some random string", String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                UnstarCommand.MESSAGE_USAGE));

        // Invalid prefix being parsed as preamble
        assertParseFailure(parser, "1 i/ string", String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                UnstarCommand.MESSAGE_USAGE));
    }
}
