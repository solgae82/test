package com.lambda.test;

import java.util.function.BiFunction;
import java.util.function.BinaryOperator;
import java.util.function.DoubleBinaryOperator;
import java.util.function.DoubleUnaryOperator;
import java.util.function.IntBinaryOperator;
import java.util.function.IntUnaryOperator;
import java.util.function.LongBinaryOperator;
import java.util.function.LongUnaryOperator;
import java.util.function.UnaryOperator;

/**
 * Operator 테스트 
 * 
 * 
 * Function의 변형, 입력타입과 같은 타입을 반환한 다는 점이 다름
 * 매개변수 있음(1개 이상), 반환 타입은 매개변수 타입과 같다
 *
 */
public class OperatorTest {

	public static void main(String[] args) {
		
		// UnaryOperator<T> extends Function<T, T> | T apply(T t) | 매개변수와 같은 타입 반환
		UnaryOperator<Integer> uOerator = i -> i * 10;
		System.out.println("UnaryOperator->" + uOerator.apply(2)); // UnaryOperator->20		
		
		// IntUnaryOperator | int applyAsInt(int operand)
		IntUnaryOperator iuOperator = i -> i *10;
		System.out.println("IntUnaryOperator->"+iuOperator.applyAsInt(3)); //IntUnaryOperator->30
		
		// DoubleUnaryOperator | double applyAsDouble(double operand)
		DoubleUnaryOperator duOperator = d -> d * 1.1;
		System.out.println("DoubleUnaryOperator->"+duOperator.applyAsDouble(100)); //DoubleUnaryOperator->110.00000000000001
		
		// LongUnaryOperator | long applyAsLong(long operand)
		LongUnaryOperator loOperator = lo -> lo * 20;
		System.out.println("LongUnaryOperator->"+ loOperator.applyAsLong(2000000000L));//LongUnaryOperator->40000000000
		
		//------------------------ Bi
		
		// BinaryOperator<T> extends BiFunction<T,T,T> | T apply(T t , T u)
		BinaryOperator<Integer> biOperatory = (i1 , i2)-> i1 * i2;
		System.out.println("BinaryOperator->"+ biOperatory.apply(2, 4)); //BinaryOperator->8
		
		// IntBinaryOperator | int applyAsInt(int left, int right)
		IntBinaryOperator iBiOperator = (i1, i2)-> i1 * i2;
		System.out.println("IntBinaryOperator->"+iBiOperator.applyAsInt(20, 5));//IntBinaryOperator->100
		
		// DoubleBinaryOperator | double applyAsDouble(double left, double right)
		DoubleBinaryOperator dBiOperator = (d1,d2)-> d1 + d2;
		System.out.println("DoubleBinaryOperator->" + dBiOperator.applyAsDouble(2.4, 2.5)); //DoubleBinaryOperator->4.9
		
		// LongBinaryOperator | long applyAsLong(long left, long right)
		LongBinaryOperator loBiOperator = (lo1, lo2) -> lo1 + lo2;
		System.out.println("LongBinaryOperator->"+loBiOperator.applyAsLong(2000000000L, 400000000L)); //LongBinaryOperator->2400000000
		
	}

}
