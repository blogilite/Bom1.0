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

@WebServlet(urlPatterns = { "/ShowSubBomRefer" }, name = "Show_Sub_Bom_Refer")
public class ShowSubBomRefer extends HttpServlet
{
	private static final long	serialVersionUID	= 1L;

	ShowSubDao					ssDao;

	public ShowSubBomRefer()
	{
		ssDao = new ShowSubDao();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		List<List<String>> data = ssDao.ShowReferTable();
		response.setContentType("text/html");

		if (data != null)
		{
			RequestDispatcher reqd = request.getRequestDispatcher("showSubBomRef.jsp");
			request.setAttribute("table", data);
			reqd.include(request, response);
		}
		else
		{
			RequestDispatcher reqd = request.getRequestDispatcher("showSubBomRef.jsp");	
			request.setAttribute("data", "table");
			reqd.include(request, response);
		}
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
	{
		 
		String na=req.getParameter("part-name");
		String name = na!=""?na:"nun";
		String btn = req.getParameter("find");
		List<List<String>> data = ssDao.ShowReferTableFil(name);

		if (btn != null && btn.equals("Find"))
		{
			RequestDispatcher reqd = req.getRequestDispatcher("showSubBomRef.jsp");
			req.setAttribute("table", data);
			reqd.include(req, resp);
		}
		else
		{
			RequestDispatcher reqd = req.getRequestDispatcher("showSubBomRef.jsp");
			req.setAttribute("table", data);
			reqd.include(req, resp);
		}
	}

}
