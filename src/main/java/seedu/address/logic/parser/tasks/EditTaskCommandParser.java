package seedu.address.logic.parser.tasks;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_INDEX_GIVEN;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DEADLINE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DESCRIPTION;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.tasks.EditTaskCommand;
import seedu.address.logic.commands.tasks.EditTaskCommand.EditTaskDescriptor;
import seedu.address.logic.parser.ArgumentMultimap;
import seedu.address.logic.parser.ArgumentTokenizer;
import seedu.address.logic.parser.Parser;
import seedu.address.logic.parser.ParserUtil;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new EditTaskCommand object.
 */
public class EditTaskCommandParser implements Parser<EditTaskCommand> {
    /**
     * Parses the given {@code String} of arguments in the context of the EditTaskCommand
     * and returns an EditTaskCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public EditTaskCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_DESCRIPTION, PREFIX_DEADLINE);

        if (argMultimap.getPreamble().isEmpty() || !argMultimap.preambleHasExpectedSegments(1)) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    EditTaskCommand.COMMAND_WORD));
        }

        EditTaskDescriptor editTaskDescriptor = new EditTaskDescriptor();
        if (argMultimap.getValue(PREFIX_DESCRIPTION).isPresent()) {
            editTaskDescriptor.setDescription(ParserUtil.parseTaskDescription(argMultimap
                    .getValue(PREFIX_DESCRIPTION).get()));
        }
        if (argMultimap.getValue(PREFIX_DEADLINE).isPresent()) {
            editTaskDescriptor.setDeadline(ParserUtil.parseTaskDeadline(argMultimap.getValue(PREFIX_DEADLINE).get()));
        }

        if (!editTaskDescriptor.isAnyFieldEdited()) {
            throw new ParseException(EditTaskCommand.MESSAGE_NOT_EDITED);
        }

        Index index;

        try {
            index = ParserUtil.parseIndex(argMultimap.getPreamble());
        } catch (ParseException pe) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    MESSAGE_INVALID_INDEX_GIVEN), pe);
        }

        return new EditTaskCommand(index, editTaskDescriptor);
    }

}
