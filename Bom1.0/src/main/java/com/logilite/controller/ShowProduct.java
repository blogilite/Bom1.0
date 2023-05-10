package com.logilite.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.logilite.dao.ShowDao;
import com.logilite.dao.SubProDao;

@WebServlet(urlPatterns = { "/ShowProduct" }, name = "ShowProduct")
public class ShowProduct extends HttpServlet
{

	private static final long	serialVersionUID	= 1L;

	static ShowDao				sd;
	static SubProDao			sbp;

	public ShowProduct()
	{
		sd = new ShowDao();
		sbp = new SubProDao();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		ShowProduct.showData(request, response);
	}

	private static void showData(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException
	{
		List<List<String>> data = sd.showList();
		request.setAttribute("list", data);

		RequestDispatcher rsd = request.getRequestDispatcher("Show.jsp");
		rsd.include(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		String btn = request.getParameter("slink");
		if (btn != null && btn.equals("ShowLink"))
		{

			List<String> lsSb = sd.getListSb();
			request.setAttribute("prSList", lsSb);
			RequestDispatcher rsd = request.getRequestDispatcher("ShowLink.jsp");
			rsd.include(request, response);
		}
		if (btn != null && btn.equals("Find"))
		{
			ShowProduct.showLinkData(request, response);
		}

		if (btn != null && btn.equals("Show"))
		{
			ShowProduct.showData(request, response);
		}
	}

	private static void showLinkData(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException
	{
		String pName = request.getParameter("subPro");
		request.setAttribute("name", "Product name: " + pName);
		List<List<String>> data = sd.showLink(pName);
		request.setAttribute("list", data);
		List<String> lsSb = sd.getListSb();
		request.setAttribute("prSList", lsSb);
		RequestDispatcher rsd = request.getRequestDispatcher("ShowLink.jsp");
		rsd.include(request, response);
	}
}
