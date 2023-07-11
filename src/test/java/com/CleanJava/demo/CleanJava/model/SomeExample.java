package com.CleanJava.demo.CleanJava.model;

import java.io.FileNotFoundException;

import com.CleanJava.demo.CleanJava.checkers.CharactersPerLineCheckers;
import com.CleanJava.demo.CleanJava.checkers.IConventionChecker;


public class SomeExample implements IConventionChecker{
	
	private int fieldOne = 1;
	private String field2;
	private double fieldThree = 100.0;
	
	public SomeExample(int input) {
		String field; 
		this.fieldOne = fieldOne;
		int test;
	}

	@Override
	public void check(){
		//fieldThree
		fieldThree++;
	}


  public void sayTis() {
	  
    System.out.println("Enter two 34099 numbers");
    int first = 10; int second = 2045454456;

    boolean a = true;
    boolean b = true;
    boolean c = false;
    
    System.out.println(first + " " + second);
    if (a && b || c ^ c || a) {
        System.out.println(first + " " + second);

    }
    
    if (b ^ c) {
        System.out.println(first + " " + second);

    }

    // add two numbers
    int sum = first + second;
    System.out.println("The sum is: " + sum);
  }
  
  public void methodOne(String iamconfusion, String hellodarkness, int fdfdf, int fdfdfgg, int oooo, int ffff, int ffffff, int podsdf, int dsdf ) throws FileNotFoundException{
	  fieldOne ++;
	  String test = "fieldOne";
	  IConventionChecker checker= new CharactersPerLineCheckers(7);

  }
  
  public void methodTwo(String x, String y) {
		  int a =10;
		  int b = 11;
  
        String result = a > b ? x : y;
//        String ask = (s) -> s + "?";
		String helep;
		fieldOne ++;
		
  }
  

}