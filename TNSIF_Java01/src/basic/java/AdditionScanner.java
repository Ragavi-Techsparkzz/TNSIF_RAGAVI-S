package basic.java;

import java.util.Scanner;

public class AdditionScanner {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
   Scanner scan = new Scanner(System.in);
   System.out.println("Enter the numbers");
   int a = scan.nextInt();
   int b = scan.nextInt();
   int c = a+b;
   System.out.println("Addition of a="+a+" and b="+b+" is " +c);
 
	}

}
