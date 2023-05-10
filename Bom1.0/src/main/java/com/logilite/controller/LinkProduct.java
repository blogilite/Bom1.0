package com.logilite.controller;

import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.logilite.dao.AddDao;
import com.logilite.dao.SubProDao;
import com.logilite.model.Product;

@WebServlet(urlPatterns = { "/LinkProduct" })
public class LinkProduct extends HttpServlet
{
	private static final long	serialVersionUID	= 1L;

	static SubProDao			sbp;

	public LinkProduct()
	{
		sbp = new SubProDao();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		LinkProduct.showData(request, response);
	}

	// to Show all the data using Req.dispatcher
	private static void showData(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException
	{
		RequestDispatcher reqd = request.getRequestDispatcher("link.jsp");
		List<List<String>> lsPr = sbp.getListPr();
		request.setAttribute("prNList", lsPr);
		request.setAttribute("list", sbp.showList());
		reqd.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		String btn = request.getParameter("addPro");
		if (btn != null && btn.equals("Link"))
		{
			String name = request.getParameter("proName"); // Product Name
			String sbname = request.getParameter("subPro"); // Sub Product Name

			String qt = request.getParameter("qty"); // QTY
			int qty = Integer.parseInt((qt != "") ? qt : "0");
			if (qty < 1)
			{
				request.setAttribute("message", "Sorry,Enter valide quantity!!!");
				LinkProduct.showData(request, response);
				return;
			}

			// Check the data is link or not
			boolean ch = sbp.check(sbname);
			// If data is not link
			if (ch)
			{
				int sku = sbp.getSku(sbname);
				boolean add = sbp.addForeign(sku, sbname, name, qty);
				if (add)
				{
					request.setAttribute("message", sbname + " link Succefully...");
					LinkProduct.showData(request, response);
				}
				else
				{
					request.setAttribute("message", sbname + " is not link!!!");
					LinkProduct.showData(request, response);
				}
			}
			// If data is already link that time
			else
			{
				int sku = sbp.getSku(sbname);
				boolean add = sbp.update(sku, sbname, name, qty);
				if (add)
				{
					request.setAttribute("message", sbname + " Update Succefully...");
					LinkProduct.showData(request, response);
				}
			}

		}
		if (btn != null && btn.equals("Update"))
		{
			String name = request.getParameter("proName"); // Product Name
			String sbname = request.getParameter("subPro"); // Sub Product Name

			String qt = request.getParameter("qty"); // QTY
			int qty = Integer.parseInt((qt != "") ? qt : "0");
			if (qty < 1)
			{
				request.setAttribute("message", "Sorry,Enter valide quantity!!!");
				LinkProduct.showData(request, response);
				return;
			}

			int sku = sbp.getSku(sbname);
			boolean add = sbp.update(sku, sbname, name, qty);
			if (add)
			{
				request.setAttribute("message", sbname + " Update Succefully...");
				LinkProduct.showData(request, response);
			}
		}
		if (btn != null && btn.equals("Delete"))
		{
			String name = request.getParameter("proName");
			if (sbp.delete(name))
			{
				request.setAttribute("message", name + " is Unlink!!!");
				LinkProduct.showData(request, response);
			}
			else
			{
				request.setAttribute("message", name + " is not Unlink!!!");
				LinkProduct.showData(request, response);
			}
		}
	}
}