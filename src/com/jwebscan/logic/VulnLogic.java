package com.jwebscan.logic;

import com.jwebscan.model.VulnTable;
import com.jwebscan.tools.DataBaseHelper;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

/**
 * @author Basil
 * @create 2022/4/26 16:44
 */
public class VulnLogic {

    private static Logger log = LoggerFactory.getLogger(VulnLogic.class);
    private static String command;
    private static final List<String> BAN_URL = Collections.singletonList("cnvd.org.cn");

    public void joinCommand(String ip, VulnTable fm, String userName) {
        String parameter;
        List<String> returnIfo;
        if (fm.isSQL()) {
            parameter = "-sV --script=http-sql-injection";
            VulnLogic.command = "doc/nmap-7.92/nmap.exe " + parameter + " " + ip;
            returnIfo = nmapScan(fm.getDomain());
            sqlInjection(returnIfo, fm, ip, userName);
        } else if (fm.isXSS()) {
            parameter = "-sV --script http-xssed.nse";
            VulnLogic.command = "doc/nmap-7.92/nmap.exe " + parameter + " " + ip;
            returnIfo = nmapScan(fm.getDomain());
            xssVuln(returnIfo, fm, ip, userName);
        } else if (fm.isCSRF()) {
            parameter = "-sV --script http-csrf.nse";
            VulnLogic.command = "doc/nmap-7.92/nmap.exe " + parameter + " " + ip;
            returnIfo = nmapScan(fm.getDomain());
            csrfVuln(returnIfo, fm, ip, userName);
        } else if (fm.isFAST()) {
            parameter = "-sV --script vuln";
            VulnLogic.command = "doc/nmap-7.92/nmap.exe " + parameter + " " + ip;
            returnIfo = nmapScan(fm.getDomain());
            fastSearch(returnIfo, fm, ip, userName);
        }
    }

    public void sqlInjection(List<String> returnIfo, VulnTable fm, String ip, String userName) {
        DefaultTableModel tm = (DefaultTableModel) fm.getTable().getModel();
        try {
            if (!returnIfo.isEmpty()) {
                for (int i = 0; i < returnIfo.size(); i++) {
                    if (returnIfo.get(i).contains("http-sql-injection")) {
                        i--;
                        String[] s = returnIfo.get(i).split(" ");
                        tm.addRow(new Object[]{s[0], s[1], "SQL注入"});
                        i++;
                    }
                }
            }
            saveScanInfo(ip, userName, tm);
        } catch (Exception e) {
            e.printStackTrace();
            log.info("解析nmap数据失败...");
        }
    }

    public void saveScanInfo(String ip, String userName, DefaultTableModel tm) {
        for (int i = 0; i < tm.getRowCount(); i++) {
            String sql = "INSERT INTO vuln_info ( url, `port`, state, vuln, user_name) VALUES ('"
                    + ip + "','"
                    + tm.getValueAt(i, 0) + "', '"
                    + tm.getValueAt(i, 1) + "', '"
                    + tm.getValueAt(i, 2) + "', '"
                    + userName + "')";
            DataBaseHelper db = new DataBaseHelper(sql);
            try {
                db.dataBaseExecute(sql);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public void xssVuln(List<String> returnIfo, VulnTable fm, String ip, String userName) {
        DefaultTableModel tm = (DefaultTableModel) fm.getTable().getModel();
        try {
            if (!returnIfo.isEmpty()) {
                for (int i = 0; i < returnIfo.size(); i++) {
                    if (returnIfo.get(i).contains("http-xssed")) {
                        i--;
                        String[] s = returnIfo.get(i).split(" ");
                        tm.addRow(new Object[]{s[0], s[1], "XSS跨域"});
                        i++;
                    }
                }
            }
            saveScanInfo(ip, userName, tm);
        } catch (Exception e) {
            e.printStackTrace();
            log.info("解析nmap数据失败...");
        }
    }

    public void csrfVuln(List<String> returnIfo, VulnTable fm, String ip, String userName) {
        DefaultTableModel tm = (DefaultTableModel) fm.getTable().getModel();
        try {
            if (!returnIfo.isEmpty()) {
                for (int i = 0; i < returnIfo.size(); i++) {
                    if (returnIfo.get(i).contains("http-csrf")) {
                        i--;
                        String[] s = returnIfo.get(i).split(" ");
                        tm.addRow(new Object[]{s[0], s[1], "CSRF跨域"});
                        i++;
                    }
                }
            }
            saveScanInfo(ip, userName, tm);
        } catch (Exception e) {
            e.printStackTrace();
            log.info("解析nmap数据失败...");
        }
    }

    public void fastSearch(List<String> returnIfo, VulnTable fm, String ip, String userName) {
        DefaultTableModel tm = (DefaultTableModel) fm.getTable().getModel();
        try {
            if (!returnIfo.isEmpty()) {
                for (int i = 0; i < returnIfo.size(); i++) {
                    if (returnIfo.get(i).contains("VULNERABLE")) {
                        i -= 2;
                        String[] s = returnIfo.get(i).split(" ");
                        tm.addRow(new Object[]{s[0], s[1], "Web漏洞"});
                        i += 2;
                    }
                }
            }
            saveScanInfo(ip, userName, tm);
        } catch (Exception e) {
            e.printStackTrace();
            log.info("解析nmap数据失败...");
        }
    }

    /**
     * 调用nmap进行扫描
     *
     * @return 执行回显
     */
    public List<String> nmapScan(String ip) {
        Process process;
        List<String> strings = new ArrayList<>();
        try {
            process = Runtime.getRuntime().exec(command);
            log.info("开始对{}进行端口扫描...", ip);
            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream(), StandardCharsets.UTF_8));
            String line;
            while ((line = reader.readLine()) != null) {
                strings.add(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return strings;
    }


    public void searchVulnInfo(String userName, JTable tabVulnInfo) {
        String sql = "select * from vuln_info where user_name = '" + userName + "'";
        DataBaseHelper db = new DataBaseHelper(sql);
        DefaultTableModel tm = (DefaultTableModel) tabVulnInfo.getModel();
        try {
            ResultSet ret = db.pst.executeQuery();
            while (ret.next()) {
                String url = ret.getString("url");
                String port = ret.getString("port");
                String state = ret.getString("state");
                String vuln = ret.getString("vuln");
                String time = ret.getString("time");
                tm.addRow(new Object[]{url, port, state, vuln, time});
            }
            ret.close();
            db.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public boolean checkUrl(String url) {
        for (int i = 0; i < BAN_URL.size(); i++) {
            if (url.equals(BAN_URL.get(i))) {
                return false;
            }
        }
        return true;
    }
}

