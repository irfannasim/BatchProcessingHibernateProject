package com.ts.config;

import com.ts.service.UserService;
import com.ts.util.LogUtil;

import java.util.List;
import java.util.logging.Level;

/**
 * Created by i.nasim on 18-Jan-17.
 */
public class Test {


    public static void main(String... args) {
        //System.out.println(getUserById(14642));
        LogUtil.log("main starts at: " + System.currentTimeMillis(), Level.INFO, null);
        //System.out.println(10 % 1000);
        //System.out.println(getUserIdsInStringByLimit(Integer.parseInt(TSResourceBundle.SYSTEM_BUNDLE.getString("queryDataLimit"))));
        List<Integer> ids = getUserIdsInIntegerByLimit(Integer.parseInt(TSResourceBundle.SYSTEM_BUNDLE.getString("queryDataLimit")));
        //deleteUsersBatch(ids);

        LogUtil.log("main ends at: " + System.currentTimeMillis(), Level.INFO, null);
    }

    public static void deleteUsersBatch(List<Integer> userIds) {
        UserService userService = new UserService();
        userService.deleteUsersBatch(userIds);
    }

    private static String getUserById(int userId) {
        UserService userService = new UserService();
        return userService.getUserById(userId).toString();
    }

    private static String getUserIdsInStringByLimit(int limit) {
        UserService userService = new UserService();
        return userService.getUserIdsInStringByLimit(limit);
    }

    private static List<Integer> getUserIdsInIntegerByLimit(int limit) {
        UserService userService = new UserService();
        return userService.getUserIdsInIntegerByLimit(limit);
    }
}
