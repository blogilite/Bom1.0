package com.bom.controller;

import com.bom.dao.BomDao;
import com.bom.dao.ShowDao;
import com.bom.model.Bom_Data;
import com.bom.model.ExtraFunction;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/*
 * In both pages one bug whenever Refresh page that time privious work 2nd time
 * or multiple time whenever we refresh
 */

@WebServlet(urlPatterns = { "/CreateBom" }, name = "Create_Bom")
public class CreateBom extends HttpServlet
{

	private static final long	serialVersionUID	= 1L;

	private BomDao				createDao;
	Bom_Data					bom					= new Bom_Data();
	ShowDao						shDao;

	public CreateBom()
	{
		createDao = new BomDao();
		shDao = new ShowDao();

	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
	{
		try
		{
			req.setAttribute("show", shDao.showTable());
			RequestDispatcher reqd = req.getRequestDispatcher("create_bom_page.jsp");
			reqd.include(req, resp);

		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		String action = request.getServletPath();

		System.out.println(action);
		String btnsub = request.getParameter("submit"); //
		PrintWriter pwout = response.getWriter();
		boolean update_allow = false;

		// Submit click to store the data in Database
		if (btnsub != null && btnsub.equals("Create Bom"))
		{
			String prname = request.getParameter("pro_name");
			String num = request.getParameter("pro_qty");
			int prqty = Integer.parseInt((num != null) ? num : "0");
			String prvendor = request.getParameter("pro_vendor");
			String prnote = request.getParameter("pro_note");

			bom.setProd_name(prname);
			bom.setProd_qty(prqty);
			bom.setProd_vendor(prvendor);
			bom.setProd_note(prnote);

			System.out.println(bom.getProd_name() + ", " + bom.getProd_vendor() + ", " + bom.getProd_note() + ", "
					+ bom.getProd_qty());

			if (bom.getProd_name() != null && bom.getProd_vendor() != null && bom.getProd_note() != null
					&& bom.getProd_qty() != 0)
			{
				boolean suc = createDao.create(bom);

				if (suc)
				{
					response.setContentType("text/html");

					RequestDispatcher reqd = request.getRequestDispatcher("create_bom_page.jsp");
					request.setAttribute("lineData", "Succefully Add data");
					request.setAttribute("show", shDao.showTable());
					reqd.include(request, response);
				}
				else
				{
					ExtraFunction.popUp("Your data is alreaddy added or some data is missing.");
				}
			}
			else
			{
				request.setAttribute("lineData", "Please, fill All the details.");
				request.setAttribute("show", shDao.showTable());
				RequestDispatcher reqd = request.getRequestDispatcher("create_bom_page.jsp");
				reqd.include(request, response);
			}

		}

		// To delete data from database using delete button
		String btndel = request.getParameter("delete");
		if (btndel != null && btndel.equals("DeleteBom"))
		{
			try
			{
				String name = request.getParameter("proname");
				response.setContentType("text/html");
				boolean isPresent = createDao.check(name);
				if (isPresent)
				{
					createDao.delete(name);
					RequestDispatcher reqd = request.getRequestDispatcher("create_bom_page.jsp");
					request.setAttribute("lineData", name + "data is Deleted Succefully");
					request.setAttribute("show", shDao.showTable());
					reqd.include(request, response);
				}
				else
				{
					RequestDispatcher reqd = request.getRequestDispatcher("create_bom_page.jsp");
					request.setAttribute("lineData", name + "data is not found!!");
					request.setAttribute("show", shDao.showTable());
					reqd.include(request, response);
				}

			}
			catch (NullPointerException e)
			{
				request.setAttribute("lineData", "Please, First fill the data!!!");
				RequestDispatcher reqd = request.getRequestDispatcher("create_bom_page.jsp");
				request.setAttribute("show", shDao.showTable());
				reqd.include(request, response);

			}
		}

		String btnup = request.getParameter("upd");
		if (btnup != null && btnup.equals("updateBom"))
		{
			String name = request.getParameter("proname");
			System.out.println(name);
			request.setAttribute("nam", name);
			RequestDispatcher reqd = request.getRequestDispatcher("create_bom_page.jsp");
			request.setAttribute("show", shDao.showTable());
			reqd.include(request, response);
		}

		String btnupd = request.getParameter("update");
		if (btnupd != null && btnupd.equals("Update Bom"))
		{
			try
			{
				String prname = request.getParameter("pro_name");
				boolean isPresent = createDao.check(prname);
				update_allow = isPresent;
				if (update_allow && prname != "")
				{

					Bom_Data bom = new Bom_Data();

					String num = request.getParameter("pro_qty");
					int prqty = Integer.parseInt((num != "") ? num : "-1");
					String prven = request.getParameter("pro_vendor");
					String prvendor = (prven == "") ? "NAN" : prven;
					String prno = request.getParameter("pro_note");
					String prnote = (prno == "") ? "NAN" : prno;

					bom.setProd_name(prname);
					bom.setProd_qty(prqty);
					bom.setProd_vendor(prvendor);
					bom.setProd_note(prnote);

					// All data are change
					if (prqty > 0 && prvendor != "NAN" && prnote != "NAN")
					{
						boolean isp = createDao.update(bom);
						if (isp)
						{
							RequestDispatcher reqd = request.getRequestDispatcher("create_bom_page.jsp");
							request.setAttribute("show", shDao.showTable());
							reqd.include(request, response);
							pwout.print("Your data is updated!");
						}
						else
						{
							RequestDispatcher reqd = request.getRequestDispatcher("create_bom_page.jsp");
							request.setAttribute("show", shDao.showTable());
							reqd.include(request, response);
							pwout.print("Sorry, Your data is not updated!");
						}
					}
					// only Qty data change
					else if (prqty > 0 && prvendor.equals("NAN") && prnote.equals("NAN"))
					{
						boolean isp = createDao.update1(bom);
						if (isp)
						{
							RequestDispatcher reqd = request.getRequestDispatcher("create_bom_page.jsp");
							request.setAttribute("show", shDao.showTable());
							reqd.include(request, response);
							pwout.print("Your data is updated!");
						}
						else
						{
							RequestDispatcher reqd = request.getRequestDispatcher("create_bom_page.jsp");
							request.setAttribute("show", shDao.showTable());
							reqd.include(request, response);
							pwout.print("Sorry, Your data is not updated!");
						}
					}
					// Only vendor data is change
					else if (prqty <= 0 && !prvendor.equals("NAN") && prnote.equals("NAN"))
					{
						boolean isp = createDao.update2(bom);
						if (isp)
						{
							RequestDispatcher reqd = request.getRequestDispatcher("create_bom_page.jsp");
							request.setAttribute("show", shDao.showTable());
							reqd.include(request, response);
							pwout.print("Your data is updated!");
						}
						else
						{
							RequestDispatcher reqd = request.getRequestDispatcher("create_bom_page.jsp");
							request.setAttribute("show", shDao.showTable());
							reqd.include(request, response);
							pwout.print("Sorry, Your data is not updated!");
						}
					}
					// Only note change
					else if (prqty <= 0 && prvendor.equals("NAN") && !prnote.equals("NAN"))
					{
						boolean isp = createDao.update3(bom);
						if (isp)
						{
							RequestDispatcher reqd = request.getRequestDispatcher("create_bom_page.jsp");
							request.setAttribute("show", shDao.showTable());
							reqd.include(request, response);
							pwout.print("Your data is updated!");
						}
						else
						{
							RequestDispatcher reqd = request.getRequestDispatcher("create_bom_page.jsp");
							request.setAttribute("show", shDao.showTable());
							reqd.include(request, response);
							pwout.print("Sorry, Your data is not updated!");
						}
					}
					// Qty and Note change
					else if (prqty > 0 && prvendor.equals("NAN") && !prnote.equals("NAN"))
					{
						boolean isp = createDao.update4(bom);
						if (isp)
						{
							RequestDispatcher reqd = request.getRequestDispatcher("create_bom_page.jsp");
							request.setAttribute("show", shDao.showTable());
							reqd.include(request, response);
							pwout.print("Your data is updated!");
						}
						else
						{
							RequestDispatcher reqd = request.getRequestDispatcher("create_bom_page.jsp");
							request.setAttribute("show", shDao.showTable());
							reqd.include(request, response);
							pwout.print("Sorry, Your data is not updated!");
						}
					}
					// Qty and Vendor data change
					else if (prqty > 0 && !prvendor.equals("NAN") && prnote.equals("NAN"))
					{
						boolean isp = createDao.update5(bom);
						if (isp)
						{
							RequestDispatcher reqd = request.getRequestDispatcher("create_bom_page.jsp");
							request.setAttribute("show", shDao.showTable());
							reqd.include(request, response);
							pwout.print("Your data is updated!");
						}
						else
						{
							RequestDispatcher reqd = request.getRequestDispatcher("create_bom_page.jsp");
							request.setAttribute("show", shDao.showTable());
							reqd.include(request, response);
							pwout.print("Sorry, Your data is not updated!");
						}
					}
					// Vendor and Note change
					else if (prqty <= 0 && !prvendor.equals("NAN") && !prnote.equals("NAN"))
					{
						boolean isp = createDao.update6(bom);
						if (isp)
						{
							RequestDispatcher reqd = request.getRequestDispatcher("create_bom_page.jsp");
							request.setAttribute("show", shDao.showTable());
							reqd.include(request, response);
							pwout.print("Your data is updated!");
						}
						else
						{
							RequestDispatcher reqd = request.getRequestDispatcher("create_bom_page.jsp");
							request.setAttribute("show", shDao.showTable());
							reqd.include(request, response);
							pwout.print("Sorry, Your data is not updated!");
						}
					}
					else
					{
						RequestDispatcher reqd = request.getRequestDispatcher("create_bom_page.jsp");
						request.setAttribute("show", shDao.showTable());
						reqd.include(request, response);
						ExtraFunction.popUp("Please, First check the data is present or not !!!");
					}
				}
				else
				{
					ExtraFunction.popUp("Please, First check the data is present or not !!!");
					RequestDispatcher reqd = request.getRequestDispatcher("create_bom_page.jsp");
					request.setAttribute("show", shDao.showTable());
					reqd.include(request, response);

				}
			}
			catch (NullPointerException e)
			{
				ExtraFunction.popUp("Please, First check the data is present or not !!!");
				RequestDispatcher reqd = request.getRequestDispatcher("create_bom_page.jsp");
				request.setAttribute("show", shDao.showTable());
				reqd.include(request, response);

			}

		}

	}
}
