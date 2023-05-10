package com.logilite.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class ShowDao
{
	private Connection	con			= null;
	private String		url			= "jdbc:postgresql://localhost:5432/bom";
	private String		userId		= "bhautik";
	private String		pass		= "Bkp1@4521";
	private String		select		= "Select * from bom order by sku asc";
	private String		selectLink	= "select b2.*,b1.sub from bom as b1 join bomSub as b2 on b1.name=b2.name order by sku asc;";

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

	// Show Main Product List with all the details
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
				rw.add(rs.getString(8).equals("t") ? "Yes" : "No");

				data.add(rw);
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
			String selectSb = "Select name,qty from bom order by name asc;";
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(selectSb);

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

	// Show Product with Main Product base on Name
	public List<List<String>> showLink(String name)
	{
		try
		{
			ShowDao sd = new ShowDao();
			Statement stmt = con.createStatement();
			ResultSet rs;

			// Show all the records
			List<List<String>> data = new ArrayList<List<String>>();
			if (name == null || name == "")
			{
				rs = stmt.executeQuery(selectLink);

				while (rs.next())
				{
					List<String> rw = new ArrayList<String>();
					rw.add(rs.getString(1));

					rw.add(rs.getString(2));
					String dt = rs.getString(3);
					rw.add(dt);
					rw.add(rs.getString(4));
					rw.add(rs.getString(5).equals("t") ? "Yes" : "No");
					data.add(rw);
				}
			}
			// Show particular name record
			else
			{
				String selectN = "Select b1.*,b2.sub from bomSub as b1 join bom as b2 on b1.name=b2.name"
						+ " where b1.name ILIKE '";
				selectN += name + "';";

				rs = stmt.executeQuery(selectN);
				String dt;
				if (rs.next())
				{
					List<String> rw = new ArrayList<String>();
					rw.add(rs.getString(1));
					dt = rs.getString(2);
					rw.add(dt);
					rw.add("");
					rw.add(rs.getString(4));
					rw.add((rs.getString(5).equals("t")) ? "Yes" : "No");

					data.add(rw);
					List<List<String>> data1 = sd.isCheck(dt);
					if (data1 != null)
						data.addAll(data1);
				}
				else
				{
					String selectName = "Select sku, name, qty,sub from bom where name ILIKE '";
					selectName += name + "';";
					rs = stmt.executeQuery(selectName);

					List<String> rw = new ArrayList<String>();
					if (rs.next())
					{
						rw.add(rs.getString(1));
						dt = rs.getString(2);

						rw.add(dt);
						rw.add("");
						rw.add(rs.getString(3));
						rw.add((rs.getString(4).equals("t")) ? "Yes" : "No");

						data.add(rw);
						List<List<String>> data1 = sd.isCheck(dt);
						if (data1 != null)
							data.addAll(data1);
					}
				}

			}

			return data;
		}
		catch (Exception e)
		{
			e.printStackTrace();
			return null;
		}
	}

	private List<List<String>> isCheck(String dt)
	{
		try
		{
			Statement stmt = con.createStatement();
			ShowDao sd = new ShowDao();
			String selectPart = "select b1.*,b2.sub from bomSub as b1 join bom as b2 on b1.name=b2.name where b1.subPro ILIKE '"
					+ dt + "';";
			// + "Select * from bomSub where subPro ILIKE '" + dt + "';";
			ResultSet rs = stmt.executeQuery(selectPart);
			List<List<String>> data1 = new ArrayList<List<String>>();

			while (rs.next())
			{
				List<String> rw = new ArrayList<String>();
				rw.add(rs.getString(1));
				String dr = rs.getString(2);
				rw.add(dr);
				rw.add(rs.getString(3));
				rw.add(rs.getString(4));
				rw.add((rs.getString(5).equals("t")) ? "Yes" : "No");

				data1.add(rw);
				List<List<String>> data2 = sd.isCheck(dr);
				if (data2 != null)
				{
					data1.addAll(data2);
				}
				else
				{
					return data1;
				}
			}
			return data1;
		}
		catch (Exception e)
		{
			e.printStackTrace();
			return null;
		}
	}

}
