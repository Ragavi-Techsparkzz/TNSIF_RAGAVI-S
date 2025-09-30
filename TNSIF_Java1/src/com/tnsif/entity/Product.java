package com.tnsif.entity;

public class Product {
	private int id;
	private String pname;
	private int pprice;
	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}
	/**
	 * @return the pname
	 */
	public String getPname() {
		return pname;
	}
	/**
	 * @param pname the pname to set
	 */
	public void setPname(String pname) {
		this.pname = pname;
	}
	/**
	 * @return the pprice
	 */
	public int getPprice() {
		return pprice;
	}
	/**
	 * @param pprice the pprice to set
	 */
	public void setPprice(int pprice) {
		this.pprice = pprice;
	}
	@Override
	public String toString() {
		return "Product [id=" + id + ", pname=" + pname + ", pprice=" + pprice + "]";
	}
	
}
