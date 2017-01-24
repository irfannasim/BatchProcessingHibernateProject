package com.ts.DAO;

import com.ts.model.User;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by i.nasim on 18-Jan-17.
 */
public interface UserDAO {
    public List<User> getAllUsers();
    public User getUserById(int userId);
    public List<Integer> getUserIdsByLimit(int limit);
    public void archiveUsersBatch(List<Integer> userIds) throws SQLException;
    public void deleteUsersBatch(List<Integer> userIds) throws SQLException;
    public void deleteUsersBatchUserObjects(List<User> users) throws SQLException;
    public List<User> getUsersByLimit(int limit) throws SQLException;
    public void addUsersBatch(List<User> users) throws SQLException;

}
