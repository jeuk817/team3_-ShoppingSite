package kr.or.bit.utils;


import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

import com.mysql.jdbc.Buffer;

public class c_Salt {
	
	/*
	public void addSalt(String id) {
		
		makeFile(id);
		
	}
	*/
	public String readSalt(String file) {
		
		return readFile(file);
		
	}
	/**
	 * 아이디를 입력받고 키파일생성하는 함수
	 * @param id
	 */
	/*
	private void makeFile(String id) {
		try {
			FileWriter fr = new FileWriter(path);
			double d = Math.random();
			String comp = "오늘도 즐거운 코딩"+d+"^^!";
			fr.write(comp);
			fr.close();
			System.out.println("파일생성성공:"+id+".key.txt");
		} catch (Exception e) {
			System.out.println("파일생성실패:{id}.key.txt");
			e.printStackTrace();
		}
		
	}
	*/
	
	
	/**
	 * 
	 * @param id 유저아이디 
	 * @return
	 */
	private  String readFile(String file) {
		String str = null;
		FileInputStream fis = null;
		BufferedInputStream bis = null;
		String path = "C:"+File.separator+"Users"+File.separator+
					"user"+File.separator+"key"+File.separator+file;
		File f = new File(path);
		try {
			fis = new FileInputStream(f);
			bis = new BufferedInputStream(fis);
			byte[] b = new byte[(byte)(f.length())];
			bis.read(b);
			str = new String(b);		
			return str;
		} catch (Exception e) {
			// TODO: handle exception
		}finally {
		
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