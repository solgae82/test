package com.lambda.test;

/**
 * 자바의 정석
 * 함수형 인터페이스 타입의 매개변수와 반환타입
 *
 */
interface MyFunction{
	void run();
}

public class LambdaEx1 {

	
	static void excute(MyFunction f) {
		f.run();
	}
	
	static MyFunction getMyFunction() {
//		MyFunction f = () -> System.out.println("f3.run(): v1");
//		return f;
		
		return ()->System.out.println("f3.run(): v2");
	}
	
	public static void main(String[] args) {
		
		//람다식으로 run()구현
		MyFunction f1 = () -> System.out.println("f1.run()");
		f1.run(); // f1.run()

		//익명 클래스로 runs()구현
		MyFunction f2 = new MyFunction() {
			
			@Override
			public void run() {
				System.out.println("f2.run()");
				
			}
		};		
		f2.run(); //f2.run()
		
		//람다식 구현체 반환
		MyFunction f3 = getMyFunction();
		f3.run(); // f3.run(): v2
		

		excute(()->System.out.println("excute->run()")); // excute->run()
	}

}
