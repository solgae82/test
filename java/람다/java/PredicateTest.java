package com.lambda.test;

import java.util.function.BiPredicate;
import java.util.function.DoublePredicate;
import java.util.function.IntPredicate;
import java.util.function.LongPredicate;
import java.util.function.Predicate;

/**
 * Predicate 테스트 
 * 
 * 
 * Function의 변형, boolean만 반환한 다는 점이 다름, 조건을 표현하는데 사용
 * 매개변수 1개, 반환 타입은 boolean
 *
 */
public class PredicateTest {

	public static void main(String[] args) {
		
		//Predicate<T> | boolean test(T t) |  반환: boolean , 객체 T를 조사
		Predicate<Integer> isNatural = s->s > 0;
		if(isNatural.test(1)) System.out.println("0  초과"); // 0 초과
		
		Predicate<String> isEmptyString = s -> s.length() <= 0;
		if(isEmptyString.test("")) System.out.println("비었다"); // 비었다
		
		//IntPredicate | boolean test(int value) | 반환: boolean , int 값을 조사
		IntPredicate intNatural = i-> i > 0;
		if(isNatural.test(11)) System.out.println("0  초과(IntPredicate)"); // 0  초과(IntPredicate)		
		
		//DoublePredicate | boolean test(double value) | 반환: boolean , double 값을 조사
		DoublePredicate doubleNatural = d -> d > 1.0;
		if(doubleNatural.test(1.1)) System.out.println("1.0  초과(DoublePredicate)"); //1.0  초과(DoublePredicate)
		
		//LongPredicate | boolean test(long value) | 반환: boolean , long 값을 조사
		LongPredicate longNatral = lo -> lo > 0L;
		if(longNatral.test(1L)) System.out.println("0L 초과 (LongPredicate)");
		
		//BiPredicate<T, U> | boolean test(T t, U u) | 반환: boolean , 객체 T와 U를 비교 조사
		BiPredicate<String, String> iqStr = (a, b)-> a.equals(b);
		if(iqStr.test("test","test")) System.out.println("같다"); // 같다
		
		
	}

}
