package com.solgae.gen;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;

public class Generator {

	public static void generate() {
		String inputFile = "input.txt"; //입력파일
    	String outputFile = "output.txt"; //출력파일
    	String tableName = "USERS"; //테이블명
    	BufferedReader br = null;
    	BufferedWriter bw  = null;
    	List<String> list = null;
    	List<String> cList = null;
    	String strTemp = null;
    	
    	File file = null;
    	
    	//파일읽기
    	try {
    		
    		file = new File(inputFile);
    		if(!file.exists() || !file.canRead()) {
    			System.out.println("읽기 파일이 존재하지 않거나 읽을 수 없습니다");
    			return;
    		}
    		
    		br =  new BufferedReader(new FileReader(file));
    		list = new ArrayList<String>();
    		
    		while ((strTemp = br.readLine()) != null) {
    			list.add(strTemp);
    		}
    	}catch (Exception e) {
    		e.printStackTrace();
		}finally {

			try {
				if(br != null) {br.close();}
			} catch (Exception e3) {}
			
			file = null;

		}
    	
    	if(list==null || list.size() <= 0) {
    		System.out.println("출력할 내용이 없습니다");
    		return;
    	}
    	
    	//파일출력 셋팅
    	FieldNameGen.setTableName(tableName);
    	//FieldNameGen.setPreComma(false);
    	cList = FieldNameGen.getCamelList(list);
    	
    	
    	try {
    		
    		file = new File(outputFile);
    		if(!file.exists() || !file.canWrite()) {
    			System.out.println("출력 파일이 존재하지 않거나 쓸 수 없습니다");
    			return;
    		}
    		
    		bw = new BufferedWriter(new FileWriter(file));
    		
    		//기본케멀케이스 필드명 출력
    		bw.write(FieldNameGen.getCamelString(cList));
    		bw.newLine();
    		bw.write("--------------------------------");
    		bw.newLine();
    		bw.write(FieldNameGen.getSelectString(list ,cList));
    		bw.newLine();
    		bw.write("--------------------------------");
    		bw.newLine();
    		bw.write(FieldNameGen.getInsertString(list, cList));
    		bw.newLine();
    		bw.write("--------------------------------");
    		bw.newLine();
    		bw.write(FieldNameGen.getInsertString2(list, cList));
    		bw.newLine();
    		bw.write("--------------------------------");
    		bw.newLine();
    		bw.write(FieldNameGen.getUpdateString(list, cList));
    		bw.newLine();
    		bw.write("--------------------------------");
    		bw.newLine();
    		bw.write(FieldNameGen.getUpdateString2(list, cList));
    		bw.newLine();
    		bw.write("--------------------------------");
    		bw.newLine();
    		bw.write(FieldNameGen.getDeleteString(list, cList));
    		bw.newLine();
    		bw.write("--------------------------------");
    		bw.newLine();
    		bw.write(FieldNameGen.getResultMapString(list, cList));
    		bw.newLine();
    		bw.write("--------------------------------");
    		bw.newLine();
    		bw.write(FieldNameGen.getVariblesVoString(cList));
    		bw.newLine();
    		
    		System.out.println("output.txt 출력완료");    		
    			
    	}catch (Exception e) {
			
    		e.printStackTrace();
		}finally {

			try {
				if(bw != null) {bw.close();}
			} catch (Exception e3) {}

			file = null;
		}
	}
}
