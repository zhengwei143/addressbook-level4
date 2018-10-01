package seedu.saveit.storage;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;

import seedu.saveit.commons.exceptions.DataConversionException;
import seedu.saveit.model.ReadOnlySaveIt;
import seedu.saveit.model.SaveIt;

/**
 * Represents a storage for {@link SaveIt}.
 */
public interface AddressBookStorage {

    /**
     * Returns the file path of the data file.
     */
    Path getAddressBookFilePath();

    /**
     * Returns SaveIt data as a {@link ReadOnlySaveIt}.
     *   Returns {@code Optional.empty()} if storage file is not found.
     * @throws DataConversionException if the data in storage is not in the expected format.
     * @throws IOException if there was any problem when reading from the storage.
     */
    Optional<ReadOnlySaveIt> readAddressBook() throws DataConversionException, IOException;

    /**
     * @see #getAddressBookFilePath()
     */
    Optional<ReadOnlySaveIt> readAddressBook(Path filePath) throws DataConversionException, IOException;

    /**
     * Saves the given {@link ReadOnlySaveIt} to the storage.
     * @param saveIt cannot be null.
     * @throws IOException if there was any problem writing to the file.
     */
    void saveAddressBook(ReadOnlySaveIt saveIt) throws IOException;

    /**
     * @see #saveAddressBook(ReadOnlySaveIt)
     */
    void saveAddressBook(ReadOnlySaveIt saveIt, Path filePath) throws IOException;

}
