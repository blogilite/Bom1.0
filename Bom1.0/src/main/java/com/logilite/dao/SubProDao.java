package com.logilite.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import com.logilite.model.Product;

public class SubProDao
{

	private Connection	con			= null;
	private String		url			= "jdbc:postgresql://localhost:5432/bom";
	private String		userId		= "bhautik";
	private String		pass		= "Bkp1@4521";
	private String		select		= "Select * from bomSub order by sku asc";
	private String		selectName	= "Select name,qty from bom order by sku asc;";
	private String		selectSub	= "Select name,qty from bom where finalPro=false order by sku asc;";
	private String		fName		= "Select SKU from bom where name='";
	private String		link		= "Insert into bomSub (sku,name,subPro,qty) values (?,?,?,?);";

	public SubProDao()
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

	public List<List<String>> getListPr()
	{
		try
		{
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(selectName);
			List<List<String>> data = new ArrayList<List<String>>();

			while (rs.next())
			{
				List<String> pr = new ArrayList<String>();
				pr.add(rs.getString(1));
				pr.add(rs.getString(2));

				data.add(pr);
			}

			return data;
		}
		catch (Exception e)
		{
			return null;
		}
	}

	public List<String> getListSb()
	{
		try
		{
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(selectSub);

			List<String> data = new ArrayList<String>();

			while (rs.next())
			{
				data.add(rs.getString(1));
			}

			return data;
		}
		catch (Exception e)
		{
			return null;
		}
	}

	public int findNum(String name)
	{
		int num = 0;
		try
		{
			Statement stmt = con.createStatement();
			fName += name + "' order by sku asc;";
			ResultSet rs = stmt.executeQuery(fName);
			while (rs.next())
			{
				num = rs.getInt(1);
			}
			return num;
		}
		catch (Exception e)
		{
			e.printStackTrace();
			return 0;
		}

	}

	public boolean addForeign(int sku, String name, String data, int qt)
	{
		try
		{
			PreparedStatement pstmt = con.prepareStatement(link);
			pstmt.setInt(1, sku);
			pstmt.setString(2, name);
			pstmt.setString(3, data);
			pstmt.setInt(4, qt);
			pstmt.executeUpdate();
			return true;
		}
		catch (Exception e)
		{
			return false;
		}
	}

	private int getPrice(String data)
	{
		String fetchPrice = "Select price from bom where name='" + data + "' order by sku asc;";
		try
		{
			int price = 0;
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(fetchPrice);
			if (rs.next())
				price = rs.getInt(1);
			return price;
		}
		catch (Exception e)
		{
			return 0;
		}
	}

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

				data.add(rw);
			}

			return data;
		}
		catch (Exception e)
		{
			return null;
		}
	}

	public boolean check(String sbname)
	{
		try
		{
			String name = "Select * from bomSub where name ILIKE '";
			Statement stmt = con.createStatement();
			name += sbname + "';";
			ResultSet rs = stmt.executeQuery(name);
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

	public boolean delete(String name)
	{
		try
		{
			String del = "delete from bomSub where name ='";
			del += name + "';";
			PreparedStatement stmt = con.prepareStatement(del);
			stmt.executeUpdate();
			return true;
		}
		catch (Exception e)
		{
			return false;
		}
	}

	public int getSku(String name)
	{
		try
		{
			String su = "Select sku from bom where name ILIKE '" + name + "';";
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(su);
			if (rs.next())
				return rs.getInt(1);
			return 0;

		}
		catch (Exception e)
		{
			return 0;
		}
	}

	public boolean update(int sku, String sbname, String name, int qty)
	{
		try
		{
			String linkUpdate = "update bomSub set qty=?, subPro=? where name=? and sku=? ;";
			PreparedStatement pstmt = con.prepareStatement(linkUpdate);
			pstmt.setInt(4, sku);
			pstmt.setString(2, name);
			pstmt.setString(3, sbname);
			pstmt.setInt(1, qty);
			pstmt.executeUpdate();
			return true;
		}
		catch (Exception e)
		{
			return false;
		}
	}
}
