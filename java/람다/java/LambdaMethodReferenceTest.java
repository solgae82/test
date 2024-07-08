package com.lambda.test;

import java.util.function.BiFunction;
import java.util.function.Function;

/**
 * 메서드 참조 방식
 * 람다식이 하나의 메서드만 호출할때 사용가능.
 * 
 * 컴파일러가 예측 가능한 메소드일때 축약형 '(static,instance)::메소드명'  형식 사용가능
 *
 */

class My2Class{
	
	private String name;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
		
	public boolean isName(Object obj) {
		return name.equals(obj);
	}
		
}
public class LambdaMethodReferenceTest {

	public static void main(String[] args) {
		
		// static 메서드 참조
		Function<String, Integer> f1 = (String s)-> Integer.parseInt(s);
		System.out.println("String->Integer변환:"+ f1.apply("34")); //String->Integer변환:34
		
		Function<String, Integer> f2 = Integer::parseInt; // 메서드 참조 방식
		System.out.println("String->Integer변환(메서드참조):"+f2.apply("35")); //String->Integer변환(메서드참조):35
		
		// 매개변수 instance 메소드 참조
		BiFunction<String, String, Boolean> f3 = (s1,s2)->s1.equals(s2);
		System.out.println("String<->String비교:"+f3.apply("test1", "test2"));//String<->String비교:false
		
		BiFunction<String, String, Boolean> f4 = String::equals; // 메서드 참조
		System.out.println("String<->String비교(메서드참조)"+f4.apply("test1", "test1")); //String<->String비교(메서드참조)true
		
		// 특정 객체 인스턴스 메서드 참조
		My2Class obj = new My2Class();
		obj.setName("dong");
				
		Function<String, Boolean> f5 = (s)-> obj.isName(s);
		System.out.println("String비교:"+f5.apply("dong2")); // String비교:false
		
		Function<String, Boolean> f6 = obj::isName; // 외부 특정 메소드 참조
		System.out.println("String비교(메서드참조):"+f6.apply("dong")); // String비교(메서드참조):true
		
		obj.setName("test");
		System.out.println("String비교/'test'이름변경(메서드참조):"+f6.apply("dong")); //String비교/'test'이름변경(메서드참조):false
		
	}
}
