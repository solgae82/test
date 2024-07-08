package com.lambda.test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.function.Predicate;
import java.util.function.UnaryOperator;

/**
 * 자바의 정석
 * 컬렉션 프레임웤과 함수형 인터페이스 Test
 *
 */
public class LambdaEx4 {
	public static void main(String[] args) {
		
		ArrayList<Integer> list =  new ArrayList<>();
		for(int i=0; i<10; i++) { list.add(i);}
		
		// list의 모든 요소를 출력 , forEach(Consumer<? super Integer> action) 
		list.forEach(i->System.out.print(i+",")); //0,1,2,3,4,5,6,7,8,9,
		System.out.println();
		
		//list에서 2 또는 3의 배수를 제거 , removeIf(Predicate<? super Integer> filter)
		boolean b = list.removeIf(x-> x%2==0 || x%3==0);
		System.out.println(b+"/"+list); //true/[1, 5, 7]
		
		//list에서 모든요소 10 곱하기 , replaceAll(UnaryOperator<Integer> operator)
		list.replaceAll(i->i * 10); //[10, 50, 70]
		System.out.println(list);
		
		Map<String, String> map = new HashMap<>();
		map.put("1","1");
		map.put("2","2");
		map.put("3","3");
		map.put("4","4");		
		
		//map 요소 {k,v} 형식 출력, forEach(BiConsumer<? super String, ? super String> action)
		map.forEach((k,v)-> System.out.print("{"+k+","+v+"}")); //{1,1}{2,2}{3,3}{4,4}
				
		
	}
}
