package com.jwebscan.frame;

import com.jwebscan.logic.UserLogic;
import com.jwebscan.model.UserTable;
import com.jwebscan.tools.Tools;

import java.awt.*;
import java.util.Arrays;

import javax.swing.*;

/**
 * @author Basil
 * @create 2022/5/27 11:17
 */
public class UserForm extends JFrame {
    public static void main(String[] args) {
        // 设置外观
        try {
            for (UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException | UnsupportedLookAndFeelException | InstantiationException | IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(DisplayForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        // 创建并显示表单
        java.awt.EventQueue.invokeLater(() -> new UserForm().setVisible(true));
    }

    public UserForm() {
        initComponents();
        Tools.setToScreenCenter(this);
    }

    private void initComponents() {
        dialogAlert = new JDialog();
        btnAlert = new JButton();
        lblAlert = new JLabel();
        lblUser = new JLabel();
        lblPassWd = new JLabel();
        againPassWd = new JLabel();
        txtUser = new JTextField();
        txtPassWd = new JPasswordField();
        txtAgain = new JPasswordField();
        btnLogin = new JButton();
        btnRegistered = new JButton();
        jScrollPane1 = new JScrollPane();
        managerPrivileges = new JCheckBox();

        dialogAlert.setTitle("提示");
        dialogAlert.setMinimumSize(new Dimension(295, 136));
        dialogAlert.setResizable(false);

        btnAlert.setText("确定");
        btnAlert.addActionListener(this::btnAlertActionPerformed);

        DisplayForm.buildFrame(btnAlert, dialogAlert, lblAlert);

        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setTitle("登陆界面");
        setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
        setName("mainFrame");

        lblUser.setText("用 户：");

        lblPassWd.setText("密 码：");

        againPassWd.setText("确认密码：");

        managerPrivileges.setText("管理员权限");

        btnLogin.setText("登录");
        btnLogin.addActionListener(evt -> btnLoginPerformed());

        btnRegistered.setText("注册");
        btnRegistered.addActionListener(evt -> btnRegisteredPerformed());

        GroupLayout layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addGap(15, 15, 15)
                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                        .addGroup(layout.createSequentialGroup()
                                                .addComponent(jScrollPane1, GroupLayout.PREFERRED_SIZE, 500, GroupLayout.PREFERRED_SIZE)
                                                .addGap(10, 10, 10))
                                        .addGroup(layout.createSequentialGroup()
                                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                                                        .addGroup(layout.createSequentialGroup()
                                                                .addGap(0, 0, Short.MAX_VALUE)
                                                                .addComponent(btnLogin)
                                                                .addComponent(btnRegistered)
                                                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                                        )
                                                        .addGroup(layout.createSequentialGroup()
                                                                .addComponent(managerPrivileges))
                                                        .addGroup(layout.createSequentialGroup()
                                                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                                                                        .addGroup(layout.createSequentialGroup()
                                                                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                                                                        .addGroup(layout.createSequentialGroup()
                                                                                        )))
                                                                        .addGroup(layout.createSequentialGroup()
                                                                                .addComponent(lblUser, GroupLayout.PREFERRED_SIZE, 63, GroupLayout.PREFERRED_SIZE)
                                                                                .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                                                                                .addComponent(txtUser))
                                                                        .addGroup(layout.createSequentialGroup()
                                                                                .addComponent(lblPassWd, GroupLayout.PREFERRED_SIZE, 63, GroupLayout.PREFERRED_SIZE)
                                                                                .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                                                                                .addComponent(txtPassWd))
                                                                        .addGroup(layout.createSequentialGroup()
                                                                                .addComponent(againPassWd, GroupLayout.PREFERRED_SIZE, 63, GroupLayout.PREFERRED_SIZE)
                                                                                .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                                                                                .addComponent(txtAgain)
                                                                                )
                                                                        .addGap(4, 4, 4)
                                                                        )
                                                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                                                                )
                                                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                        ))
                                                .addGap(15, 15, 15))))
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addGap(15, 15, 15)
                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                        .addComponent(lblUser)
                                        .addComponent(txtUser, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(btnLogin)
                                        .addComponent(btnRegistered))
                                .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                        .addComponent(lblPassWd)
                                        .addComponent(txtPassWd, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                )
                                .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                        .addComponent(againPassWd)
                                        .addComponent(txtAgain, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(managerPrivileges)
                                )
                                .addGap(10, 10, 10)
                                .addComponent(jScrollPane1, GroupLayout.DEFAULT_SIZE, 100, Short.MAX_VALUE)
                                .addContainerGap())
        );

        pack();
    }

    private void btnLoginPerformed() {
        String userName = this.txtUser.getText();
        char[] passWord = this.txtPassWd.getPassword();
        Tools.setToScreenCenter(dialogAlert);
        if (Tools.checkTheUserName(userName)) {

        }
        UserTable ut = new UserTable();
        ut.setUserName(userName);
        String passWd = String.valueOf(passWord);
        ut.setPassWord(passWd);
        UserTable newUt = new UserLogic().searchUserInfo(ut);
        if (newUt != null){
            this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            this.dispose();
            DisplayForm displayForm = new DisplayForm(newUt.isPrivileges(), newUt.getUserName());
            java.awt.EventQueue.invokeLater(() -> displayForm.setVisible(true));
        }else {
            //弹窗
            lblAlert.setText("用户名或密码错误");
            dialogAlert.setVisible(true);
        }
    }

    private void btnRegisteredPerformed() {
        String userName = this.txtUser.getText();
        char[] passWord = this.txtPassWd.getPassword();
        char[] againPassWd = this.txtAgain.getPassword();
        Tools.setToScreenCenter(dialogAlert);
        if (!Arrays.equals(passWord, againPassWd)){
            lblAlert.setText("密码不一致");
            dialogAlert.setVisible(true);
            return;
        }
        if (Tools.checkTheUserName(userName)) {
            lblAlert.setText("用户名已存在");
            dialogAlert.setVisible(true);
            return;
        }
        UserTable ut = new UserTable();
        ut.setUserName(userName);
        String passWd = String.valueOf(passWord);
        ut.setPassWord(passWd);
        ut.setPrivileges(managerPrivileges.isSelected());
        UserLogic userLogic = new UserLogic();
        userLogic.addUserInfo(ut);
        lblAlert.setText("注册成功");
        dialogAlert.setVisible(true);
    }

    private void btnAlertActionPerformed(java.awt.event.ActionEvent evt) {
        dialogAlert.dispose();
    }

    private JButton btnAlert;
    private JButton btnLogin;
    private JButton btnRegistered;
    private JScrollPane jScrollPane1;
    private JDialog dialogAlert;
    private JLabel lblAlert;
    private JLabel lblUser;
    private JLabel lblPassWd;
    private JLabel againPassWd;
    private JCheckBox managerPrivileges;
    private JTextField txtUser;
    private JPasswordField txtPassWd;
    private JPasswordField txtAgain;
}
