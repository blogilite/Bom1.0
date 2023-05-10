package com.bom.model;

public class Bom_Data {
	private String prod_name;
	private int prod_qty;
	private String prod_vendor;
	private String prod_note;

	public String getProd_name() {
		return prod_name;
	}

	public void setProd_name(String prod_name) {
		this.prod_name = prod_name;
	}

	public int getProd_qty() {
		return prod_qty;
	}

	public void setProd_qty(int prod_qty) {
		this.prod_qty = prod_qty;
	}

	public String getProd_vendor() {
		return prod_vendor;
	}

	public void setProd_vendor(String prod_vendor) {
		this.prod_vendor = prod_vendor;
	}

	public String getProd_note() {
		return prod_note;
	}

	public void setProd_note(String prod_note) {
		this.prod_note = prod_note;
	}

}
