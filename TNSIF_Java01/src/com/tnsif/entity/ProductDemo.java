package com.tnsif.entity;

public class ProductDemo {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Product p1 = new Product();
		p1.setId(201);
		p1.setPname("Shampoo");
		p1.setPprice(10);
		System.out.println(p1);
		Product p2 = new Product();
		p2.setId(202);
		p2.setPname("Soap");
		p2.setPprice(20);
		System.out.println(p2);
	}
}
