package Exo1;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.logging.Logger;


/**
 * Backup manager for mediastore's stocks.
 * <br>
 * It loads and saves the backup by using serialization mechanism in a file named stocks.data.
 * 
 * @author bsauvan
 */
public class StocksBackupManager implements BackupManager {
    private static final Logger logger = Logger.getLogger(Stocks.class.getName());

    private static final String STOCKS_FILENAME = "stocks.data";

    /**
     * {@inheritDoc}
     */
    @Override
    public Object loadBackup() {
        Object stocks = null;

        try (FileInputStream fis = new FileInputStream(STOCKS_FILENAME);
                ObjectInputStream ois = new ObjectInputStream(fis);) {
            stocks = ois.readObject();
            

            logger.info("Etat des stocks charge");
        } catch (ClassNotFoundException e) {
            logger.severe(e.getMessage());
        } catch (FileNotFoundException e) {
            logger.warning(e.getMessage());
        } catch (IOException e) {
            logger.severe(e.getMessage());
        }

        return stocks;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void saveBackup(Object object) {
        try (FileOutputStream fos = new FileOutputStream(STOCKS_FILENAME);
                ObjectOutputStream oos = new ObjectOutputStream(fos);) {
            oos.writeObject(object);

            logger.info("Etat des stocks sauvegarde");
        } catch (FileNotFoundException e) {
            logger.severe(e.getMessage());
        } catch (IOException e) {
            logger.severe(e.getMessage());
        }
    }
}
