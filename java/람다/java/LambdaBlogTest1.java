package com.lambda.test;

import java.util.function.Predicate;


/**
 * 블로그 글에 있는 코드 테스트 1
 *
 */
@FunctionalInterface
interface Calculate {
 int operlation(int a , int b);
 default void def() {System.out.println("하하");};
 static void print() {System.out.println("static");}
}

public class LambdaBlogTest1 {

	private void calculateClassic() {
		Calculate cal = new Calculate() {
			
			@Override
			public int operlation(int a, int b) {
				// TODO Auto-generated method stub
				return a + b;
			}
		};
		
		System.out.println("calculateClassic->" + cal.operlation(3, 4));
	}
	
	private void calculateLambda() {
		Calculate cal = (a , b) -> a + b;
		System.out.println("calculateLambda plus->"+ cal.operlation(5,5));
		
		cal.def();
		Calculate.print();
		
		Calculate cal2 = (a , b) -> a - b;
		System.out.println("calculateLambda minus->"+ cal2.operlation(5,5));
		
	}
	public static void main(String[] args) {
		LambdaBlogTest1 caltest = new LambdaBlogTest1();
		
		caltest.calculateClassic(); //calculateClassic->7
		caltest.calculateLambda();
		/*
		    calculateLambda plus->10
			하하
			static
			calculateLambda minus->0
		 */

		
		Predicate<String> p1 = (a)->a.length()>5;
		
		System.out.println(p1.test("abc")); // false
	}
	
	

}
