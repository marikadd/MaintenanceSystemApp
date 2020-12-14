/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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

    public int insertMessageNotificationPlanner(String message) throws SQLException {

        Connection con = dbProduct.connectToDB();

        String query = "Insert into Notification_Planner VALUES(?, false)";

        PreparedStatement ps = con.prepareStatement(query);
        ps.setString(1, message);

        return ps.executeUpdate();

    }

    public int readAllMessage() throws SQLException {

        Connection con = dbProduct.connectToDB();

        String query = "UPDATE Notification_Planner SET read = true";

        PreparedStatement ps = con.prepareStatement(query);

        return ps.executeUpdate();

    }

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
    
    private void validateNotification(String message) throws InvalidParameterObjectException {
        
        if (message == null || message.isBlank()) {
            throw new InvalidParameterObjectException("Activity type must be not null");
        }

        if (message.length() > 255) {
            throw new InvalidParameterObjectException("Activity type must be length at most 255 characters");
        }
    }
}
