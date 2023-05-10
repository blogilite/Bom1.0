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
import com.bom.model.ListReference;
import com.bom.model.Sub_Bom;

public class SubBomDao
{
	private Connection	con			= null;
	private String		url			= "jdbc:postgresql://localhost:5432/bom";
	private String		userId		= "bhautik";
	private String		pass		= "Bkp1@4521";
	private String		insert2		= "Insert into sub_bom (item_name,qty,unit,assemble,vendor,note,sub_bom) values (? ,? ,? ,?,?,?,?);";
	private String		insert1		= "Insert into Bom_1(item_name,qty,vendor,note) values (? ,? ,? ,?)";
	private String		chQuery		= "Select item_name,assemble from sub_bom where item_name = '";
	private String		dlQuery		= "DELETE FROM sub_bom WHERE item_name = '";
	private String		updqty		= "Update sub_bom set qty=? where item_name=? and assemble=?";
	private String		updvend		= "Update sub_bom set vendor=? where item_name=? and assemble=?";
	private String		updunit		= "Update sub_bom set unit=? where item_name=? and assemble=?";
	private String		updnote		= "Update sub_bom set note=? where item_name=? and assemble=?";
	private String		fetchRef	= "Select item_name,item_number from bom_1; ";
	private String		fetchReW	= "Select item_name,item_number from bom_1 where item_number= ";

	/*
	 * 1.Constructor 2.create 3.check 4.delteData 5.Update
	 */
	public SubBomDao()
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

	public void create(Sub_Bom bom)
	{

		try
		{
			if (bom.isId_sub_bom())
			{
				PreparedStatement pst = con.prepareStatement(insert1);
				pst.setString(1, bom.getSub_name());
				pst.setInt(2, bom.getSub_qty());
				pst.setString(3, bom.getSub_vendor());
				pst.setString(4, bom.getSub_note());
				pst.executeUpdate();
			}

			PreparedStatement pstmt = con.prepareStatement(insert2);
			pstmt.setString(1, bom.getSub_name());
			pstmt.setInt(2, bom.getSub_qty());
			pstmt.setString(3, bom.getUnit());
			pstmt.setInt(4, bom.getAssemble());
			pstmt.setString(5, bom.getSub_vendor());
			pstmt.setString(6, bom.getSub_note());
			pstmt.setBoolean(7, bom.isId_sub_bom());
			pstmt.executeUpdate();

		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}
	}

	public boolean check(String sbname, int sbassemble)
	{
		String data1 = chQuery + sbname + "' and assemble =" + sbassemble + ";";
		try
		{
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(data1);

			while (rs.next())
			{
				String nmatch = rs.getString(1);
				int amatch = rs.getInt(2);
				if ((nmatch.equals(sbname) || nmatch == sbname) && amatch == sbassemble)
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

	public boolean deleteData(String sbname, int sbassemble)
	{
		String dlString = dlQuery + sbname + "' and assemble =" + sbassemble + ";";
		try
		{
			PreparedStatement pstmt = con.prepareStatement(dlString);
			pstmt.executeUpdate();
			pstmt.close();
			BomDao bd = new BomDao();
			bd.delete(sbname);
			return true;
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		return false;
	}

	public boolean updateqty(String sbname, int sub_qty, int sbassemble)
	{
		try
		{
			PreparedStatement pstmt = con.prepareStatement(updqty);
			pstmt.setInt(1, sub_qty);
			pstmt.setString(2, sbname);
			pstmt.setInt(3, sbassemble);
			pstmt.executeUpdate();
			return true;
		}
		catch (Exception e)
		{
			return false;
		}
	}

	public boolean updatevend(String sbname, String sbvendor, int sbassemble)
	{
		try
		{
			PreparedStatement pstmt = con.prepareStatement(updvend);
			pstmt.setString(1, sbvendor);
			pstmt.setString(2, sbname);
			pstmt.setInt(3, sbassemble);
			pstmt.executeUpdate();
			return true;
		}
		catch (Exception e)
		{
			return false;
		}
	}

	public boolean updateunit(String sbname, String sbunit, int sbassemble)
	{
		try
		{
			PreparedStatement pstmt = con.prepareStatement(updunit);
			pstmt.setString(1, sbunit);
			pstmt.setString(2, sbname);
			pstmt.setInt(3, sbassemble);
			pstmt.executeUpdate();
			return true;
		}
		catch (Exception e)
		{
			return false;
		}
	}

	public boolean updateNote(String sbname, String sbnote, int sbassemble)
	{
		try
		{
			PreparedStatement pstmt = con.prepareStatement(updnote);
			pstmt.setString(1, sbnote);
			pstmt.setString(2, sbname);
			pstmt.setInt(3, sbassemble);
			pstmt.executeUpdate();
			return true;
		}
		catch (Exception e)
		{
			return false;
		}
	}

	public List<ListReference> getList()
	{
		List<ListReference> data = new ArrayList<ListReference>();
		try
		{
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(fetchRef);

			while (rs.next())
			{
				ListReference lr = new ListReference();
				lr.setItem_name(rs.getString(1));
				lr.setAssemble(rs.getInt(2));
				data.add(lr);
			}
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		return data;
	}

	public List<ListReference> getList(int sbassemble)
	{
		List<ListReference> data = new ArrayList<ListReference>();
		try
		{
			Statement stmt = con.createStatement();
			fetchReW += sbassemble + ";";
			ResultSet rs = stmt.executeQuery(fetchReW);

			while (rs.next())
			{
				ListReference lr = new ListReference();
				lr.setItem_name(rs.getString(1));
				lr.setAssemble(rs.getInt(2));
				data.add(lr);
			}
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		return data;
	}
}
