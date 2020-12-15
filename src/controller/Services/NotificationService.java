
package controller.Services;

import controller.Dao.NotificationDao;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author Group 9
 * 
 * The public methods of this class encapsulate the methods of the DAO classes. 
 * For this reason the comments relating to the parameters and exceptions 
 * have not been written because they are already in the DAOs.
 *  
 */
public class NotificationService {

    private static NotificationService notService;
    private NotificationDao notDao;

    /**
     * Pattern Singleton.
     */
    private NotificationService() {
    }

    /**
     * Creates a singleton for the current class. In order to avoid conflicts
     * between threads, the method uses the synchronized construct.
     * @return an instance of the current class.
     */
    public static NotificationService init() {
        
        if (notService == null) {
            synchronized (NotificationService.class) {
                if (notService == null) {
                    notService = new NotificationService();
                    notService.notDao = NotificationDao.init();
                }
            }
        }
        return notService;
    }

    /** 
     * This method keeps track of notifications that have not yet been read.
     * @return a list of notifications that have not yet been read.
     * @throws SQLException 
     */
    public List<String> readNotifications() throws SQLException {

        List<String> notifications = notDao.getAllMessagesNotRead();
        notDao.readAllMessage();
        return notifications;

    }

}
