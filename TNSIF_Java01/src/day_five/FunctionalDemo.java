package day_five;

public class FunctionalDemo {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		GreetingDemo gd = () -> 
		System.out.println("Hello Everyone, Welcome to the Java Session");
		
		gd.greet();

	}

}
