package day_four;

public class AcessModifiers {
	public int publicVar = 10;
	private int privateVar = 20;
	protected int protectedVar = 30;
	int defaultVar = 40;
	public void show() {
		System.out.println("Public Variable: "+ publicVar);
		System.out.println("Private Variable: "+ privateVar);
		System.out.println("Protected Variable: "+ protectedVar);
		System.out.println("Default Variable: "+ defaultVar);
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		AcessModifiers am = new AcessModifiers();
		am.show();
	}
}
