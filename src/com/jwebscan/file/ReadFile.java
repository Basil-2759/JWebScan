package com.jwebscan.file;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Basil
 */
public class ReadFile {

    /**
     * 读文件返回读到的每行数据集合
     *
     * @param path   文件路径
     * @param encode 文本编码
     */
    public static List<String> read(String path, String encode) {

        List<String> list = new ArrayList<>();

        FileInputStream fs;

        try {

            fs = new FileInputStream(new File(path));

            InputStreamReader isr = new InputStreamReader(fs, encode);

            BufferedReader br = new BufferedReader(isr);

            String tem = null;

            while ((tem = br.readLine()) != null) {

                list.add(tem);
            }

            br.close();
            isr.close();

        } catch (Exception e) {

        }

        return list;
    }

    /**
     * 读取多个文件返回读到的每行数据集合
     *
     * @param paths  文件路径数组
     * @param encode 文本编码
     */
    public static List<String> readFiles(List<String> paths, String encode) {

        List list = new ArrayList<String>();

        FileInputStream fs;

        try {
            for (String path : paths) {

                fs = new FileInputStream(new File(System.getProperty("user.dir") + "/" + path));

                InputStreamReader isr = new InputStreamReader(fs, encode);

                BufferedReader br = new BufferedReader(isr);

                String tem = null;

                while ((tem = br.readLine()) != null) {

                    list.add(tem);
                }

                br.close();
                isr.close();

            }
        } catch (Exception e) {

        }
        int a = 0;
        return list;
    }

}
