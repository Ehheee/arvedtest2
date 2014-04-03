package test;

import java.util.Date;

import thething.arved.utils.AbstractArvedFilter;
import thething.arved.utils.AbstractArvedFilter.ArvedType;
import thething.arved.utils.AbstractArvedFilter.Period;

public class Main {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		
		print(ArvedType.fromString("o"));
		
	}
	
	
	
	public static void print(Object o){
		System.out.println(o);
	}

}
