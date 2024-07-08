package com.lambda.test;
/**
 * 자바의 정석
 * 외부변수를 참조하는 람다식 Test
 *
 */
@FunctionalInterface
interface MyFunction3{
	void myMethod();
	
}
class Outer {
	int val =10; 		// Outer.this.val
	
	class Inner{
		
		int val= 20; 	// this.val
		
		void method(int i) {
			// void method(final int i) {
			
			int val=30; // final int val=30; 이 지역변수도 자동 final (람다식 내에서 쓰이므로)
		//	i = 10; 	// 이  매개변수도 자동 final 이므로 수정할 수 없다 (람다식 내에서 쓰이므로)
			
			MyFunction3 f = ()->{
				System.out.println("매개변수 i->" + i);	//매개변수 i->100
				System.out.println("지역변수 val->" + val);	//지역변수 val->30
				System.out.println("Inner 클래스 멤버 val->" + (++this.val));	//Inner 클래스 멤버 val->21
				System.out.println("Outer 클래스 멤버 val->" + (++Outer.this.val)); //Outer 클래스 멤버 val->11
			
			};
			
			f.myMethod();
		}
	}
}

public class LambdaEx3{
	public static void main(String[] args) {
		Outer outer = new Outer();
		Outer.Inner inner = outer.new Inner();
		inner.method(100);
		/*
		  	매개변수 i->100
			지역변수 val->30
			Inner 클래스 멤버 val->21
			Outer 클래스 멤버 val->11
		 */
	}
}
