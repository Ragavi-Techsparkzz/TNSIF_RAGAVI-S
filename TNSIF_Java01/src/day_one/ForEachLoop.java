package day_one;

public class ForEachLoop {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int a[]= {10,20,30,40,50};	
		//for integer array
				for(int i:a)
				{
				System.out.println(i);	
				}
				
				char ch[]= {'R','a','g','a','v','i'};
				for(char c:ch)
				{
					System.out.print(c);		
				}
				System.out.println(" ");
				String s1[]= {"Java","is","programming","language"};
		//for String array
				for(String s:s1)
				{
					System.out.print(s+" ");		
				}

	}

}
