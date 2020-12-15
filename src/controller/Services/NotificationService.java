/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.Services;

import controller.Dao.NotificationDao;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author Group9
 */
public class NotificationService {

    private static NotificationService notService;
    private NotificationDao notDao;

    public static NotificationService init() {

        if (notService == null) {
            synchronized(NotificationService.class) {
                if(notService == null) {
                    notService = new NotificationService();
                    notService.notDao = NotificationDao.init();
                }
            }
        }
        return notService;

    }

    public List<String> readNotifications() throws SQLException {

        List<String> notifications = notDao.getAllMessagesNotRead();
        notDao.readAllMessage();
        return notifications;

    }

}
