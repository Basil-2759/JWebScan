package com.jwebscan.frame;

import com.jwebscan.logic.UserLogic;
import com.jwebscan.logic.VulnLogic;
import com.jwebscan.model.UserTable;
import com.jwebscan.model.VulnTable;
import com.jwebscan.tools.Tools;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

/**
 * @author Basil
 */
public class DisplayForm extends JFrame {

    private final boolean managerPrivileges;
    private final String userName;
    private int scanNum = 0;

    public DisplayForm(boolean managerPrivileges, String userName) {
        this.userName = userName;
        this.managerPrivileges = managerPrivileges;
        initComponents();
        Tools.setToScreenCenter(this);
    }

    @SuppressWarnings("unchecked")
    private void initComponents() {
        dialogAlert = new JDialog();
        dialogUserInfo = new JDialog();
        dialogVulnInfo = new JDialog();
        JButton btnAlert = new JButton();
        JButton btnSave = new JButton();
        lblAlert = new JLabel();
        JLabel lblUrl = new JLabel();
        txtUrl = new JTextField();
        JButton btnStart = new JButton();
        JButton btnManage = new JButton();
        JButton btnSearchHistory = new JButton();
        JButton btnDelete = new JButton();
        JLabel lblThreadCount = new JLabel();
        JComboBox combThreadCount = new JComboBox();
        JLabel lblTimeout = new JLabel();
        JComboBox combTimeout = new JComboBox();
        JLabel lblScantype = new JLabel();
        JScrollPane jScrollPaneTotal = new JScrollPane();
        JScrollPane jScrollPaneUser = new JScrollPane();
        JScrollPane jScrollPaneVuln = new JScrollPane();
        tabResult = new JTable();
        tabUserInfo = new JTable();
        tabVulnInfo = new JTable();
        JLabel lblScanSatus = new JLabel();
        JLabel lblCurScanStatus = new JLabel();
        JLabel lblCurScanPro = new JLabel();
        lblCurScanProNum = new JLabel();
        JLabel lblSusetime = new JLabel();
        lblUsetime = new JLabel();
        chkXSS = new JCheckBox();
        chkSQL = new JCheckBox();
        chkCSRF = new JCheckBox();
        chkFast = new JCheckBox();

        dialogAlert.setTitle("提示");
        dialogAlert.setMinimumSize(new java.awt.Dimension(295, 136));
        dialogAlert.setResizable(false);

        btnAlert.setText("确定");
        btnAlert.addActionListener(this::btnAlertActionPerformed);

        dialogUserInfo.setTitle("用户列表");
        dialogUserInfo.setMinimumSize(new java.awt.Dimension(400, 300));
        dialogUserInfo.setResizable(false);

        dialogVulnInfo.setTitle("扫描结果");
        dialogVulnInfo.setMinimumSize(new java.awt.Dimension(800, 300));
        dialogVulnInfo.setResizable(false);

        buildFrame(btnAlert, dialogAlert, lblAlert);

        GroupLayout jdlgUserInfoLayout = new GroupLayout(dialogUserInfo.getContentPane());
        dialogUserInfo.getContentPane().setLayout(jdlgUserInfoLayout);
        jdlgUserInfoLayout.setHorizontalGroup(
                jdlgUserInfoLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(jdlgUserInfoLayout.createSequentialGroup()
                                .addGroup(jdlgUserInfoLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                        .addGroup(jdlgUserInfoLayout.createSequentialGroup()
                                                .addGap(160, 160, 160)
                                                .addComponent(btnSave))
                                        .addGroup(jdlgUserInfoLayout.createSequentialGroup()
                                                .addComponent(jScrollPaneUser, GroupLayout.PREFERRED_SIZE, 400, GroupLayout.PREFERRED_SIZE)
                                                .addGap(10, 10, 10)))
                                .addContainerGap(33, Short.MAX_VALUE))
        );
        jdlgUserInfoLayout.setVerticalGroup(
                jdlgUserInfoLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(GroupLayout.Alignment.TRAILING, jdlgUserInfoLayout.createSequentialGroup()
                                .addGap(21, 21, 21)
                                .addComponent(jScrollPaneUser, GroupLayout.DEFAULT_SIZE, 300, Short.MAX_VALUE)
                                .addGap(21, 21, 21)
                                .addComponent(btnSave, GroupLayout.DEFAULT_SIZE, 28, Short.MAX_VALUE))
        );

        GroupLayout jdlgVulnInfoLayout = new GroupLayout(dialogVulnInfo.getContentPane());
        dialogVulnInfo.getContentPane().setLayout(jdlgVulnInfoLayout);
        jdlgVulnInfoLayout.setHorizontalGroup(
                jdlgVulnInfoLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(jdlgVulnInfoLayout.createSequentialGroup()
                                .addGroup(jdlgVulnInfoLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                        .addGroup(jdlgVulnInfoLayout.createSequentialGroup()
                                                .addComponent(jScrollPaneVuln, GroupLayout.PREFERRED_SIZE, 800, GroupLayout.PREFERRED_SIZE)
                                                .addGap(10, 10, 10)))
                                .addContainerGap(33, Short.MAX_VALUE))
        );
        jdlgVulnInfoLayout.setVerticalGroup(
                jdlgVulnInfoLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(GroupLayout.Alignment.TRAILING, jdlgVulnInfoLayout.createSequentialGroup()
                                .addGap(21, 21, 21)
                                .addComponent(jScrollPaneVuln, GroupLayout.DEFAULT_SIZE, 800, Short.MAX_VALUE))
        );

        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setTitle("基于JavaEE的漏洞扫描系统");
        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        setName("mainFrame");

        lblUrl.setText("地 址：");

        txtUrl.setText("输入网站地址...");

        btnStart.setText("开始");
        btnStart.addActionListener(evt -> btnStartActionPerformed());

        btnManage.setText("管理用户");
        btnManage.addActionListener(evt -> btnManagePerformed());

        btnSearchHistory.setText("查询历史");
        btnSearchHistory.addActionListener(evt -> setBtnSearchHistoryPerformed());

        btnDelete.setText("删 除");
        btnDelete.addActionListener(evt -> btnDeletePerformed());

        btnSave.setText("保 存");
        btnSave.addActionListener(this::btnSaveActionPerformed);

        lblThreadCount.setText("线 程：");

        combThreadCount.setModel(new DefaultComboBoxModel(new String[]{"1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "15", "20", "30", "40", "50", "100"}));
        combThreadCount.setSelectedIndex(9);

        lblTimeout.setText("超 时：");

        combTimeout.setModel(new DefaultComboBoxModel(new String[]{"1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "15", "20", "25", "30", "60"}));
        combTimeout.setSelectedIndex(9);

        lblScantype.setText("类 型：");

        tabResult.setModel(new DefaultTableModel(
                new String[]{
                        "端 口", "状 态", "漏 洞"
                }, 0
        ) {
            final Class[] types = new Class[]{
                    java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            final boolean[] canEdit = new boolean[]{
                    false, true, false
            };

            @Override
            public Class getColumnClass(int columnIndex) {
                return types[columnIndex];
            }

            @Override
            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit[columnIndex];
            }
        });

        tabUserInfo.setModel(new DefaultTableModel(
                new String[]{
                        "ID", "用户名", "密 码", "操 作"
                }, 0
        ) {
            final Class[] types = new Class[]{
                    java.lang.String.class, java.lang.String.class, java.lang.String.class, JButton.class
            };
            final boolean[] canEdit = new boolean[]{
                    true, true, false
            };

            @Override
            public Class getColumnClass(int columnIndex) {
                return types[columnIndex];
            }

            @Override
            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit[columnIndex];
            }
        });

        tabVulnInfo.setModel(new DefaultTableModel(
                new String[]{
                        "URL", "端 口", "状 态", "漏 洞", "日 期"
                }, 0
        ) {
            final Class[] types = new Class[]{
                    java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            final boolean[] canEdit = new boolean[]{
                    false, false, false, false, false
            };

            @Override
            public Class getColumnClass(int columnIndex) {
                return types[columnIndex];
            }

            @Override
            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit[columnIndex];
            }
        });

        jScrollPaneTotal.setViewportView(tabResult);
        tabResult.getColumnModel().getColumn(0).setMinWidth(160);
        tabResult.getColumnModel().getColumn(0).setPreferredWidth(160);
        tabResult.getColumnModel().getColumn(0).setMaxWidth(160);
        tabResult.getColumnModel().getColumn(1).setMinWidth(160);
        tabResult.getColumnModel().getColumn(1).setPreferredWidth(160);
        tabResult.getColumnModel().getColumn(1).setMaxWidth(160);
        tabResult.getColumnModel().getColumn(2).setMinWidth(220);
        tabResult.getColumnModel().getColumn(2).setPreferredWidth(220);
        tabResult.getColumnModel().getColumn(2).setMaxWidth(220);

        jScrollPaneUser.setViewportView(tabUserInfo);
        tabUserInfo.getColumnModel().getColumn(0).setMinWidth(0);
        tabUserInfo.getColumnModel().getColumn(0).setPreferredWidth(0);
        tabUserInfo.getColumnModel().getColumn(0).setMaxWidth(0);
        tabUserInfo.getColumnModel().getColumn(1).setMinWidth(160);
        tabUserInfo.getColumnModel().getColumn(1).setPreferredWidth(160);
        tabUserInfo.getColumnModel().getColumn(1).setMaxWidth(160);
        tabUserInfo.getColumnModel().getColumn(2).setMinWidth(160);
        tabUserInfo.getColumnModel().getColumn(2).setPreferredWidth(160);
        tabUserInfo.getColumnModel().getColumn(2).setMaxWidth(160);
        tabUserInfo.getColumnModel().getColumn(3).setMinWidth(70);
        tabUserInfo.getColumnModel().getColumn(3).setPreferredWidth(70);
        tabUserInfo.getColumnModel().getColumn(3).setMaxWidth(70);

        jScrollPaneVuln.setViewportView(tabVulnInfo);
        tabVulnInfo.getColumnModel().getColumn(0).setPreferredWidth(250);
        tabVulnInfo.getColumnModel().getColumn(1).setPreferredWidth(100);
        tabVulnInfo.getColumnModel().getColumn(2).setPreferredWidth(90);
        tabVulnInfo.getColumnModel().getColumn(3).setPreferredWidth(90);
        tabVulnInfo.getColumnModel().getColumn(4).setPreferredWidth(370);

        lblScanSatus.setText("状 态：");

        lblCurScanStatus.setText("准备中");

        lblCurScanPro.setText("进 度：");

        lblCurScanProNum.setText("0/0");

        lblSusetime.setText("用 时：");

        lblUsetime.setText("0");

        chkFast.setText("快速扫描");

        chkXSS.setText("XSS跨域");

        chkSQL.setText("SQL注入");

        chkCSRF.setText("CSRF");

        GroupLayout layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addGap(15, 15, 15)
                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                        .addGroup(layout.createSequentialGroup()
                                                .addComponent(jScrollPaneTotal, GroupLayout.PREFERRED_SIZE, 549, GroupLayout.PREFERRED_SIZE)
                                                .addGap(10, 10, 10))
                                        .addGroup(layout.createSequentialGroup()
                                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                                                        .addGroup(layout.createSequentialGroup()
                                                                .addGap(0, 0, Short.MAX_VALUE)
                                                                .addComponent(btnStart))
                                                        .addGroup(layout.createSequentialGroup()
                                                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                                                                        .addGroup(layout.createSequentialGroup()
                                                                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                                                                        .addGroup(layout.createSequentialGroup()
                                                                                                .addComponent(lblThreadCount)
                                                                                                .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                                                                                                .addComponent(combThreadCount, GroupLayout.PREFERRED_SIZE, 50, GroupLayout.PREFERRED_SIZE)
                                                                                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                                                                                .addComponent(lblTimeout))
                                                                                        .addGroup(layout.createSequentialGroup()
                                                                                                .addComponent(lblScanSatus)
                                                                                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                                                                                .addComponent(lblCurScanStatus)
                                                                                                .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                                                                                                .addComponent(lblCurScanPro)))
                                                                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                                                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                                                                        .addComponent(lblCurScanProNum, GroupLayout.PREFERRED_SIZE, 61, GroupLayout.PREFERRED_SIZE)
                                                                                        .addComponent(combTimeout, GroupLayout.PREFERRED_SIZE, 49, GroupLayout.PREFERRED_SIZE))
                                                                                .addGap(4, 4, 4)
                                                                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                                                                        .addComponent(lblScantype, GroupLayout.Alignment.TRAILING)
                                                                                        .addGroup(layout.createSequentialGroup()
                                                                                                .addGap(2, 2, 2)
                                                                                                .addComponent(lblSusetime, GroupLayout.PREFERRED_SIZE, 42, GroupLayout.PREFERRED_SIZE)))
                                                                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                                                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                                                                                        .addGroup(layout.createSequentialGroup()
                                                                                                .addComponent(chkFast)
                                                                                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                                                                                .addComponent(chkXSS)
                                                                                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                                                                                .addComponent(chkSQL)
                                                                                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                                                                                .addComponent(chkCSRF)
                                                                                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED))
                                                                                        .addGroup(layout.createSequentialGroup()
                                                                                                .addComponent(lblUsetime)
                                                                                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                                                                .addComponent(btnManage)
                                                                                                .addComponent(btnSearchHistory)))
                                                                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                                                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                                                                        .addGroup(layout.createSequentialGroup()
                                                                                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                                                                        )
                                                                                        .addGroup(layout.createSequentialGroup()
                                                                                                .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                                                                                                .addGap(0, 0, Short.MAX_VALUE)))
                                                                                .addGap(6, 6, 6))
                                                                        .addGroup(layout.createSequentialGroup()
                                                                                .addComponent(lblUrl, GroupLayout.PREFERRED_SIZE, 42, GroupLayout.PREFERRED_SIZE)
                                                                                .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                                                                                .addComponent(txtUrl)))
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
                                        .addComponent(lblUrl)
                                        .addComponent(txtUrl, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(btnStart))
                                .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                        .addComponent(lblThreadCount)
                                        .addComponent(combThreadCount, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(lblTimeout)
                                        .addComponent(combTimeout, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(lblScantype)
                                        .addComponent(chkFast)
                                        .addComponent(chkXSS)
                                        .addComponent(chkSQL)
                                        .addComponent(chkCSRF)
                                )
                                .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                        .addComponent(lblScanSatus)
                                        .addComponent(lblCurScanStatus)
                                        .addComponent(lblCurScanPro)
                                        .addComponent(lblCurScanProNum)
                                        .addComponent(lblSusetime)
                                        .addComponent(lblUsetime)
                                        .addComponent(btnSearchHistory)
                                        .addComponent(btnManage)
                                )
                                .addGap(10, 10, 10)
                                .addComponent(jScrollPaneTotal, GroupLayout.DEFAULT_SIZE, 268, Short.MAX_VALUE)
                                .addContainerGap())
        );

        pack();
    }

    static void buildFrame(JButton btnAlert, JDialog dialogAlert, JLabel lblAlert) {
        GroupLayout jdlgAlertLayout = new GroupLayout(dialogAlert.getContentPane());
        dialogAlert.getContentPane().setLayout(jdlgAlertLayout);
        jdlgAlertLayout.setHorizontalGroup(
                jdlgAlertLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(jdlgAlertLayout.createSequentialGroup()
                                .addGroup(jdlgAlertLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                        .addGroup(jdlgAlertLayout.createSequentialGroup()
                                                .addGap(115, 115, 115)
                                                .addComponent(btnAlert))
                                        .addGroup(jdlgAlertLayout.createSequentialGroup()
                                                .addGap(27, 27, 27)
                                                .addComponent(lblAlert, GroupLayout.PREFERRED_SIZE, 235, GroupLayout.PREFERRED_SIZE)))
                                .addContainerGap(33, Short.MAX_VALUE))
        );
        jdlgAlertLayout.setVerticalGroup(
                jdlgAlertLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(GroupLayout.Alignment.TRAILING, jdlgAlertLayout.createSequentialGroup()
                                .addGap(21, 21, 21)
                                .addComponent(lblAlert, GroupLayout.DEFAULT_SIZE, 28, Short.MAX_VALUE)
                                .addGap(18, 18, 18)
                                .addComponent(btnAlert)
                                .addGap(46, 46, 46))
        );
    }


    private void btnStartActionPerformed() {
        Tools.setToScreenCenter(dialogAlert);
        VulnLogic vulnLogic = new VulnLogic();
        String param = this.txtUrl.getText();
        if (param.isEmpty()) {
            lblAlert.setText("请输入扫描网址");
            dialogAlert.setVisible(true);
            return;
        }
        if (!(chkFast.isSelected() || chkSQL.isSelected() || chkXSS.isSelected() || chkCSRF.isSelected())) {
            lblAlert.setText("请选择扫描类型");
            dialogAlert.setVisible(true);
            return;
        }
        if (!vulnLogic.checkUrl(param) || scanNum == 100) {
            lblAlert.setText("禁止扫描！");
            dialogAlert.setVisible(true);
            return;
        }
        scanNum++;
        VulnTable fm = new VulnTable();
        fm.setSQL(chkSQL.isSelected());
        fm.setXSS(chkXSS.isSelected());
        fm.setCSRF(chkCSRF.isSelected());
        fm.setFAST(chkFast.isSelected());
        fm.setLbl_curScanProNum(lblCurScanProNum);
        fm.setLbl_usetime(lblUsetime);
        fm.setTable(tabResult);
        vulnLogic.joinCommand(param, fm, userName);
        int num = 100 - scanNum;
        lblAlert.setText("剩余扫描次数：" + num);
        dialogAlert.setVisible(true);
    }

    private void btnManagePerformed() {
        Tools.setToScreenCenter(dialogAlert);
        Tools.setToScreenCenter(dialogUserInfo);
        if (managerPrivileges) {
            DefaultTableModel model = (DefaultTableModel) tabUserInfo.getModel();
            for (int i = model.getRowCount() - 1; i >= 0; i--) {
                model.removeRow(i);
            }
            UserTable ut = new UserTable();
            UserLogic ul = new UserLogic();
            ut.setUserTable(tabUserInfo);
            ul.queryUserInfo(ut);
            dialogUserInfo.setVisible(true);
        } else {
            lblAlert.setText("没有权限");
            dialogAlert.setVisible(true);
        }
    }

    private void btnDeletePerformed() {

    }

    private void setBtnSearchHistoryPerformed() {
        Tools.setToScreenCenter(dialogVulnInfo);
        DefaultTableModel model = (DefaultTableModel) tabVulnInfo.getModel();
        for (int i = model.getRowCount() - 1; i >= 0; i--) {
            model.removeRow(i);
        }
        VulnLogic vulnLogic = new VulnLogic();
        vulnLogic.searchVulnInfo(userName, tabVulnInfo);
        dialogVulnInfo.setVisible(true);
    }

    private void btnAlertActionPerformed(java.awt.event.ActionEvent evt) {
        dialogAlert.dispose();
    }

    private void btnSaveActionPerformed(java.awt.event.ActionEvent evt) {
        UserTable ut = new UserTable();
        UserLogic ul = new UserLogic();
        ut.setUserTable(tabUserInfo);
        ul.updateUserInfo(ut);
        btnManagePerformed();
    }

    private JCheckBox chkFast;
    private JCheckBox chkSQL;
    private JCheckBox chkXSS;
    private JCheckBox chkCSRF;
    private JDialog dialogAlert;
    private JDialog dialogUserInfo;
    private JDialog dialogVulnInfo;
    private JTable tabResult;
    private JTable tabUserInfo;
    private JTable tabVulnInfo;
    private JLabel lblAlert;
    private JLabel lblCurScanProNum;
    private JLabel lblUsetime;
    private JTextField txtUrl;
}
