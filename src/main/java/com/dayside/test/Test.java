package com.dayside.test;

import java.util.Random;
import java.util.stream.IntStream;

public class Test {

	public static void main(String[] args) {
		Random rd = new Random();
		int a = rd.nextInt(900000)+100000;
		String b = a+" "+"hhh";
		System.out.println(b);
		
		
	}

}
