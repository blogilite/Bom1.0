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

import com.bom.dao.ShowDao;
import com.bom.model.ExtraFunction;

@WebServlet(name = "Show_Bom", urlPatterns = { "/ShowBom" })
public class ShowBom extends HttpServlet
{
	private static final long	serialVersionUID	= 1L;

	ShowDao						swDao;

	public ShowBom()
	{
		swDao = new ShowDao();
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
	{

		PrintWriter pwOut = resp.getWriter();
		List<List<String>> data = swDao.showTable();
		resp.setContentType("text/html");

		if (data != null)
		{

			RequestDispatcher rsq = req.getRequestDispatcher("show_bom.jsp");
			req.setAttribute("table", data);
			rsq.include(req, resp);

		}
		else
		{

			pwOut.print(ExtraFunction.popUp(
					"Your data is Not present....!<br> Please, Add data... <br>&nbsp;&nbsp; then come heare...."));

			pwOut.flush();
		}

	}

}
