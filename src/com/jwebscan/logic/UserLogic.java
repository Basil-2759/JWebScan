package com.jwebscan.logic;

import com.jwebscan.model.UserTable;
import com.jwebscan.tools.DataBaseHelper;

import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.table.DefaultTableModel;

/**
 * @author Basil
 * @create 2022/5/27 11:18
 */
public class UserLogic {
    public UserTable searchUserInfo(UserTable ut) {
        String sql = "select * from user_info where user_name = '" + ut.getUserName() + "' and passwd = '" + ut.getPassWord() + "'";
        DataBaseHelper db = new DataBaseHelper(sql);

        try {
            ResultSet ret = db.pst.executeQuery();
            if (ret.next()) {
                UserTable newUt = new UserTable();
                newUt.setUserName(ret.getString("user_name"));
                newUt.setPrivileges(ret.getBoolean("registered"));
                return newUt;
            }
        } catch (
                SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    public boolean addUserInfo(UserTable ut) {
        String sql = "insert into user_info (user_name, passwd, registered) values ( '"
                + ut.getUserName() + "','"
                + ut.getPassWord() + "',"
                + ut.isPrivileges() + ")";
        DataBaseHelper db = new DataBaseHelper(sql);

        try {
            return db.dataBaseExecute(sql) > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    public void updateUserInfo(UserTable ut) {
        for (int i = 0; i < ut.getUserTable().getRowCount(); i++) {
            String sql = "UPDATE user_info SET user_name = '" + ut.getUserTable().getValueAt(i, 1) +
                    "', passwd = '" + ut.getUserTable().getValueAt(i, 2) +
                    "' WHERE id = " + ut.getUserTable().getValueAt(i, 0);
            DataBaseHelper db = new DataBaseHelper(sql);
            try {
                db.dataBaseExecute(sql);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public void queryUserInfo(UserTable ut) {
        String sql = "select * from user_info where registered = 0";
        DataBaseHelper db = new DataBaseHelper(sql);
        DefaultTableModel tm = (DefaultTableModel) ut.getUserTable().getModel();
        try {
            ResultSet ret = db.pst.executeQuery();
            while (ret.next()) {
                String id = ret.getString(1);
                String userName = ret.getString(2);
                String password = ret.getString(3);
                tm.addRow(new Object[]{id, userName, password, "删 除"});
            }
            ret.close();
            db.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

}
