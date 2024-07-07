package com.lambda.test;

import java.util.function.BooleanSupplier;
import java.util.function.DoubleSupplier;
import java.util.function.IntSupplier;
import java.util.function.LongSupplier;
import java.util.function.Supplier;


/**
 * Supplier 테스트
 * 데이터 공급 
 * 매개값 없음, 리턴값 있음	
 */
public class SupplierTest {
	public static void main(String[] args) {
		
		//Supplier<T> | T get() | 반환: T 객체
		Supplier<String> suppier = ()-> new String("하하");
		System.out.println(suppier.get()); 	//하하
		
		//BooleanSupplier | boolean getAsBoolean() | 반환: boolean
		BooleanSupplier booleanSup = ()-> true;
		if(booleanSup.getAsBoolean()) System.out.println("booleanSup->true"); //booleanSup->true
		
		//IntSupplier |	int getAsInt() | 반환: int
		IntSupplier intSup = ()-> (int) (Math.random() * 6) + 1;
		System.out.println("주사위 랜덤 숫자(IntSupplier)->" + intSup.getAsInt()); //주사위 랜덤 숫자(IntSupplier)->4
		
		//DoubleSupplier |  double getAsDouble() | 반환:double
		DoubleSupplier doubleSup = ()-> 2.5 * 4;
		System.out.println("DoubleSupplier->"+doubleSup.getAsDouble()); //DoubleSupplier->10.0
		
		//LongSupplier |  long getAsLong() | 반환:long
		LongSupplier longSup = ()-> 20000000000L;
		System.out.println("LongSupplier->"+longSup.getAsLong()); //LongSupplier->20000000000
		
	}
}
