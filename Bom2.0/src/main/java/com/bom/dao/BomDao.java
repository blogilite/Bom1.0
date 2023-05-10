package com.bom.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.bom.model.Bom_Data;

public class BomDao
{
	private Connection	con		= null;
	private String		url		= "jdbc:postgresql://localhost:5432/bom";
	private String		userId	= "bhautik";
	private String		pass	= "Bkp1@4521";
	private String		data	= "Select item_name from bom_1 where item_name='";
	private String		insert	= "Insert into Bom_1(item_name,qty,vendor,note) values (? ,? ,? ,?)";
	private String		del		= "DELETE FROM bom_1 WHERE item_name = '";
	private String		subdel	= "DELETE FROM sub_bom WHERE item_name = '";
	private String		upd		= "update bom_1 set qty=? , vendor=? , note=? where item_name=?;";
	private String		upd1	= "update bom_1 set qty=? where item_name=?;";
	private String		upd2	= "update bom_1 set vendor=? where item_name=?;";
	private String		upd3	= "update bom_1 set note=? where item_name=?;";
	private String		upd4	= "update bom_1 set qty=? , note=? where item_name=?;";
	private String		upd5	= "update bom_1 set qty=? , vendor=? where item_name=?;";
	private String		upd6	= "update bom_1 set vendor=? , note=? where item_name=?;";
	
	
	public BomDao()
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

	// Add data in table
	public boolean create(Bom_Data bom)
	{
		try
		{
			PreparedStatement pstmt = con.prepareStatement(insert);
			pstmt.setString(1, bom.getProd_name());
			pstmt.setInt(2, bom.getProd_qty());
			pstmt.setString(3, bom.getProd_vendor());
			pstmt.setString(4, bom.getProd_note());
			pstmt.executeUpdate();
			return true;
		}
		catch (SQLException e)
		{
			return false;
		}
	}

	// Check data is present or not in table
	public boolean check(String name)
	{

		String data1 = data + name + "';";
		try
		{
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(data1);

			while (rs.next())
			{
				String match = rs.getString(1);
				if (match.equals(name) || match == name)
				{
					return true;
				}
			}

		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}

		return false;
	}

	// Update table record
	public boolean update(Bom_Data bom)
	{
		try
		{
			PreparedStatement pstmt = con.prepareStatement(upd);
			pstmt.setInt(1, bom.getProd_qty());
			pstmt.setString(2, bom.getProd_vendor());
			pstmt.setString(3, bom.getProd_note());
			pstmt.setString(4, bom.getProd_name());
			pstmt.executeUpdate();
			return true;
		}
		catch (Exception e)
		{
			return false;
		}
	}

	// Update record base on QTY
	public boolean update1(Bom_Data bom)
	{
		try
		{
			PreparedStatement pstmt = con.prepareStatement(upd1);
			pstmt.setInt(1, bom.getProd_qty());
			pstmt.setString(2, bom.getProd_name());
			pstmt.executeUpdate();
			return true;
		}
		catch (Exception e)
		{
			return false;
		}
	}

	// Update record Vendor data
	public boolean update2(Bom_Data bom)
	{
		try
		{
			PreparedStatement pstmt = con.prepareStatement(upd2);
			pstmt.setString(1, bom.getProd_vendor());
			pstmt.setString(2, bom.getProd_name());
			pstmt.executeUpdate();
			return true;
		}
		catch (Exception e)
		{
			return false;
		}
	}

	// Update record note data
	public boolean update3(Bom_Data bom)
	{
		try
		{
			PreparedStatement pstmt = con.prepareStatement(upd3);
			pstmt.setString(1, bom.getProd_note());
			pstmt.setString(2, bom.getProd_name());
			pstmt.executeUpdate();
			return true;
		}
		catch (Exception e)
		{
			return false;
		}
	}

	public boolean update4(Bom_Data bom)
	{
		try
		{
			PreparedStatement pstmt = con.prepareStatement(upd4);
			pstmt.setInt(1, bom.getProd_qty());
			pstmt.setString(2, bom.getProd_note());
			pstmt.setString(3, bom.getProd_name());
			pstmt.executeUpdate();
			return true;
		}
		catch (Exception e)
		{
			return false;
		}
	}

	public boolean update5(Bom_Data bom)
	{
		try
		{
			PreparedStatement pstmt = con.prepareStatement(upd5);
			pstmt.setInt(1, bom.getProd_qty());
			pstmt.setString(2, bom.getProd_vendor());
			pstmt.setString(3, bom.getProd_name());
			pstmt.executeUpdate();
			return true;
		}
		catch (Exception e)
		{
			return false;
		}
	}

	public boolean update6(Bom_Data bom)
	{
		try
		{
			PreparedStatement pstmt = con.prepareStatement(upd6);
			pstmt.setString(1, bom.getProd_vendor());
			pstmt.setString(2, bom.getProd_note());
			pstmt.setString(3, bom.getProd_name());
			pstmt.executeUpdate();
			return true;
		}
		catch (Exception e)
		{
			return false;
		}
	}

	// Delete the record from database
	public void delete(String name)
	{
		try
		{
			del += name + "';";
			if (check(name))
			{
				subdel += name + "';";
				PreparedStatement pstmt = con.prepareStatement(subdel);
				pstmt.executeUpdate();
				pstmt.close();
			}
			PreparedStatement pstmt = con.prepareStatement(del);
			pstmt.executeUpdate();
			pstmt.close();
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}

	

}