package com.bom.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.*;

public class ShowDao
{
	private Connection	con		= null;
	private String		url		= "jdbc:postgresql://localhost:5432/bom";
	private String		userId	= "bhautik";
	private String		pass	= "Bkp1@4521";
	private String		show	= "Select * from bom_1";
	private String		showSub	= "Select * from sub_bom;";
	private String		showBom	= "Select b.*,sb.item_name from bom_1 as b join sub_bom as sb on b.item_number=sb.assemble;";

	// Connection to data base
	public ShowDao()
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

	// Show table of bom
	public List<List<String>> showTable()
	{
		try
		{
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(show);

			List<List<String>> ls = new ArrayList<List<String>>();

			while (rs.next())
			{
				List<String> row = new ArrayList<String>();
				row.add(rs.getString(1));
				row.add(rs.getString(2));
				row.add(rs.getString(3));
				row.add(rs.getString(4));
				row.add(rs.getString(5));

				ls.add(row);
			}

			/*
			 * String table = "<table style=\"border: 1px solid black;\">" +
			 * "<thead><tr><th>Part Number</th><th>Part_name</th>" +
			 * "<th>Quantity</th><th>Vendor</th><th>Note</th><th>Action</th></tr></thead>";
			 * for (List<String> row : ls) { table += "<tr>"; for (String cell :
			 * row) { table += "<td>" + cell + " </td>"; } String data =
			 * row.get(1); table += "<td>\n" +
			 * "          <form action=\"CreateBom\" method=\"post\">\n" +
			 * "            <input type=\"hidden\" name=\"proname\" value=" +
			 * data + ">\n" +
			 * "            <button type=\"submit\" name=\"delete\" value=\"Delete Bom\">Delete</button>\n"
			 * + "          </form>\n" + "        </td>"; table += "</tr>"; }
			 * table += "</table>";
			 */

			return ls;

		}
		catch (Exception e)
		{
			e.printStackTrace();
			return null;
		}
	}

	// Show table of bom with sub bom
	public String showSubBomTable()
	{
		try
		{
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(showSub);

			List<List<String>> ls = new ArrayList<List<String>>();

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
				row.add(rs.getString(8));

				ls.add(row);
			}

			String table = "<table style=\"border: 1px solid black;\">"
					+ "<thead><tr><th>Part Number</th><th>Part_name</th>"
					+ "<th>Quantity</th><th>Unit of mesurment</th><th>Reference</th><th>Vendor</th><th>Note</th><th>Is Bom</th></tr></thead>";
			for (List<String> row : ls)
			{
				table += "<tr>";
				for (String cell : row)
				{
					table += "<td>" + cell + " </td>";
				}
				table += "</tr>";
			}
			table += "</table>";

			rs.close();
			stmt.close();
			// con.close();

			return table;
		}
		catch (Exception e)
		{
			e.printStackTrace();
			return null;
		}
	}

	//
	public List<List<String>> showSubTable()
	{
		List<List<String>> ls = new ArrayList<List<String>>();
		try
		{
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(showBom);

			while (rs.next())
			{
				List<String> row = new ArrayList<String>();
				row.add(rs.getString(1));
				row.add(rs.getString(2));
				row.add(rs.getString(3));
				row.add(rs.getString(6));

				ls.add(row);
			}

			return ls;
		}
		catch (

		Exception e)
		{
			e.printStackTrace();
			return null;
		}
	}

}
