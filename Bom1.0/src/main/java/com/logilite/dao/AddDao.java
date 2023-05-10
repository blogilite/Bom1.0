package com.logilite.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.logilite.model.Product;

public class AddDao
{
	private Connection	con			= null;
	private String		url			= "jdbc:postgresql://localhost:5432/bom";
	private String		userId		= "bhautik";
	private String		pass		= "Bkp1@4521";
	private String		insert		= "insert into bom(name,qty,batch_code,category,price,note,sub) values (?,?,?,?,?,?,?)";
	private String		select		= "Select * from bom order by sku desc;";
	private String		nameFind	= "Select name from bom where name ILIKE '";
	private String		delete		= "Delete from bom where name=?;";
	private String		nameUp		= "Update bom set name=?,batch_code=?,category=?,qty=?,price=?,note=?,sub=? where sku=?";
//	private String		to			= "Update bom set total_price=? where sku=?";

	// Connection establish between data base and Java Page
	public AddDao()
	{
		try
		{
			Class.forName("org.postgresql.Driver");
			con = DriverManager.getConnection(url, userId, pass);
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}

	// Add data in Table with fetch data
	public boolean addData(Product pr)
	{
		AddDao ad = new AddDao();
		if (ad.check(pr.getName()))
		{
			try
			{
				PreparedStatement pstmt = con.prepareStatement(insert);
				pstmt.setString(1, pr.getName());
				pstmt.setInt(2, pr.getQty());
				pstmt.setString(3, pr.getBatchCode());
				pstmt.setString(4, pr.getCatagory());
				pstmt.setInt(5, pr.getPrice());
				pstmt.setString(6, pr.getNote());
				pstmt.setBoolean(7, pr.isSubPro());

				if (pstmt.executeUpdate() == 1)
				{
					return true;
				}
			}
			catch (Exception e)
			{
				e.printStackTrace();
			}
			return false;
		}
		else
		{
			return false;
		}
	}

	// Check data is present or not bash on the name
	private boolean check(String name)
	{
		try
		{
			Statement stmt = con.createStatement();
			nameFind += name + "';";
			ResultSet rs = stmt.executeQuery(nameFind);

			if (rs.next())
				return false;
			else
				return true;
		}
		catch (Exception e)
		{
			return false;
		}
	}

	// Show the list of the data
	public List<List<String>> showList()
	{
		try
		{
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(select);

			List<List<String>> data = new ArrayList<List<String>>();

			while (rs.next())
			{
				List<String> rw = new ArrayList<String>();
				rw.add(rs.getString(1));
				rw.add(rs.getString(2));
				rw.add(rs.getString(3));
				rw.add(rs.getString(4));
				rw.add(rs.getString(5));
				rw.add(rs.getString(6));
				rw.add(rs.getString(7));
				rw.add((rs.getString(8).equals("t")) ? "Yes" : "No");

				data.add(rw);
			}
			return data;
		}
		catch (Exception e)
		{
			return null;
		}

	}

	// Delete data click on delete button

	public boolean deleteList(String pName)
	{
		try
		{
			/*
			 * PreparedStatement pstmt = con.prepareStatement(ppdelet);
			 * pstmt.setString(1, pName); pstmt.executeUpdate();
			 */
			PreparedStatement pstmt = con.prepareStatement(delete);
			pstmt.setString(1, pName);
			pstmt.executeUpdate();
			return true;
		}
		catch (Exception e)
		{
			return false;
		}
	}

	// Fetch data data base on the name
	public Product fetch(String name)
	{
		try
		{
			String findName = "Select * from bom where name='" + name + "';";
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(findName);
			Product pr = new Product();
			while (rs.next())
			{
				pr.setSku(rs.getInt(1));
				pr.setName(rs.getString(2));
				pr.setBatchCode(rs.getString(4));
				pr.setCatagory(rs.getString(5));
				pr.setPrice(rs.getInt(6));
				pr.setQty(rs.getInt(3));
				pr.setNote(rs.getString(7));
				pr.setSubPro(rs.getBoolean(8));
			}
			return pr;
		}
		catch (Exception e)
		{
			return null;
		}
	}

	// Update all the data
	public boolean updateData(int pSku, String pName, String pBatch, String pCat, int pQty, int pPrice, String note,
			boolean sub)
	{
		boolean isUpd = false;
		try
		{
			if (pName != null && pBatch != null && pCat != null && pQty > 0 && pPrice > 0 && note != null)
			{
				AddDao ad = new AddDao();
				PreparedStatement pstmt = con.prepareStatement(nameUp);
				pstmt.setString(1, pName);
				pstmt.setString(2, pBatch);
				pstmt.setString(3, pCat);
				pstmt.setInt(4, pQty);
				pstmt.setInt(5, pPrice);
				pstmt.setString(6, note);
				pstmt.setBoolean(7, sub);
				pstmt.setInt(8, pSku);

				pstmt.executeUpdate();
//				pstmt = con.prepareStatement(to);
//				int qtyt = ad.getQTY(pSku);
//				pstmt.setInt(1, pPrice * qtyt);
//				pstmt.setInt(2, pSku);
//				pstmt.executeUpdate();
			}
			return true;
		}
		catch (Exception e)
		{
			return isUpd;
		}
	}

	// Get quantity for particular sku
	private int getQTY(int pSku)
	{
		try
		{
			String data = "Select qtyt from bom where sku=" + pSku + ";";
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(data);
			if (rs.next())
			{
				return rs.getInt(1);
			}
			return 0;
		}
		catch (Exception e)
		{
			return 0;
		}

	}

	// Validation for data is Present or note
	public boolean checkData(String name, String batchCode)
	{
		try
		{
			String ch = "select * from bom where name ILIKE'" + name + "' or batch_code ILIKE '" + batchCode + "';";
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(ch);
			if (rs.next())
				return false;
			return true;
		}
		catch (Exception e)
		{

			return false;
		}

	}

}
