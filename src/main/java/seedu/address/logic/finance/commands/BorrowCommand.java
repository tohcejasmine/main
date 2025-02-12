package seedu.address.logic.finance.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.finance.parser.FinanceCliSyntax.PREFIX_AMOUNT;
import static seedu.address.logic.finance.parser.FinanceCliSyntax.PREFIX_CATEGORY;
import static seedu.address.logic.finance.parser.FinanceCliSyntax.PREFIX_DAY;
import static seedu.address.logic.finance.parser.FinanceCliSyntax.PREFIX_DESCRIPTION;
import static seedu.address.logic.finance.parser.FinanceCliSyntax.PREFIX_FROM;
import static seedu.address.logic.finance.parser.FinanceCliSyntax.PREFIX_TRANSACTION_METHOD;

import seedu.address.logic.finance.commands.exceptions.CommandException;
import seedu.address.model.finance.Model;
import seedu.address.model.finance.logentry.LogEntry;

/**
 * Adds an entry of borrow (instance of borrowing) to the finance log.
 */
public class BorrowCommand extends Command {

    public static final String COMMAND_WORD = "borrow";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds an entry of borrowing to the finance log. \n"
            + "Parameters: "
            + PREFIX_AMOUNT + "AMOUNT "
            + PREFIX_DESCRIPTION + "DESCRIPTION "
            + PREFIX_TRANSACTION_METHOD + "TRANSACTION_METHOD "
            + PREFIX_FROM + "PERSON_BORROWED_FROM "
            + "[" + PREFIX_DAY + "DATE_BORROWED] "
            + "[" + PREFIX_CATEGORY + "CATEGORY]...\n"
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_AMOUNT + "2 "
            + PREFIX_DAY + "09-08-2019 "
            + PREFIX_DESCRIPTION + "Dilys egg tarts "
            + PREFIX_TRANSACTION_METHOD + "Cash "
            + PREFIX_FROM + "Classmate ";

    public static final String MESSAGE_SUCCESS = "New entry added: %1$s \n";

    private final LogEntry toAdd;

    /**
     * Creates an AddCommand to add the specified {@code LogEntry}
     */
    public BorrowCommand(LogEntry logEntry) {
        requireNonNull(logEntry);
        toAdd = logEntry;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        model.addLogEntry(toAdd);
        return new CommandResult(String.format(MESSAGE_SUCCESS, toAdd));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof BorrowCommand // instanceof handles nulls
                && toAdd.equals(((BorrowCommand) other).toAdd));
    }
}
