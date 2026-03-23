package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import java.util.Arrays;
import java.util.Collections;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.ListCommand;
import seedu.address.model.person.EmailContainsKeywordPredicate;
import seedu.address.model.person.ListCommandPredicate;
import seedu.address.model.person.TagContainsKeywordPredicate;

public class ListCommandParserTest {

    private ListCommandParser parser = new ListCommandParser();

    @Test
    public void parse_emptyArg_returnsListAllCommand() {
        assertParseSuccess(parser, "", new ListCommand());
        assertParseSuccess(parser, "   ", new ListCommand());
    }

    @Test
    public void parse_invalidArgs_throwsParseException() {
        // no t/ prefix
        assertParseFailure(parser, "friend",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, ListCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_validSingleTag_returnsListCommand() {
        TagContainsKeywordPredicate tagPredicate = new TagContainsKeywordPredicate(Collections.singletonList("friends"));
        ListCommand expectedCommand = new ListCommand(new ListCommandPredicate(tagPredicate, null));
        assertParseSuccess(parser, " t/friends", expectedCommand);
    }

    @Test
    public void parse_validMultipleTags_returnsListCommand() {
        TagContainsKeywordPredicate tagPredicate = new TagContainsKeywordPredicate(Arrays.asList("friends", "owesMoney"));
        ListCommand expectedCommand = new ListCommand(new ListCommandPredicate(tagPredicate, null));
        assertParseSuccess(parser, " t/friends t/owesMoney", expectedCommand);
    }

    @Test
    public void parse_validSingleEmail_returnsListCommand() {
        EmailContainsKeywordPredicate emailPredicate = new EmailContainsKeywordPredicate(Collections.singletonList("gmail"));
        ListCommand expectedCommand = new ListCommand(new ListCommandPredicate(null, emailPredicate));
        assertParseSuccess(parser, " e/gmail", expectedCommand);
    }

    @Test
    public void parse_validTagAndEmail_returnsListCommand() {
        TagContainsKeywordPredicate tagPredicate = new TagContainsKeywordPredicate(Collections.singletonList("friends"));
        EmailContainsKeywordPredicate emailPredicate = new EmailContainsKeywordPredicate(Collections.singletonList("gmail"));
        ListCommand expectedCommand = new ListCommand(new ListCommandPredicate(tagPredicate, emailPredicate));
        assertParseSuccess(parser, " t/friends e/gmail", expectedCommand);
    }
}
