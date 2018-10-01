package seedu.saveit.storage;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;
import java.util.logging.Logger;

import com.google.common.eventbus.Subscribe;

import seedu.saveit.commons.core.ComponentManager;
import seedu.saveit.commons.core.LogsCenter;
import seedu.saveit.commons.events.model.SaveItChangedEvent;
import seedu.saveit.commons.events.storage.DataSavingExceptionEvent;
import seedu.saveit.commons.exceptions.DataConversionException;
import seedu.saveit.model.ReadOnlySaveIt;
import seedu.saveit.model.UserPrefs;

/**
 * Manages storage of SaveIt data in local storage.
 */
public class StorageManager extends ComponentManager implements Storage {

    private static final Logger logger = LogsCenter.getLogger(StorageManager.class);
    private SaveItStorage addressBookStorage;
    private UserPrefsStorage userPrefsStorage;


    public StorageManager(SaveItStorage addressBookStorage, UserPrefsStorage userPrefsStorage) {
        super();
        this.addressBookStorage = addressBookStorage;
        this.userPrefsStorage = userPrefsStorage;
    }

    // ================ UserPrefs methods ==============================

    @Override
    public Path getUserPrefsFilePath() {
        return userPrefsStorage.getUserPrefsFilePath();
    }

    @Override
    public Optional<UserPrefs> readUserPrefs() throws DataConversionException, IOException {
        return userPrefsStorage.readUserPrefs();
    }

    @Override
    public void saveUserPrefs(UserPrefs userPrefs) throws IOException {
        userPrefsStorage.saveUserPrefs(userPrefs);
    }


    // ================ SaveIt methods ==============================

    @Override
    public Path getSaveItFilePath() {
        return addressBookStorage.getSaveItFilePath();
    }

    @Override
    public Optional<ReadOnlySaveIt> readSaveIt() throws DataConversionException, IOException {
        return readSaveIt(addressBookStorage.getSaveItFilePath());
    }

    @Override
    public Optional<ReadOnlySaveIt> readSaveIt(Path filePath) throws DataConversionException, IOException {
        logger.fine("Attempting to read data from file: " + filePath);
        return addressBookStorage.readSaveIt(filePath);
    }

    @Override
    public void saveSaveIt(ReadOnlySaveIt saveIt) throws IOException {
        saveSaveIt(saveIt, addressBookStorage.getSaveItFilePath());
    }

    @Override
    public void saveSaveIt(ReadOnlySaveIt saveIt, Path filePath) throws IOException {
        logger.fine("Attempting to write to data file: " + filePath);
        addressBookStorage.saveSaveIt(saveIt, filePath);
    }


    @Override
    @Subscribe
    public void handleSaveItChangedEvent(SaveItChangedEvent event) {
        logger.info(LogsCenter.getEventHandlingLogMessage(event, "Local data changed, saving to file"));
        try {
            saveSaveIt(event.data);
        } catch (IOException e) {
            raise(new DataSavingExceptionEvent(e));
        }
    }

}
