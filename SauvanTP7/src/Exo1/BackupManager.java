package Exo1;

/**
 * Common interface for backup managers of any kind of object.
 * 
 * @author bsauvan
 */
public interface BackupManager {
    /**
     * Loads an object from the backup.
     * 
     * @return The object loaded from the backup.
     */
    public Object loadBackup();

    /**
     * Saves the object to the backup.
     * 
     * @param object The object to save into the backup.
     */
    public void saveBackup(Object object);
}
