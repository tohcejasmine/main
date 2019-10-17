package seedu.address.logic.cap.commands;

import static java.util.Objects.requireNonNull;

import seedu.address.model.cap.CapLog;
import seedu.address.model.cap.Model;

/**
 * Clears the address book.
 */
public class ClearCommand extends Command {

    public static final String COMMAND_WORD = "clear";
    public static final String MESSAGE_SUCCESS = "Address book has been cleared!";


    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.setAddressBook(new CapLog());
        return new CommandResult(MESSAGE_SUCCESS);
    }
}
