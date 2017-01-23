package com.ts.DAO;

import com.ts.config.TSResourceBundle;
import com.ts.model.User;
import com.ts.util.LogUtil;
import org.hibernate.Query;
import org.hibernate.ScrollableResults;
import org.hibernate.Transaction;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;

/**
 * Created by i.nasim on 18-Jan-17.
 */
public class UserDAOImpl extends GenericDAO implements UserDAO {

    public List<User> getAllUsers() {
        return null;
    }

    public List<Integer> getUserIdsByLimit(int limit) {
        LogUtil.log("find users ids ", Level.SEVERE, null);
        try {
            if (session.getTransaction() != null
                    && session.getTransaction().isActive()) {
                session.getTransaction();
            } else {
                session.beginTransaction();
            }

            final String queryString = "SELECT userId FROM User u ";
            Query q = session.createQuery(queryString).setMaxResults(limit);

            return q.list();

        } catch (RuntimeException re) {
            LogUtil.log("find users ids failed", Level.SEVERE, re);
            throw re;
        }
    }

    public User getUserById(int userId) {
        return findById(User.class, userId);
    }

    public void deleteUsersBatch(List<Integer> userIds) throws SQLException {
        int batchSize = Integer.parseInt(TSResourceBundle.SYSTEM_BUNDLE.getString("batchSize"));

        try {
            Transaction tx;
            if (session.getTransaction() != null
                    && session.getTransaction().isActive()) {
                tx = session.getTransaction();
            } else {
                tx = session.beginTransaction();
            }

            ScrollableResults userCursor =
                    session.createQuery("FROM User WHERE userId IN (:ids)")
                            .setParameterList("ids", userIds)
                            .scroll();
            int count = 0;
            while (userCursor.next()) {
                User user = (User) userCursor.get(0);
                user.setDeleted(true);
                session.update(user);

                if (++count % 100 == 0) {
                    session.flush();
                    session.clear();
                }
            }
            tx.commit();
        } catch (RuntimeException re) {
            LogUtil.log("deleteUsersBatch failed", Level.SEVERE, re);
            throw re;
        }
    }

}
