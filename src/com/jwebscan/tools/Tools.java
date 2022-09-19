/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jwebscan.tools;

import java.awt.*;
import java.sql.SQLException;

/**
 * @author Administrator
 */
public class Tools {
    public static void setToScreenCenter(Window window) {
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        Dimension windowSize = window.getSize();
        if (windowSize.height > screenSize.height) {
            windowSize.height = screenSize.height;
        }
        if (windowSize.width > screenSize.width) {
            windowSize.width = screenSize.width;
        }
        window.setLocation((screenSize.width - windowSize.width) / 2, (screenSize.height - windowSize.height) / 2);
    }

    /**
     * 验证并修改域名格式
     */
    public static String checkTheDomain(String webUrl) {
        if ("".equals(webUrl.trim())) {
            return "";
        }
        if (!webUrl.startsWith("http://")) {
            webUrl = "http://" + webUrl;
        }
        if ("/".equals(webUrl.substring(webUrl.length() - 1))) {
//               移除最后一个
            webUrl = webUrl.substring(0, webUrl.length() - 1);
        }
        return webUrl;
    }

    public static boolean checkTheUserName(String userName) {
        String sql = "select * from user_info where user_name = '" + userName + "'";
        DataBaseHelper db = new DataBaseHelper(sql);
        try {
            return db.pst.executeQuery().next();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return true;
    }

}
