package com.bom.model;

public class UpdateSubBom
{
	private int		refer;
	private String	item_name;
	
	
	
	public UpdateSubBom(int refer, String item_name)
	{
		super();
		this.refer = refer;
		this.item_name = item_name;
	}
	public int getRefer()
	{
		return refer;
	}
	public void setRefer(int refer)
	{
		this.refer = refer;
	}
	public String getItem_name()
	{
		return item_name;
	}
	public void setItem_name(String item_name)
	{
		this.item_name = item_name;
	}

	

}
