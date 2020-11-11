package kr.or.bit.service;


import java.io.File;
import java.io.FileWriter;
import java.util.Scanner;

public class Salt {
	
	
	public void addSalt(String id) {
		
		makeFile(id);
		
	}
	public String readSalt(String id) {
		
		return readFile(id);
		
	}
	
	private void makeFile(String id) {
		try {
			FileWriter fr = new FileWriter("C:\\Users\\user\\key\\"+id+".key.txt");
			double d = Math.random();
			String comp = "오늘도 화이팅"+d+"^^!";
			fr.write(comp);
			fr.close();
			System.out.println("파일생성성공:"+id+".key.txt");
		} catch (Exception e) {
			System.out.println("파일생성실패:{id}.key.txt");
			e.printStackTrace();
		}
		
	}
	
	private  String readFile(String id) {
		String data = "";
		try {
			File f = new File("C:\\Users\\user\\key\\"+id+".key.txt");
			Scanner sc = new Scanner(f);
			while(sc.hasNextLine()) {
				data = sc.nextLine();
				System.out.println(data);
				return data;
			}
			
		} catch (Exception e) {
			System.out.println("파일읽기 실패");
			e.printStackTrace();
		}
		System.out.println("data:"+data);
		return data;
		
	}
	
}

	