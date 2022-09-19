package com.jwebscan.model;

import javax.swing.*;

/**
 * @author Basil
 * @create 2022/5/28 10:43
 */
public class UserTable {
    private JTable userTable;
    private String userName;
    private String passWord;
    private boolean privileges;

    public JTable getUserTable() {
        return userTable;
    }

    public void setUserTable(JTable userTable) {
        this.userTable = userTable;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassWord() {
        return passWord;
    }

    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }

    public boolean isPrivileges() {
        return privileges;
    }

    public void setPrivileges(boolean privileges) {
        this.privileges = privileges;
    }
}
