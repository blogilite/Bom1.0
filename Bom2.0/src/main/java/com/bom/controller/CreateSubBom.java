package com.bom.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bom.dao.ShowSubDao;
import com.bom.dao.SubBomDao;
import com.bom.model.ExtraFunction;
import com.bom.model.ListReference;
import com.bom.model.Sub_Bom;

@WebServlet(urlPatterns = { "/CreateSubBom" }, name = "Create_Sub_Bom")
public class CreateSubBom extends HttpServlet
{
	private static final long	serialVersionUID	= 1L;

	private SubBomDao			subDao;
	private ShowSubDao			showSubDao;

	public CreateSubBom()
	{
		super();
		subDao = new SubBomDao();
		showSubDao = new ShowSubDao();
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
	{
		try
		{

			List<ListReference> ls = subDao.getList();
			req.setAttribute("listData", ls);
			req.setAttribute("show", showSubDao.ShowTable());

			RequestDispatcher rsd = req.getRequestDispatcher("create_bom_component.jsp");
			rsd.include(req, resp);
		}
		catch (Exception e)
		{
			req.setAttribute("listData", null);

			RequestDispatcher rsd = req.getRequestDispatcher("create_bom_component.jsp");
			rsd.forward(req, resp);
		}
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		String sbtn = request.getParameter("submit");
		PrintWriter pwout = response.getWriter();

		// Create Bom Data in table
		if (sbtn != null && sbtn.equals("Create Sub Bom"))
		{
			Sub_Bom sbom = new Sub_Bom(); // create new subBom

			String sbname = request.getParameter("sub_pro_name");
			String sbq = request.getParameter("sub_pro_qty");
			int sbqty = Integer.parseInt(sbq != null ? sbq : "1");
			String sbunit = request.getParameter("sub_pro_measu");
			String sba = request.getParameter("sub_pro_assemble");
			int sbassemble = Integer.parseInt(sba != null ? sba : "1");
			String sbvendor = request.getParameter("sub_pro_vendor");
			String sbnote = request.getParameter("sub_pro_note");
			String sbi = request.getParameter("sub_pro_sub_bom");
			boolean sbis = false;
			if (sbi != null && sbi.equals("on"))
				sbis = true;

			sbom.setSub_name(sbname);
			sbom.setSub_qty(sbqty);
			sbom.setUnit(sbunit);
			sbom.setAssemble(sbassemble);
			sbom.setSub_vendor(sbvendor);
			sbom.setSub_note(sbnote);
			sbom.setId_sub_bom(sbis);

			System.out.println(sbom.getAssemble() + ", " + sbom.getSub_name() + ", " + sbom.getSub_qty() + ", "
					+ sbom.isId_sub_bom());

			if (sbom.getAssemble() > 0 && sbom.getSub_name() != null && sbom.getSub_qty() > 0)
			{
				subDao.create(sbom);
				sbom = null;
				List<ListReference> ls = subDao.getList();
				request.setAttribute("listData", ls);
				RequestDispatcher reqd = request.getRequestDispatcher("create_bom_component.jsp");

				request.setAttribute("lineData", "Data added succefully in server");
				request.setAttribute("show", showSubDao.ShowTable());
				reqd.include(request, response);
			}
			else
			{
				response.setContentType("text/html");
				List<ListReference> ls = subDao.getList();
				request.setAttribute("listData", ls);
				request.setAttribute("show", showSubDao.ShowTable());
				request.setAttribute("lineData", "Please Fill all the data!");
				RequestDispatcher reqd = request.getRequestDispatcher("create_bom_component.jsp");
				reqd.include(request, response);
			}
		}

		// Delete Data
		String dbtn = request.getParameter("delete");
		if (dbtn != null && dbtn.equals("Delete Sub Bom"))
		{
			try
			{
				String sbname = request.getParameter("sub_pro_name");

				String sba = request.getParameter("sub_pro_assemble");
				int sbassemble = Integer.parseInt(sba != null ? sba : "-1");

				if (sbname != null && sbassemble > 0)
				{
					response.setContentType("text/html");
					boolean isPresent = subDao.deleteData(sbname, sbassemble);

					if (isPresent)
					{
						List<ListReference> ls = subDao.getList();
						request.setAttribute("listData", ls);
						request.setAttribute("show", showSubDao.ShowTable());
						request.setAttribute("lineData", sbname + " is delete succefully");
						RequestDispatcher reqd = request.getRequestDispatcher("create_bom_component.jsp");
						reqd.include(request, response);
					}
					else
					{
						List<ListReference> ls = subDao.getList();
						request.setAttribute("listData", ls);
						request.setAttribute("show", showSubDao.ShowTable());
						request.setAttribute("lineData", sbname + " is not found");
						RequestDispatcher reqd = request.getRequestDispatcher("create_bom_component.jsp");
						reqd.include(request, response);
					}
				}
				else
				{
					List<ListReference> ls = subDao.getList();
					request.setAttribute("listData", ls);
					request.setAttribute("show", showSubDao.ShowTable());
					request.setAttribute("lineData", "Please, fill item name or assemble data First...!");
					RequestDispatcher reqd = request.getRequestDispatcher("create_bom_component.jsp");
					reqd.include(request, response);
				}
			}
			catch (Exception e)
			{
				List<ListReference> ls = subDao.getList();
				request.setAttribute("listData", ls);
				request.setAttribute("show", showSubDao.ShowTable());
				request.setAttribute("lineData", "Please, fill item name or assemble data First...!");
				RequestDispatcher reqd = request.getRequestDispatcher("create_bom_component.jsp");
				reqd.include(request, response);
			}
		}

		String ubt = request.getParameter("upd");
		if (ubt != null && ubt.equals("Update"))
		{
			try
			{
				String sbname = request.getParameter("sub_pro_name");
				String sba = request.getParameter("sub_pro_assemble");
				int sbassemble = Integer.parseInt(sba != null ? sba : "-1");
				request.setAttribute("prona", sbname);
				List<ListReference> ls = subDao.getList(sbassemble);
				request.setAttribute("listData", ls);
				request.setAttribute("show", showSubDao.ShowTable());
				RequestDispatcher reqd = request.getRequestDispatcher("create_bom_component.jsp");
				reqd.include(request, response);
			}
			catch (Exception e)
			{
				e.printStackTrace();
			}
		}

		// Update data in database
		String ubtn = request.getParameter("update");
		if (ubtn != null && ubtn.equals("Update Sub Bom"))
		{
			try
			{
				String sbname = request.getParameter("sub_pro_name");
				String sba = request.getParameter("sub_pro_assemble");
				int sbassemble = Integer.parseInt(sba != null ? sba : "-1");
				response.setContentType("text/html");

				boolean isPresent = subDao.check(sbname, sbassemble);

				if (isPresent && sbname != "")
				{
					Sub_Bom sbom = new Sub_Bom(); // create new subBom

					String sbq = request.getParameter("sub_pro_qty");
					int sbqty = Integer.parseInt(sbq != "" ? sbq : "0");
					String sbu = request.getParameter("sub_pro_measu");
					String sbunit = (sbu == "") ? "NAN" : sbu;
					String sbv = request.getParameter("sub_pro_vendor");
					String sbvendor = (sbv == "") ? "NAN" : sbv;
					String sbn = request.getParameter("sub_pro_note");
					String sbnote = (sbn == "") ? "NAN" : sbn;

					sbom.setSub_name(sbname);
					sbom.setSub_qty(sbqty);
					sbom.setAssemble(sbassemble);
					sbom.setSub_vendor(sbvendor);
					sbom.setSub_note(sbnote);

					if (sbqty > 0 || !sbunit.equals("NAN") || sbvendor.equals("NAN") || sbnote.equals("NAN"))
					{
						boolean sb = true;
						if (sbqty > 0)
						{
							boolean isp = subDao.updateqty(sbname, sbqty, sbassemble);
							if (!isp)
							{
								sb = isp && sb;
								pwout.print("Sorry, Your data is not updated!");
							}
						}
						if (!sbvendor.equals("NAN"))
						{
							boolean isp = subDao.updatevend(sbname, sbvendor, sbassemble);
							if (!isp)
							{
								sb = isp && sb;
								pwout.print("Sorry, Your data is not updated!");
							}
						}
						if (!sbunit.equals("NAN"))
						{
							boolean isp = subDao.updateunit(sbname, sbunit, sbassemble);
							if (!isp)
							{
								sb = isp && sb;
								pwout.print("Sorry, Your data is not updated!");
							}
						}
						if (!sbnote.equals("NAN"))
						{
							boolean isp = subDao.updateNote(sbname, sbnote, sbassemble);
							if (!isp)
							{
								sb = isp && sb;
								pwout.print("Sorry, Your data is not updated!");
							}
						}
						if (sb)
						{
							List<ListReference> ls = subDao.getList();
							request.setAttribute("listData", ls);
							request.setAttribute("show", showSubDao.ShowTable());
							request.setAttribute("lineData", sbname + " is updated succefully!");
							RequestDispatcher reqd = request.getRequestDispatcher("create_bom_component.jsp");
							reqd.include(request, response);
						}
					}
					else
					{
						ExtraFunction.popUp("Please, First check the data is present or not !!!");
					}

				}
			}
			catch (NullPointerException e)
			{

			}
		}

	}
}
