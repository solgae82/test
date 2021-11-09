package com.solgae.gen;

import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class FieldNameGen {
	
	public static String delimeter = "_";	//필드명 연결 문자
	public static String tabWord = "	"; //탭 띄어쓰기
	public static String newLine = System.getProperty("line.separator"); //개행문자
	public static String tableName = "";
	public static boolean preComma = true; // true: ',userId' , false : 'userId,' 
	

	public static String getDelimeter() {
		return delimeter;
	}

	public static String getTabWord() {
		return tabWord;
	}

	public static String getNewLine() {
		return newLine;
	}

	public static String getTableName() {
		return tableName;
	}

	public static boolean isPreComma() {
		return preComma;
	}

	public static void setDelimeter(String delimeter) {
		FieldNameGen.delimeter = delimeter;
	}

	public static void setTabWord(String tabWord) {
		FieldNameGen.tabWord = tabWord;
	}

	public static void setNewLine(String newLine) {
		FieldNameGen.newLine = newLine;
	}

	public static void setTableName(String tableName) {
		FieldNameGen.tableName = tableName;
	}

	public static void setPreComma(boolean preComma) {
		FieldNameGen.preComma = preComma;
	}

	/**
	 * 케멀케이스 단어 만들기 
	 * @description : USER_ID => userId
	 * @param word
	 * @return
	 */
	public static String getCamelWord(String word) {
		String result = "";
		String divTemp = null;
		String remains = null;
		String first = null;
		int len = 0;
		StringBuilder sb = new StringBuilder();
		
		word = word.trim();
		
		if(word.indexOf(delimeter) == -1) {
			result = word.toLowerCase();
		}else {
			StringTokenizer token = new StringTokenizer(word, delimeter);
			int i = 0;
			while(token.hasMoreTokens()) {
				divTemp = token.nextToken().trim();
				if(i==0) {
					sb.append(divTemp.toLowerCase());
				}else {
					len = divTemp.length();
					if(len <=0 ) {continue;}
					if(len == 1) {
						sb.append(divTemp.toUpperCase());
					}else if(len > 1) {
						first =  divTemp.substring(0,1);
						remains = divTemp.substring(1);
						sb.append(first.toUpperCase());
						sb.append(remains.toLowerCase());
						
					}
				}
				i++;
			}
			
			result =sb.toString();
			
			sb.setLength(0);
		}
		
		return result;
	}
	
	/**
	 * 케멀케이스 변환 리스트 담기
	 * @param oriList
	 * @return
	 */
	public static List<String> getCamelList(List<String> oriList){
		
		List<String> cList = new ArrayList<String>();
		if(oriList.size() <= 0) {return cList;}
		for(String word : oriList) {
			cList.add(getCamelWord(word));
		}
		return cList;
	}
	/**
	 * 케멀케이스 단어들 여러줄 출력
	 * @param list
	 * @return
	 */
	public static String getCamelString(List<String> clist) {
		
		if(clist == null || clist.size() <= 0) {return "";}
				
		StringBuilder sb = new StringBuilder();
		for(String fieldNm : clist) {
			sb.append(fieldNm);
			sb.append(newLine);
		}
		
		return sb.toString();
	}
	
	/**
	 * SQL - select 포멧 문자열 반환
	 * @param list
	 * @return
	 */
	public static String getSelectString(List<String> list , List<String> cList) {
		
		if(list == null || list.size() <= 0) {return "";}
				
		StringBuilder sb = new StringBuilder();
		StringBuilder fsb = new StringBuilder();

		sb.append("SELECT ");sb.append(newLine);
		for(String fieldNm : list) {
			if(preComma) {
				fsb.append(tabWord);fsb.append(",");fsb.append(fieldNm);
			}else {
				fsb.append(tabWord);fsb.append(fieldNm);fsb.append(",");
			}
			fsb.append(newLine);
		}
		
		String temp = fsb.toString();
		if(preComma) {
			int len = (tabWord + "," ).length();
			temp = 	tabWord + temp.substring(len, temp.length() );
		}else {
			int len = ("," + newLine).length();
			temp = 	temp.substring(0, (temp.length() - len) );
		}
		fsb.setLength(0);
		
		sb.append(temp);
		
		sb.append(newLine);
		sb.append("FROM ");
		sb.append(newLine);
		sb.append(tabWord);sb.append(tableName);sb.append(newLine);
		sb.append("WHERE");sb.append(newLine);
		sb.append(tabWord);
		sb.append(list.get(0));sb.append("=");sb.append("#{");sb.append(cList.get(0));sb.append("}");
		
		return sb.toString();
	}
	

	/**
	 * SQL - insert 포멧 문자열 반환
	 * @param list
	 * @param cList
	 * @return
	 */
	public static String getInsertString(List<String> list , List<String> cList) {
		
		if(list == null || list.size() <= 0) {return "";}
				
		StringBuilder sb = new StringBuilder();
		StringBuilder fsb = new StringBuilder();

		sb.append("INSERT INTO ");sb.append(tableName);sb.append(newLine);
		sb.append("(");
		sb.append(newLine);
		for(String fieldNm : list) {
			if(preComma) {
				fsb.append(tabWord);fsb.append(",");fsb.append(fieldNm);
			}else {
				fsb.append(tabWord);fsb.append(fieldNm);fsb.append(",");
			}
			fsb.append(newLine);
		}
		
		String temp = fsb.toString();
		if(preComma) {
			int len = (tabWord + ",").length();
			temp = 	tabWord + temp.substring(len, temp.length() );
		}else {
			int len = ("," + newLine).length();
			temp = 	temp.substring(0, (temp.length() - len) );
		}
		fsb.setLength(0);
		sb.append(temp);
		
		sb.append(newLine);
		sb.append(")VALUES(");
		sb.append(newLine);
		for(String fieldNm : cList) {
			if(preComma) {
				fsb.append(tabWord);fsb.append(",");fsb.append("#{");fsb.append(fieldNm);fsb.append("}");
			}else {
				fsb.append(tabWord);fsb.append("#{");fsb.append(fieldNm);fsb.append("}");fsb.append(",");
			}
			fsb.append(newLine);
		}
		
		temp = fsb.toString();
		if(preComma) {
			int len = (tabWord + ",").length();
			temp = 	tabWord + temp.substring(len, temp.length() );
		}else {
			int len = ("," + newLine).length();
			temp = 	temp.substring(0, (temp.length() - len) );
		}
		fsb.setLength(0);
		sb.append(temp);
		
		sb.append(newLine);
		sb.append(")");
		
		return sb.toString();
	}
	
	/**
	 * SQL - insert 포멧 문자열 반환 (조건문 포함)
	 * @param list
	 * @param cList
	 * @return
	 */
	public static String getInsertString2(List<String> list , List<String> cList) {
		
		if(list == null || list.size() <= 0) {return "";}
				
		StringBuilder sb = new StringBuilder();
		StringBuilder fsb = new StringBuilder();
		String cFieldNm = null;
		int i = 0;
		
		sb.append("INSERT INTO ");sb.append(tableName);sb.append(newLine);
		sb.append("(");
		sb.append(newLine);
		fsb.append(tabWord);
		
		if(preComma) {
			fsb.append("<trim prefixOverrides=\",\">");
		}else {
			fsb.append("<trim suffixOverrides=\",\">");
		}
		fsb.append(newLine);
		for(String fieldNm : list) {
			cFieldNm = cList.get(i);
						
			fsb.append(tabWord);fsb.append(tabWord);			
			fsb.append("<if test=\"");fsb.append(cFieldNm);fsb.append(" != null and !");fsb.append(cFieldNm);fsb.append(".equals('')\">");
			if(preComma) {
				fsb.append(",");fsb.append(fieldNm);
			}else {
				fsb.append(fieldNm);fsb.append(",");
			}			
			fsb.append("</if>");			
			fsb.append(newLine);
			i++;
		}
		fsb.append(tabWord);
		fsb.append("</trim>");		
		
		sb.append(fsb.toString());
		fsb.setLength(0);		
		
		sb.append(newLine);
		sb.append(")VALUES(");
		sb.append(newLine);
		sb.append(tabWord);
		
		if(preComma) {
			fsb.append("<trim prefixOverrides=\",\">");
		}else {
			fsb.append("<trim suffixOverrides=\",\">");			
		}	
		fsb.append(newLine);
		
		i = 0;
		for(String fieldNm : cList) {
			cFieldNm = cList.get(i);
			
			fsb.append(tabWord);fsb.append(tabWord);
			fsb.append("<if test=\"");fsb.append(cFieldNm);fsb.append(" != null and !");fsb.append(cFieldNm);fsb.append(".equals('')\">");
			if(preComma) {
				fsb.append(",");fsb.append("#{");fsb.append(cFieldNm);fsb.append("}");
			}else {
				fsb.append("#{");fsb.append(cFieldNm);fsb.append("}");fsb.append(",");
			}	
			fsb.append("</if>");
			fsb.append(newLine);
			i++;
		}
		fsb.append(tabWord);
		fsb.append("</trim>");
		
		sb.append(fsb.toString());
		fsb.setLength(0);
		
		sb.append(newLine);
		sb.append(")");
		
		return sb.toString();
	}
	
	/**
	 * SQL - update 포멧 문자열 반환
	 * @param list
	 * @param cList
	 * @return
	 */
	public static String getUpdateString(List<String> list , List<String> cList) {
		
		if(list == null || list.size() <= 0) {return "";}
				
		StringBuilder sb = new StringBuilder();
		StringBuilder fsb = new StringBuilder();
		int i= 0;
		
		sb.append("UPDATE ");sb.append(tableName);
		sb.append(" SET");
		sb.append(newLine);
		sb.append("(");
		sb.append(newLine);
		for(String fieldNm : list) {
			
			fsb.append(tabWord);
			if(preComma) {
				fsb.append(",");fsb.append(fieldNm);fsb.append("="); fsb.append("#{");fsb.append(cList.get(i));fsb.append("}");
			}else {
				fsb.append(fieldNm);fsb.append("="); fsb.append("#{");fsb.append(cList.get(i));fsb.append("}");fsb.append(",");
			}
			fsb.append(newLine);
			i++;
		
		}
		
		String temp = fsb.toString();
		if(preComma) {
			int len = (tabWord + ",").length();
			temp = 	tabWord + temp.substring(len, temp.length());
		}else {
			int len = ("," + newLine).length();
			temp = 	temp.substring(0, (temp.length() - len) );
		}
		fsb.setLength(0);
		sb.append(temp);
		
		sb.append(newLine);
		sb.append(")");
		sb.append(newLine);
		
		sb.append("WHERE");sb.append(newLine);
		sb.append(tabWord);
		sb.append(list.get(0));sb.append("=");sb.append("#{");sb.append(cList.get(0));sb.append("}");
				
		return sb.toString();
	}
	
	/**
	 * SQL - update 포멧 문자열 반환 (조건문포함)
	 * @param list
	 * @param cList
	 * @return
	 */
	public static String getUpdateString2(List<String> list , List<String> cList) {
		
		if(list == null || list.size() <= 0) {return "";}
				
		StringBuilder sb = new StringBuilder();
		StringBuilder fsb = new StringBuilder();
		String cFieldNm = null;
		int i = 0;
		
		sb.append("UPDATE ");sb.append(tableName);sb.append(" SET");
		sb.append(newLine);
		sb.append("(");
		sb.append(newLine);
		fsb.append(tabWord);
		
		if(preComma) {
			fsb.append("<trim prefixOverrides=\",\">");
		}else {
			fsb.append("<trim suffixOverrides=\",\">");
		}
		
		fsb.append(newLine);
		
		for(String fieldNm : list) {
			cFieldNm = cList.get(i);
			
			fsb.append(tabWord);fsb.append(tabWord);			
			fsb.append("<if test=\"");fsb.append(cFieldNm);fsb.append(" != null and !");fsb.append(cFieldNm);fsb.append(".equals('')\">");
			if(preComma) {
				fsb.append(",");fsb.append(fieldNm);fsb.append("=");fsb.append("#{");fsb.append(cFieldNm);fsb.append("}");
			}else {
				fsb.append(fieldNm);fsb.append("=");fsb.append("#{");fsb.append(cFieldNm);fsb.append("}");fsb.append(",");
			}
			fsb.append("</if>");
			fsb.append(newLine);
			
			i++;
		}
		fsb.append(tabWord);
		fsb.append("</trim>");		
		
		sb.append(fsb.toString());
		fsb.setLength(0);		
		
		sb.append(newLine);
		sb.append(")");
		sb.append(newLine);
		sb.append("WHERE");
		sb.append(newLine);
		sb.append(tabWord);
		sb.append(list.get(0));sb.append("=");sb.append("#{");sb.append(cList.get(0));sb.append("}");
		
		return sb.toString();
	}
	
	/**
	 * SQL - delete 포멧 문자열 반환
	 * @param list
	 * @param cList
	 * @return
	 */
	public static String getDeleteString(List<String> list , List<String> cList) {
		
		if(list == null || list.size() <= 0) {return "";}
				
		StringBuilder sb = new StringBuilder();
		StringBuilder fsb = new StringBuilder();

		sb.append("DELETE FROM ");sb.append(tableName);sb.append(newLine);
		sb.append("WHERE");sb.append(newLine);
		sb.append(tabWord);
		sb.append(list.get(0));sb.append("=");sb.append("#{");sb.append(cList.get(0));sb.append("}");
		
		return sb.toString();
	}
	
	/**
	 * resultMap 태그 자동 생성 
	 * @param list
	 * @param cList
	 * @return
	 */
	public static String getResultMapString(List<String> list , List<String> cList) {
		
		if(list == null || list.size() <= 0) {return "";}
				
		StringBuilder sb = new StringBuilder();
		String cFieldNm = null;
		int i = 0;
		
		sb.append("<resultMap id=\"\" type=\"\">");
		sb.append(newLine);
		
		for(String fieldNm : list) {
			cFieldNm = cList.get(i);
			sb.append(tabWord);
			if(i==0) {
				sb.append("<resultMap id=\"");
			}else {
				sb.append("<resultMap property=\"");
			}
			sb.append(cFieldNm);sb.append("\" column=\"");sb.append(fieldNm);sb.append("\" />");
			sb.append(newLine);
			i++;
		}
		
		sb.append("</resultMap>");
		sb.append(newLine);
		
		
		
		return sb.toString();
	}
	
	public static String getVariblesVoString(List<String> cList) {
		
		if(cList == null || cList.size() <= 0) {return "";}
				
		StringBuilder sb = new StringBuilder();
				
		for(String cFieldNm : cList) {
			
			sb.append("String ");sb.append(cFieldNm);sb.append(" = \"\";");
			sb.append(newLine);
			
		}
				
		return sb.toString();
	}
}
