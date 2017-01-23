package com.ts.service;

import com.ts.DAO.UserDAO;
import com.ts.DAO.UserDAOImpl;
import com.ts.model.User;
import com.ts.util.LogUtil;

import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;

/**
 * Created by i.nasim on 18-Jan-17.
 */
public class UserService {
    UserDAO userDAO = new UserDAOImpl();

    public User getUserById(int userId) {
        return userDAO.getUserById(userId);

    }

    public String getUserIdsInStringByLimit(int limit) {
        LogUtil.log("getUserIdsByLimit starts for " + limit + "records " + "at: " + System.currentTimeMillis(), Level.INFO, null);

        List<Integer> ids = userDAO.getUserIdsByLimit(limit);

        LogUtil.log("getUserIdsByLimit ends for " + limit + "records " + "at: " + System.currentTimeMillis(), Level.INFO, null);
        return buildStringOfIds(ids);
    }

    public List<Integer> getUserIdsInIntegerByLimit(int limit) {
        LogUtil.log("getUserIdsByLimit starts for " + limit + "records " + "at: " + System.currentTimeMillis(), Level.INFO, null);

        List<Integer> ids = userDAO.getUserIdsByLimit(limit);

        LogUtil.log("getUserIdsByLimit ends for " + limit + "records " + "at: " + System.currentTimeMillis(), Level.INFO, null);
        return ids;
    }

    public void deleteUsersBatch(List<Integer> userIds) {
        LogUtil.log("deleteUsersBatch starts for " + userIds.size() + "records " + "at: " + System.currentTimeMillis(), Level.INFO, null);

        try {
            userDAO.deleteUsersBatch(userIds);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        LogUtil.log("deleteUsersBatch ends for " + userIds.size() + "records " + "at: " + System.currentTimeMillis(), Level.INFO, null);
    }


    private String buildStringOfIds(List<Integer> dbIds) {

        LogUtil.log("buildStringOfIds starts at: " + System.currentTimeMillis(), Level.INFO, null);
        StringBuilder ids = new StringBuilder();
        try {
            for (Integer id : dbIds) {
                ids.append(id.intValue() + "");
                ids.append(",");
            }

        } catch (Exception ex) {
            ex.printStackTrace();
        }
        LogUtil.log("buildStringOfIds ends at: " + System.currentTimeMillis(), Level.INFO, null);
        return ids.deleteCharAt(ids.length() - 1).toString();
    }
}
