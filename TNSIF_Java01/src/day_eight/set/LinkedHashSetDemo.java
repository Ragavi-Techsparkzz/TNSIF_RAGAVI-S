package day_eight.set;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashSet;
public class LinkedHashSetDemo {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		LinkedHashSet<Integer> lhset = new LinkedHashSet<Integer>();
		lhset.add(10);
		lhset.add(40);
		lhset.add(20);
		lhset.add(70);
		lhset.add(60);
		lhset.add(30);
		lhset.add(10);
		System.out.println("LinkedHashSet : " + lhset);
		ArrayList<Integer> array = new ArrayList<>(lhset);
		Collections.sort(array);
		System.out.println("After sort: " + array);

	}
}
