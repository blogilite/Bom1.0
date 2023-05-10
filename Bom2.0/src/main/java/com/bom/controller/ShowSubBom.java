package com.bom.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bom.dao.ShowSubDao;

@WebServlet(name = "ShowSubBomData", urlPatterns = { "/ShowSubBom" })
public class ShowSubBom extends HttpServlet
{
	private static final long	serialVersionUID	= 1L;

	ShowSubDao					ssDao;

	public ShowSubBom()
	{
		ssDao = new ShowSubDao();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{

		List<List<String>> data = ssDao.ShowTable();
		response.setContentType("text/html");

		if (data != null)
		{
			RequestDispatcher reqd = request.getRequestDispatcher("showSubBom.jsp");
			request.setAttribute("table",data);
			reqd.include(request, response);
		}
		else
		{
			RequestDispatcher reqd = request.getRequestDispatcher("showSubBom.jsp");
			request.setAttribute("null", "table");
			reqd.include(request, response);
		}
	}
}
