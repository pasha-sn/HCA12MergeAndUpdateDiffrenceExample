package com.hibernateinfo.client;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Pasha
 * Remember the golden rule: readable code is often faster code. 
 * Produce readable code first and only change it if it proves to be too slow.
 */
public class ClientTest3ForeachJava8Example {

	public static void main(String[] args) 
	{		
		//https://www.mkyong.com/java8/java-8-foreach-examples/
		//In Java 8, you can loop a List with forEach + lambda expression 
		//or method reference.
		List<String> items = new ArrayList<>();
		items.add("A");
		items.add("B");
		items.add("C");
		items.add("D");
		items.add("E");

		//lambda
		//Output : A,B,C,D,E
		items.forEach(item->System.out.print(item + ", "));
		System.out.println();
		System.out.println("***************************************");
			
		//Output : C
		items.forEach(item->{
			if("C".equals(item)){
				System.out.println(item);
			}
		});
		System.out.println("***************************************");	
		
		//method reference
		//Output : A,B,C,D,E
		items.forEach(System.out::println);
		System.out.println("***************************************");
		
		//Stream and filter
		//Output : B
		items.stream()
			.filter(s->s.contains("B"))
			.forEach(System.out::println);		
	}
}
/*
A, B, C, D, E, 
***************************************
C
***************************************
A
B
C
D
E
***************************************
B
*/