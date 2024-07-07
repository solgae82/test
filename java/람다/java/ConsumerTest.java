package com.lambda.test;

import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.function.DoubleConsumer;
import java.util.function.IntConsumer;
import java.util.function.LongConsumer;
import java.util.function.ObjDoubleConsumer;
import java.util.function.ObjIntConsumer;
import java.util.function.ObjLongConsumer;

/**
 * Consumer 테스트
 * 데이터  소비 
 * 매개값 있음, 리턴값 없음	
 */
public class ConsumerTest {

	public static void main(String[] args) {
		
		// Consumer<String> | void accept(T t)
		Consumer<String> consumer = s-> System.out.println("Consumer->"+s);
		consumer.accept("홍길동");//Consumer->홍길동
		
		// BiConsumer<T, U> | void accept(T t, U u) 
		BiConsumer<Integer, Double> biConsumer = (i,d)->System.out.println("BiConsumer->"+(i + d));
		biConsumer.accept(11, 20.3); //BiConsumer->31.3

		//IntConsumer | void accept(int value)
		IntConsumer intConsumer = (i)->System.out.println("IntConsumer->"+ i);
		intConsumer.accept(30); //IntConsumer->30
		
		//DoubleConsumer | void accept(double value)
		DoubleConsumer doubleConsumer = (d)->System.out.println("DoubleConsumer->"+d);
		doubleConsumer.accept(33.3); //DoubleConsumer->33.3
		
		//LongConsumer | void accept(long value)
		LongConsumer longConsumer = (lo)->System.out.println("LongConsumer->"+lo);
		longConsumer.accept(200000000000L); //LongConsumer->200000000000
		
		//ObjIntConsumer | void accept(T t, int value);
		ObjIntConsumer<String> objIntConsumer = (s ,  i) -> System.out.println(s + "->"+i);
		objIntConsumer.accept("ObjIntConsumer", 33); // ObjIntConsumer->33
		
		//ObjDoubleConsumer | void accept(T t, double value);
		ObjDoubleConsumer<Integer> objDoubleConsumer = (i,d)->System.out.println("ObjDoubleConsumer->"+(i + d));
		objDoubleConsumer.accept(20, 30.3); //ObjDoubleConsumer->50.3
		
		//ObjLongConsumer | void accept(T t, long value);
		ObjLongConsumer<String> objLongConsumer = (s ,  lo) -> System.out.println(s + "->"+lo);
		objLongConsumer.accept("ObjLongConsumer", 200000000000L); // ObjLongConsumer->200000000000
		
	}

}
