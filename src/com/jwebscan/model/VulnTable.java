/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jwebscan.model;

import java.util.List;

import javax.swing.JLabel;
import javax.swing.JTable;

/**
 *
 * @author Administrator
 */
public class VulnTable {
        private JTable table;
	    private String domain;
        private int sumNum=0;
        private int maxThread=20;//最大线程数
        private int timeout=10;//超时时间（单位秒）
        private JLabel lbl_curScanProNum;//已扫描数量
        private JLabel lbl_usetime;
        private boolean SQL;
        private boolean XSS;
        private boolean CSRF;
        private boolean FAST;
        private List<String> list_urls;

    public JTable getTable() {
        return table;
    }

    public void setTable(JTable table) {
        this.table = table;
    }

    public String getDomain() {
        return domain;
    }

    public void setDomain(String domain) {
        this.domain = domain;
    }

    public int getSumNum() {
        return sumNum;
    }

    public void setSumNum(int sumNum) {
        this.sumNum = sumNum;
    }

    public int getMaxThread() {
        return maxThread;
    }

    public void setMaxThread(int maxThread) {
        this.maxThread = maxThread;
    }

    public int getTimeout() {
        return timeout;
    }

    public void setTimeout(int timeout) {
        this.timeout = timeout;
    }

    public JLabel getLbl_curScanProNum() {
        return lbl_curScanProNum;
    }

    public void setLbl_curScanProNum(JLabel lbl_curScanProNum) {
        this.lbl_curScanProNum = lbl_curScanProNum;
    }

    public JLabel getLbl_usetime() {
        return lbl_usetime;
    }

    public void setLbl_usetime(JLabel lbl_usetime) {
        this.lbl_usetime = lbl_usetime;
    }

    public boolean isSQL() {
        return SQL;
    }

    public void setSQL(boolean SQL) {
        this.SQL = SQL;
    }

    public boolean isXSS() {
        return XSS;
    }

    public void setXSS(boolean XSS) {
        this.XSS = XSS;
    }

    public boolean isCSRF() {
        return CSRF;
    }

    public void setCSRF(boolean CSRF) {
        this.CSRF = CSRF;
    }

    public boolean isFAST() {
        return FAST;
    }

    public void setFAST(boolean FAST) {
        this.FAST = FAST;
    }

    public List<String> getList_urls() {
        return list_urls;
    }

    public void setList_urls(List<String> list_urls) {
        this.list_urls = list_urls;
    }
}
