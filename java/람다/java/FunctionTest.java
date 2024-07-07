package com.lambda.test;

import java.util.function.BiFunction;
import java.util.function.DoubleFunction;
import java.util.function.DoubleToIntFunction;
import java.util.function.DoubleToLongFunction;
import java.util.function.Function;
import java.util.function.IntFunction;
import java.util.function.IntToDoubleFunction;
import java.util.function.IntToLongFunction;
import java.util.function.LongFunction;
import java.util.function.LongToDoubleFunction;
import java.util.function.LongToIntFunction;
import java.util.function.ToDoubleBiFunction;
import java.util.function.ToDoubleFunction;
import java.util.function.ToIntBiFunction;
import java.util.function.ToIntFunction;
import java.util.function.ToLongBiFunction;
import java.util.function.ToLongFunction;

/**
 * 
 * Function 함수형 인터페이스 사용 테스트
 *
 */
public class FunctionTest {

	public static void main(String[] args) {
		
		//----------------// 기본 Function
		// Function<T, R> | R apply(T t) | 입력:(객체 T) , 반환: 객체 R 
		Function<Integer, String> func = t->"integer->String:"+String.valueOf(t);
		System.out.println(func.apply(100)); // integer->String:100
		
		// IntFunction<R> | R apply(int value) | 입력:(int) , 반환: 객체 R 
		IntFunction<String> intFunc = (i)->"int->String:" + String.valueOf(i);
		System.out.println(intFunc.apply(100)); // int->String:100			
		
		// DoubleFunction<R> | R apply(double value) | 입력:(double) , 반환: 객체 R 
		DoubleFunction<String> dFunc = (d)-> ("double->String:"+String.valueOf(d));
		System.out.println(dFunc.apply(33.3)); //double->String:33.3
		
		// LongFunction<R> | R apply(long value) | 입력:(long) , 반환: 객체 R
		LongFunction<String> loFunc = (lo)->("long->String:" + String.valueOf(lo));
		System.out.println(loFunc.apply(200L)); //long->String:200
		
		//----------------//Bi , to기본자료형Bi
		
		// BiFunction<T, U, R> | R apply(T t, U u) | 입력: (객체T,객체U)  ,  반환 : 객체 R
		BiFunction<Integer, Double, String> biFunc = (t,u) -> "(100+20.1)->String:" + String.valueOf(t + u);
		System.out.println(biFunc.apply(100, 20.1)); //(100+20.1)->String:120.1
		
		// ToIntBiFunction<T, U> | int applyAsInt(T t, U u) | 입력: (객체T,객체U)  ,  반환 : int
		ToIntBiFunction<Integer, Double> toIntBiFunc = (t,u)->(int) (t + u);
		System.out.println("(200+10.3)->int:" + toIntBiFunc.applyAsInt(200, 10.3)); //(200+10.3)->int:210
		
		// ToDoubleBiFunction<T, U> | double applyAsDouble(T t, U u) | 입력: (객체T,객체U)  ,  반환 : double
		ToDoubleBiFunction<Integer, Double> toDoubleBiFunc = (t,u)->(double)(t + u);
		System.out.println("(200+10.3)->double"+ toDoubleBiFunc.applyAsDouble(200, 10.3)); //(200+10.3)->double210.3
		
		// ToLongBiFunction<T, U> | long applyAsLong(T t, U u) | 입력: (객체T,객체U)  ,  반환 : long
		ToLongBiFunction<Integer, Double> toLongBiFunc = (t,u)->(long)(t+u+200000000000000L);
		System.out.println("(200+10.3)->long:"+ toLongBiFunc.applyAsLong(200, 10.3));//(200+10.3)->long:200000000000210
		
		//---------------// to기본자료형
		// ToIntFunction<T> | int applyAsInt(T t) | 입력: (객체T) , 반환 : int
		ToIntFunction<Double> toIntFunc = (d)-> d.intValue();
		System.out.println("(20.3)->int:"+toIntFunc.applyAsInt(20.3)); //(20.3)->int:20
		
		// ToDoubleFunction<T> | double applyAsDouble(T t) | 입력: (객체T) , 반환 : double
		ToDoubleFunction<Integer> toDoubleFunc = (i)->i.doubleValue();
		System.out.println("(20)->double:"+toDoubleFunc.applyAsDouble(20));	//(20)->double:20.0
		
		// ToLongFunction<T> | long applyAsLong(T t) | 입력: (객체T) , 반환 : long
		ToLongFunction<Double> toLongFunc = (d)->d.longValue();
		System.out.println("(20.3)->long:"+toLongFunc.applyAsLong(20.3)); //(20.3)->long:20
		
		//--------------//기본자료형To기본자료형
		
		// IntToDoubleFunction | double applyAsDouble(int value) | 입력: int , 반환 : double
		IntToDoubleFunction intToDoubleFunc = (i)->i+0.0;
		System.out.println("(30)->double:"+intToDoubleFunc.applyAsDouble(30));//(30)->double:30.0
		
		// IntToLongFunction | long applyAsLong(int value) | 입력: int , 반환 : long
		IntToLongFunction intToLongFunc =  (i) -> i + 100000000000000L;
		System.out.println("(30)->long:"+ intToLongFunc.applyAsLong(30)); //(30)->long:100000000000030
		
		// DoubleToIntFunction | int applyAsInt(double value); | 입력: double , 반환 : int
		DoubleToIntFunction doubleTointFunc = (d)->(int)d;
		System.out.println("(30.3)->int:"+doubleTointFunc.applyAsInt(30.3)); //(30.3)->int:30
		
		// DoubleToLongFunction | long applyAsLong(double value); | 입력: double , 반환 : long
		DoubleToLongFunction doubleTolongFunc = (d)-> (long)d + 100000000000000L;
		System.out.println("(30.3)->long:"+ doubleTolongFunc.applyAsLong(30.3)); // (30.3)->long:100000000000030
		
		// LongToIntFunction | int applyAsInt(long value); | 입력: long , 반환 : int
		LongToIntFunction longToIntFunc = (long lo)-> Long.valueOf(lo).intValue();
		System.out.println("(100000000000000L)->int:"+longToIntFunc.applyAsInt(100000000000000L));//(100000000000000L)->int:276447232
		
		// LongToDoubleFunction | double applyAsDouble(long value); | 입력: long , 반환 : double
		LongToDoubleFunction longToDoubleFunc = (long lo)-> Long.valueOf(lo).doubleValue();
		System.out.println("(100000000000000L)->double:"+longToDoubleFunc.applyAsDouble(100000000000000L));//(100000000000000L)->double:1.0E14
	}

}
