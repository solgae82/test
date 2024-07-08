package com.lambda.test;


import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;


/**
 * 블로그 글에 있는 코드 테스트 1
 *
 */

@FunctionalInterface
interface MyTestFunc {
	public abstract int max(int a , int b);
}

public class LambdaBlogTest2 {
	 
	static MyTestFunc getFunction() {
		return (a,b)-> a*b;
	}
	static void print(MyTestFunc f , int a, int b) {
		int v = f.max(a, b);
		System.out.println(v);
	}
	public static void main(String[] args) {
		 MyTestFunc f1 = new MyTestFunc() {
			
			@Override
			public int max(int a, int b) {
				// TODO Auto-generated method stub
				return a + b;
			}
		};
		System.out.println(f1.max(1, 2));
		
		
		///MyTestFunc f2 = (a, b)-> a + b;
		MyTestFunc f2 = (a, b)-> 11;
		
		
		System.out.println(f2.max(3, 4));
		
		List<String> list = Arrays.asList("aa","bb","cc");
		Collections.sort(list, new Comparator<String>() {
			public int compare(String s1, String s2) { return s2.compareTo(s1); }
		});
		
		System.out.println(list);
		
		Collections.sort(list, (s1,s2)->s1.compareTo(s2));
		System.out.println(list);
		
		print((a,b)-> a + b , 3, 4);
		
		MyTestFunc f3 = getFunction();
		int mul = f3.max(20, 3);
		System.out.println(mul);
		
		// 람다식 타입과 형변환
		// 람다식도 타입이 있지만, 컴파일러가 임의로 정하기 때문에 알 수가 없다
		// 원래는 아래처럼 형변화을 해야하지만, 생략 가능 하기도 하다
		MyTestFunc f4 = (MyTestFunc)((a,b)-> a + b);
		//MyTestFunc f4 = (a,b)-> a + b; //형변환 생략가능==위와 같다
		System.out.println(f4.max(2, 2)); // 4
		
		//함수형 인터페이스로 형변환 가능
		//Object j = ()->{}; // (x)
		
		// 억지로 형변환 한다면 아래처럼 가능하다
		Object j = (Object)(MyTestFunc) ((a,b)->a + b);
		int x = ((MyTestFunc) j).max(3, 3);
		System.out.println(x);
	 }
}
