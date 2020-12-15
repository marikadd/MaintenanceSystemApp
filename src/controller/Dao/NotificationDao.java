package controller.Dao;

import configuration.Database.DBAbstractFactory;
import configuration.Database.DBFactoryContext;
import configuration.Database.DBManager;
import configuration.Database.DBProduct;
import configuration.Exceptions.InvalidParameterObjectException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Group9
 */
public class NotificationDao {

    private static NotificationDao notificationDao;
    private DBProduct dbProduct;

    /**
     * Pattern Singleton
     */
    private NotificationDao() {
    }

    /**
     * Creates a singleton for the current class. In order to avoid conflicts
     * between threads, the method uses the synchronized construct.
     *
     * @return an instance of the current class
     */
    public static NotificationDao init() {
        if (notificationDao == null) {
            synchronized (NotificationDao.class) {
                if (notificationDao == null) {
                    notificationDao = new NotificationDao();
                    DBAbstractFactory dbFactory = new DBFactoryContext();
                    notificationDao.dbProduct = dbFactory.getInstance(DBManager.instanceType);
                }
            }
        }
        return notificationDao;
    }

    /**
     * Inserts the message passed in input inside the table Notification_Planner
     *
     * @param message represents the message to show to the Planner
     * @return either the row count for SQL Data Manipulation Language (DML)
     * statements or 0 for SQL statements that return nothing
     * @throws SQLException if a database access error occurs
     */
    public int insertMessageNotificationPlanner(String message) throws SQLException {

        Connection con = dbProduct.connectToDB();

        String query = "Insert into Notification_Planner VALUES(?, false)";

        PreparedStatement ps = con.prepareStatement(query);
        ps.setString(1, message);

        return ps.executeUpdate();

    }

    /**
     * Changes the states of all the planner's messages, stating that they've
     * been read.
     *
     * @return either the row count for SQL Data Manipulation Language (DML)
     * statements or 0 for SQL statements that return nothing
     * @throws SQLException if a database access error occurs
     */
    public int readAllMessage() throws SQLException {

        Connection con = dbProduct.connectToDB();

        String query = "UPDATE Notification_Planner SET read = true";

        PreparedStatement ps = con.prepareStatement(query);

        return ps.executeUpdate();

    }

    /**
     * Creates a list of all the messages that haven't been read by a maintainer
     * yet.
     *
     * @return a LinkedList of strings that represents the messages not read yet
     * @throws SQLException if a database access error occurs
     */
    public List<String> getAllMessagesNotRead() throws SQLException {

        Connection con = dbProduct.connectToDB();

        String query = "SELECT message FROM Notification_Planner WHERE read = false";

        PreparedStatement ps = con.prepareStatement(query);

        ResultSet rs = ps.executeQuery();

        List<String> messages = new ArrayList<>();

        while (rs.next()) {

            messages.add(rs.getString("message"));

        }

        return messages;

    }

    /**
     * Validates a notification checking if its message is not null, blank or
     * has a lenght higher than 255 characters.
     *
     * @param message represents the text of the notification
     * @throws InvalidParameterObjectException if the message is null, blank, or
     * its lenght is higher than 255 characters.
     */
    private void validateNotification(String message) throws InvalidParameterObjectException {

        if (message == null || message.isBlank()) {
            throw new InvalidParameterObjectException("Activity type must be not null");
        }

        if (message.length() > 255) {
            throw new InvalidParameterObjectException("Activity type must be length at most 255 characters");
        }
    }
}
