package seedu.saveit.storage;

import java.io.FileNotFoundException;
import java.nio.file.Path;

import javax.xml.bind.JAXBException;

import seedu.saveit.commons.exceptions.DataConversionException;
import seedu.saveit.commons.util.XmlUtil;

/**
 * Stores saveit data in an XML file
 */
public class XmlFileStorage {
    /**
     * Saves the given saveit data to the specified file.
     */
    public static void saveDataToFile(Path file, XmlSerializableAddressBook saveIt)
            throws FileNotFoundException {
        try {
            XmlUtil.saveDataToFile(file, saveIt);
        } catch (JAXBException e) {
            throw new AssertionError("Unexpected exception " + e.getMessage(), e);
        }
    }

    /**
     * Returns address book in the file or an empty address book
     */
    public static XmlSerializableAddressBook loadDataFromSaveFile(Path file) throws DataConversionException,
                                                                            FileNotFoundException {
        try {
            return XmlUtil.getDataFromFile(file, XmlSerializableAddressBook.class);
        } catch (JAXBException e) {
            throw new DataConversionException(e);
        }
    }

}
