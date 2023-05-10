package com.bom.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class ShowSubDao
{
	private Connection	con		= null;
	private String		url		= "jdbc:postgresql://localhost:5432/bom";
	private String		userId	= "bhautik";
	private String		pass	= "Bkp1@4521";
	private String		show	= "Select * from sub_bom;";
	private String		showSub	= "Select sb.*,b.item_name from bom_1 as b join sub_bom as sb on b.item_number=sb.assemble;";
	private String		showSubF	= "Select sb.*,b.item_name from bom_1 as b join sub_bom as sb on b.item_number=sb.assemble where sb.item_name=";

	public ShowSubDao()
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

	public List<List<String>> ShowTable()
	{
		try
		{
			List<List<String>> ls = new ArrayList<List<String>>();
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(show);
			if (rs != null)
			{

				while (rs.next())
				{
					List<String> row = new ArrayList<String>();
					row.add(rs.getString(1));
					row.add(rs.getString(2));
					row.add(rs.getString(3));
					row.add(rs.getString(4));
					row.add(rs.getString(5));
					row.add(rs.getString(6));
					row.add(rs.getString(7));
					row.add((rs.getString(8).equals("t") ? "Yes" : "No"));

					ls.add(row);
				}

				return ls;
			}
			else
			{
				stmt.close();
				// con.close();
				return ls;
			}
		}
		catch (Exception e)
		{
			e.printStackTrace();
			return null;
		}
	}

	public List<List<String>> ShowReferTable()
	{
		try
		{
			List<List<String>> ls = new ArrayList<List<String>>();
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(showSub);
			if (rs != null)
			{
				while (rs.next())
				{
					List<String> row = new ArrayList<String>();
					row.add(rs.getString(1));
					row.add(rs.getString(2));
					row.add(rs.getString(5));
					row.add(rs.getString(8).equals("t") ? "Yes" : "No");
					row.add(rs.getString(9));

					ls.add(row);
				}
				rs.close();
				stmt.close();
				return ls;
			}
			else
			{
				stmt.close();
				return ls;
			}
		}
		catch (Exception e)
		{
			e.printStackTrace();
			return null;
		}
	}

	public List<List<String>> ShowReferTableFil(String name)
	{
		try
		{
			List<List<String>> ls = new ArrayList<List<String>>();
			if(name.equals("nun")) {
				ShowSubDao sbd=new ShowSubDao();
				return sbd.ShowReferTable();
			}
			Statement stmt = con.createStatement();
			showSubF+="'"+name+"';";
			ResultSet rs = stmt.executeQuery(showSubF);
			if (rs != null)
			{
				while (rs.next())
				{
					List<String> row = new ArrayList<String>();
					row.add(rs.getString(1));
					row.add(rs.getString(2));
					row.add(rs.getString(5));
					row.add(rs.getString(8).equals("t") ? "Yes" : "No");
					row.add(rs.getString(9));

					ls.add(row);
				}

				rs.close();
				stmt.close();
				return ls;
			}
			else
			{
				stmt.close();
				return ls;
			}
		}
		catch (Exception e)
		{
			e.printStackTrace();
			return null;
		}
	}

}
