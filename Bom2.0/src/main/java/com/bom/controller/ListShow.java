package com.bom.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bom.dao.BomDao;
import com.bom.dao.SubBomDao;
import com.bom.model.ListReference;

@WebServlet(urlPatterns = { "/ListShow" }, name = "List-Show")
public class ListShow extends HttpServlet
{
	private static final long	serialVersionUID	= 1L;

	private SubBomDao			cBom;

	public ListShow()
	{
		cBom = new SubBomDao();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		try
		{
			List<ListReference> ls = cBom.getList();
			request.setAttribute("listData", ls);
			
			RequestDispatcher rsd=request.getRequestDispatcher("create_bom_component.jsp");
			rsd.forward(request, response);
		}
		catch (Exception e)
		{
			request.setAttribute("listData", null);
			
			RequestDispatcher rsd=request.getRequestDispatcher("create_bom_component.jsp");
			rsd.forward(request, response);
		}

	}

}
