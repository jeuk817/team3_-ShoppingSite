package kr.or.bit.utils;


import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

import com.mysql.jdbc.Buffer;

public class c_Salt {

    public String readSalt(String file) {

        return readFile(file);

    }

    private String readFile(String file) {
    	System.out.println("readFile");
        String str = null;
        FileInputStream fis = null;
        BufferedInputStream bis = null;
        
//        String path = "C:" + File.separator + "Users" + File.separator +
//                "user" + File.separator + "key" + File.separator + file;
        
        String path = "C:" + File.separator + "Users" + File.separator +
        		"user" + File.separator + "git" + File.separator + "team3_ShoppingSite" + File.separator + file;
        System.out.println("path: " + path);
        File f = new File(path);
        try {
            fis = new FileInputStream(f);
            bis = new BufferedInputStream(fis);
            byte[] b = new byte[(byte) (f.length())];
            bis.read(b);
            str = new String(b);
            return str;
        } catch (Exception e) {
            // TODO: handle exception
        } finally {

            try {
                fis.close();
                bis.close();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        return str;
    }

}