package com.lambda.test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentLinkedDeque;
import java.util.function.BiFunction;
import java.util.function.Function;

/**
 * 
 * 컬렉션 프레임웍의 인터페이스에 다수의 defualt 메서드가 추가 되었는데, 그 중의 일부는 함수형 인터페이스를 사용한다.
 * 자주 사용하게 될(또는 분석하게 될) 함수형 인터페이스 테스트
 *
 */
public class LambdaConllectionTest {

	public static void main(String[] args) {
		
		/**
		 * 리스트 자료 정렬
		 */
		int loc = "a".compareTo("b"); // b를 기준으로 값이 이전에 있냐(-1), 같은면(0), 이후에 있냐(+1)
		System.out.println(loc); // -1
	
		List<String> list2 = Arrays.asList("aa","bb","cc");
		Collections.sort(list2, new Comparator<String>() {
			public int compare(String s1, String s2) { return s2.compareTo(s1); } //내림차순 정렬
		});
		
		System.out.println(list2); //[cc, bb, aa]
		
		Collections.sort(list2, (s1,s2)->s1.compareTo(s2));//오름차순 정렬
		System.out.println(list2); //[aa, bb, cc]
		
		/*
		 *  Iterable.forEach , Collection.removeIf
		 *  (Collection -> Iterable 상속관계)
		 */
		Collection<Integer> collection = new ConcurrentLinkedDeque<Integer>();
		for(int i=1; i<=10; i++) {collection.add(i);}
		System.out.println(collection); // [1, 2, 3, 4, 5, 6, 7, 8, 9, 10]
		
		//Collection 인터페이스 : boolean removeIf(Predicate<? super E> filter)
		collection.removeIf(element->(element % 2 == 0)); // 짝수는 제거
		System.out.println(collection); // [1, 3, 5, 7, 9]

		List<String> cL = new ArrayList<String>();
		
		//Iterable 인터페이스:  void forEach(Consumer<? super T> action)
		collection.forEach(e-> cL.add("z:" +e));
		System.out.println(cL); // [z:1, z:3, z:5, z:7, z:9]
		
		/**
		 * List.replaceAll
		 * (List -> Collection -> Iterable 상속관계 )
		 */
		List<String> list = Arrays.asList("AA","BB","CC");
		System.out.println(list); //[AA, BB, CC]
		
		//List 인터페이스 :  void replaceAll(UnaryOperator<E> operator)
		list.replaceAll(s -> "소문자:" + s.toLowerCase());
		System.out.println(list); //[소문자:aa, 소문자:bb, 소문자:cc]
		
		
		/**
		 * Map.compute
		 * Map.computeIfAbsent
		 * Map.computeIfPresent
		 * Map.merge
		 * Map.forEach
		 * Map.replaceAll
		 */
		
		Map<String , Integer> map = new HashMap<>();
		map.put("first", 1);
		map.put("second", 2);
		
		
		System.out.println(map); //{first=1, second=2}
		
		/*
		 *  V compute(K key, BiFunction<? super K, ? super V, ? extends V> remappingFunction)
		 *  : map에 추가 ,삭제,업데이트 ,아무것도 안함, 4가지 동작
		 *  
		 *  1.첫번째 매개변수명과 매치되는 키가 map에 있을때, 두번째  매개변수 연산 반환값이 null 인지 여부에 의해 
		 *  		- null 반환 : map에 해당 키를 삭제 후, null 리턴
		 *  		- 값 반환 : map에 put(키명,리턴값)으로 해당 키값을 반환값으로 업데이트 후 반환값으로 리턴
		 *  	
		 *  2.첫번째 매개변수명과 매치되는 키가 map에  없을때, 두번째  매개변수 연산 반환값이 null 인지 여부에 의해
		 *  		- null 반환 : map에 아무것도 하지 않고, null 리턴
		 *  		- 값 반환 : map에 put(키명,반환값)으로  추가 후 ,  반환값 리턴
		 *  		
		 *  (null이 반환되면, map에 있는 키이면 삭제되고, 없는 키이면 추가 되지 않는다)
		 *  
		 *  예) 
		 *  map에 매칭키가 있을때 매개변수 전달 : {first=1} => map.compute("first",("first",1)->{});
		 *  map에 매칭키가 없을때 매개변수 전달 : '매칭없음' => map.compute("third",("third",null)->{});
		 */
		 
		Integer rV1 = map.compute("first", (k,v)-> {return (v==null)?null:v+2;}); // 3반환
		System.out.println(map); //{first=3, second=2}
		System.out.println("firts,반환값->"+rV1); //firts,반환값->3
		
		/*
		 * V computeIfAbsent(K key, Function<? super K, ? extends V> mappingFunction)
		 * : 첫번째 매개변수명과 매칭되는 키가 map에 없을때만 두번째 함수형 매개변수 동작 , 있으면 map에 있던  값만 반환(함수형 매개변수 작동 안함) 
		 * 
		 * 
		 * 1.첫번째 매개변수명과 매칭되는 키가 map에 있을때, map 해당 키값 반환.(두번째 함수형 매개변수 작동 안함)   
		 * 
		 * 2.첫번째 매개변수명과 매칭되는 키가 map에 없을 때, 두번째 함수형 매개변수 작동 
		 * 	- null 반환 : 아무것도 안함 , null 리턴
		 * 	- 값 반환 : map에 추가 , 값 리턴
		 * 
		 * 
		 * 예)
		 * map에 매칭키가 없을때 매개변수 전달 : map.computeIfAbsent("ddd",("ddd")->{});
		 */
		
		
		Integer rV2 = map.computeIfAbsent("third", (k)-> 3); 
		System.out.println(map); //{third=3, first=3, second=2}
		System.out.println("third,반환값->"+rV2); //third,추가값->3
		
		/*
		 * V computeIfPresent(K key, BiFunction<? super K, ? super V, ? extends V> remappingFunction) 
		 * : 첫번째 매개변수명과 매칭되는 키가 map에 있을때만 두번째 함수형 매개변수 로직 동작 , 없으면  null 반환(함수형 매개변수 작동 안함)
		 * 
		 * 1.첫번째 매개변수명과 매칭되는 키가 map에 있을때, 두번째 함수형 매개변수 작동.
		 * 	- 연산결과 (null) 반환 : 해당 키 삭제 , null 리턴
		 *  - 연산결과 (값) 반환 : 해당 키값을 반환값으로 업데이트 , 반환값 리턴
		 *  
		 * 2.첫번째 매개변수명과 매칭되는 키가 map에 없을때, null 반환.(두번째 함수형 매개변수 작동 안함)
		 */  
		Integer rV3 = map.computeIfPresent("first", (k,v)->{return 1;});
		System.out.println(map); // {third=3, first=1, second=2}
		System.out.println("first,반환값->"+ rV3); // first,반환값->1
		
		/*
		 * V merge(K key, V value, BiFunction<? super V, ? super V, ? extends V> remappingFunction)
		 * : 첫번째 매개변수명과 매칭되는 키가 map에 있을때만 두번째 함수형 매개변수(merge로직) 동작, 매칭키가 없으면 단순 추가(함수형 매개변수 작동 안함)
		 * : 추가 , 업데이트, 삭제 가능
		 * 
		 * 1.첫번째 매개변수명과 매칭되는 키가 map에 있을때, 두번째 함수형 매개변수 작동(merge 로직).
		 * 	- 연산결과 (null) 반환 : 해당키 삭제 , null 리턴
		 *  - 연산결과 (값) 반환 : 해당 키값 반환 값으로 업데이트 , 반환값 리턴
		 *  
		 * 2.첫번째 매개변수명과 매칭되는 키가 map에 없을때, (두번째 함수형 매개변수 작동 안함)
		 *  - 두번째 value 값이 있을때 : 맵 단순 추가 / put(key,value) , value 값 반환
		 *  - 두번째 value 값이 null일때 : java.lang.NullPointerException
		 *  
		 *  예)
		 *  map = {third=3, first=1, second=2}
		 *  함수형 매개변수 (merge의 첫번째 매칭키값,merge의 두번째 값)
		 *  map.merge("third", 2, (3,2)->{})
		 */
		Integer rV4 = map.merge("second", 22, (oldVal,newVal)->{return (oldVal + newVal);});
		System.out.println(map); // {third=3, first=1, second=24}
		System.out.println("second,반환값->"+rV4); //second,반환값->24반환값->24
		
		
		// forEach
		map.forEach((k,v)->System.out.print(v+",")); //3,1,24,
		System.out.println();
		
		// replaceAll
		map.replaceAll((k,v)-> ("second".equals(k))?(v * 10):v); //key가 "second" 인것만 곱하기 10
		System.out.println(map); //{third=3, first=1, second=240}
		
	}

}
