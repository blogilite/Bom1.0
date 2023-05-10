package com.bom.model;

public class Sub_Bom {
	private String sub_name;
	private int sub_qty;
	private String unit;
	private String sub_vendor;
	private String sub_note;
	private boolean id_sub_bom;
	private int assemble;

	public int getAssemble()
	{
		return assemble;
	}

	public void setAssemble(int assemble)
	{
		this.assemble = assemble;
	}

	public String getSub_name() {
		return sub_name;
	}

	public void setSub_name(String sub_name) {
		this.sub_name = sub_name;
	}

	public int getSub_qty() {
		return sub_qty;
	}

	public void setSub_qty(int sub_qty) {
		this.sub_qty = sub_qty;
	}

	public String getUnit() {
		return unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}

	public String getSub_vendor() {
		return sub_vendor;
	}

	public void setSub_vendor(String sub_vendor) {
		this.sub_vendor = sub_vendor;
	}

	public String getSub_note() {
		return sub_note;
	}

	public void setSub_note(String sub_note) {
		this.sub_note = sub_note;
	}

	public boolean isId_sub_bom() {
		return id_sub_bom;
	}

	public void setId_sub_bom(boolean id_sub_bom) {
		this.id_sub_bom = id_sub_bom;
	}

}
