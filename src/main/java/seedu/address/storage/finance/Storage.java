package seedu.address.storage.finance;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;

import seedu.address.commons.exceptions.DataConversionException;
import seedu.address.model.finance.ReadOnlyFinanceLog;
import seedu.address.model.finance.ReadOnlyUserPrefs;
import seedu.address.model.finance.UserPrefs;


/**
 * API of the Storage component
 */
public interface Storage extends FinanceStorage, UserPrefsStorage {

    @Override
    Optional<UserPrefs> readUserPrefs() throws DataConversionException, IOException;

    @Override
    void saveUserPrefs(ReadOnlyUserPrefs userPrefs) throws IOException;

    @Override
    Path getAddressBookFilePath();

    @Override
    Optional<ReadOnlyFinanceLog> readAddressBook() throws DataConversionException, IOException;

    @Override
    void saveAddressBook(ReadOnlyFinanceLog addressBook) throws IOException;

}
