package com.lambda.test;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

/**
 * 자바의 정석
 * 함수형 인터페이스 기본형 사용 Test
 *
 */
public class LambdaEx5 {

	public static void main(String[] args) {
		Supplier<Integer> s = ()-> (int)(Math.random() *100) + 1;
		Consumer<Integer> c = i-> System.out.print(i+",");
		Predicate<Integer> p = i-> i%2==0; // 짝수면 true
		Function<Integer, Integer> f = i-> i/10*10; // i 값의 1의 자리 없애기(15->10)
		
		List<Integer> list = new ArrayList<>();
		
		//랜덤 숫자 10개 리스트 생성
		makeRandomList(s, list);
		System.out.println(list); //[88, 36, 57, 85, 36, 97, 63, 68, 72, 11]
		
		//리스트에서 짝수 인것만 출력
		printEventNum(p, c, list); //[88,36,36,68,72,] 
		
		//리스트 모든 값들 1의 자리를 0으로 만듦
		List<Integer> newList = doSomething(f, list);
		System.out.println(newList); //[80, 30, 50, 80, 30, 90, 60, 60, 70, 10]
		
	}
	
	static <T> List<T> doSomething(Function<T,T> f, List<T> list){
		List<T> newList = new ArrayList<T>(list.size());
		for(T t : list) {
			newList.add(f.apply(t));
		}
		return newList;
	}
	
	static <T> void printEventNum(Predicate<T> p, Consumer<T> c, List<T> list) {
		System.out.print("[");
		for(T t : list) {
			if(p.test(t)) {
				c.accept(t);
			}
		}
		System.out.println("]");
	}
	
	static <T> void makeRandomList(Supplier<T> s , List<T> list) {
		for(int i=0; i<10; i++) {
			list.add(s.get());
		}
	}

}
