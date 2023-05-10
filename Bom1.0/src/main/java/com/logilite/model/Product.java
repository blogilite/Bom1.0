package com.logilite.model;

public class Product
{
	private String	name;
	private int		sku;
	private int		qty;
	private int		totalQty;
	private String	batchCode;
	private String	catagory;
	private String	note;
	private int		price;
	private int		totalPrice;
	private boolean	subPro;

	public boolean isSubPro()
	{
		return subPro;
	}

	public void setSubPro(boolean subPro)
	{
		this.subPro = subPro;
	}

	public String getCatagory()
	{
		return catagory;
	}

	public void setCatagory(String catagory)
	{
		this.catagory = catagory;
	}

	public int getQty()
	{
		return qty;
	}

	public void setQty(int qty)
	{
		this.qty = qty;
	}

	public int getTotalQty()
	{
		return totalQty;
	}

	public void setTotalQty(int totalQty)
	{
		this.totalQty = totalQty;
	}

	public String getNote()
	{
		return note;
	}

	public void setNote(String note)
	{
		this.note = note;
	}

	public int getTotalPrice()
	{
		return totalPrice;
	}

	public void setTotalPrice(int totalPrice)
	{
		this.totalPrice = totalPrice;
	}

	public String getName()
	{
		return name;
	}

	public void setName(String name)
	{
		this.name = name;
	}

	public int getSku()
	{
		return sku;
	}

	public void setSku(int sku)
	{
		this.sku = sku;
	}

	public int getPrice()
	{
		return price;
	}

	public void setPrice(int price)
	{
		this.price = price;
	}

	public String getBatchCode()
	{
		return batchCode;
	}

	public void setBatchCode(String batchCode)
	{
		this.batchCode = batchCode;
	}

}
