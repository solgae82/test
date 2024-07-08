package com.lambda.test;
/**
 * 자바의 정석
 * 람다식의 타입과 형변환 Test
 *
 */
@FunctionalInterface
interface MyFunction2{
	void myMethod();
}
public class LambdaEx2 {
	public static void main(String[] args) {
		
		
		Object o = new Object();
		String objString = o.toString();
		System.out.println(objString); //java.lang.Object@7852e922
		
		System.out.println("=================");
		
		MyFunction2 f = ()->{};
		Object obj = (MyFunction2)(()->{});
		String str = ((Object) (MyFunction2) (()->{})).toString();
		
		System.out.println(f); //com.lambda.test.LambdaEx2$$Lambda$1/135721597@65ab7765
		System.out.println(obj);//com.lambda.test.LambdaEx2$$Lambda$2/1826771953@1b28cdfa
		System.out.println(str);//com.lambda.test.LambdaEx2$$Lambda$3/1406718218@e9e54c2
		System.out.println((MyFunction2)(()->{}));//com.lambda.test.LambdaEx2$$Lambda$4/250421012@7229724f
		System.out.println(((Object) (MyFunction2) (()->{})).toString());//com.lambda.test.LambdaEx2$$Lambda$5/1283928880@119d7047
	}
}
