package com.logilite.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.logilite.dao.AddDao;
import com.logilite.model.Product;

@WebServlet(urlPatterns = { "/AddProduct" }, name = "Product", displayName = "Nothing")
public class AddProject extends HttpServlet
{
	private static final long	serialVersionUID	= 1L;

	static AddDao				ad					= new AddDao();

	public AddProject()
	{
		ad = new AddDao();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		AddProject.showData(request, response);
	}

	// To show Data using request dispatcher
	private static void showData(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException
	{
		RequestDispatcher reqd = request.getRequestDispatcher("product.jsp");
		List<List<String>> list = ad.showList();
		request.setAttribute("dis_upd", "disabled");
		request.setAttribute("table", list);
		reqd.forward(request, response);
	}

	// To fetch Data from Form
	private static Product fetchData(HttpServletRequest request)
	{
		Product pr = new Product();
		String prsk = request.getParameter("sku");
		int pSku = Integer.parseInt((prsk != "") ? prsk : "0");
		String pName = request.getParameter("proName");
		String pBatch = request.getParameter("proBatch");
		String pCat = request.getParameter("proCat");
		String prq = request.getParameter("proQty");
		int pQty = Integer.parseInt((prq != "") ? prq : "0");
		String prp = request.getParameter("proPrice");
		int pPrice = Integer.parseInt((prp != "") ? prp : "0");
		String note = request.getParameter("proNot");
		String prf = request.getParameter("proFinal");
		boolean pFinal = Boolean.parseBoolean((prf != null) ? "TRUE" : "FALSE");

		request.setAttribute("dis_upd", "disabled");
		pr.setSku(pSku);
		pr.setName(pName);
		pr.setQty(pQty);
		pr.setBatchCode(pBatch);
		pr.setCatagory(pCat);
		pr.setPrice(pPrice);
		pr.setNote(note);
		pr.setSubPro(pFinal);

		return pr;
	}

	private static void setData(HttpServletRequest request, Product pr)
	{
		int sku = pr.getSku();
		request.setAttribute("sku", sku);
		request.setAttribute("name", pr.getName() != null ? pr.getName() : null);
		request.setAttribute("cat", pr.getCatagory() != null ? pr.getCatagory() : null);
		request.setAttribute("price", pr.getPrice() > 0 ? pr.getPrice() : null);
		request.setAttribute("note", pr.getNote() != null ? pr.getNote() : null);
		request.setAttribute("batch", pr.getBatchCode() != null ? pr.getBatchCode() : null);
		request.setAttribute("qty", pr.getQty() > 0 ? pr.getQty() : null);
		request.setAttribute("sub", (pr.isSubPro() ? "checked" : "OFF"));
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		String btn = request.getParameter("putData");
		// To add data
		if (btn != null && btn.equals("ADD"))
		{
			Product pr = AddProject.fetchData(request);

			if (!pr.getName().equals("NAN") && pr.getPrice() > 0 && !pr.getBatchCode().equals("NAN") && pr.getQty() > 0)
			{
				if (ad.checkData(pr.getName(), pr.getBatchCode()))
				{
					if (ad.addData(pr))
					{
						request.setAttribute("green", "Product add Succefully!");
						AddProject.showData(request, response);
					}
					else
					{

						AddProject.setData(request, pr);

						request.setAttribute("red", "Sorry, Your Product is not added!");
						AddProject.showData(request, response);
					}
				}
				else
				{
					request.setAttribute("red", "Name or Batch Code is repeted!!!");
					AddProject.setData(request, pr);
					AddProject.showData(request, response);
				}
			}
			else
			{
				request.setAttribute("red", "Sorry, First fill all the details of Product!");
				AddProject.setData(request, pr);
				AddProject.showData(request, response);
			}
		}
		// TO verify data is present or not
		/*
		 * else if (btn != null && btn.equals("Verify")) { Product pr =
		 * AddProject.fetchData(request); if (pr.getName() != null &&
		 * pr.getBatchCode() != null) { if (ad.checkData(pr.getName(),
		 * pr.getBatchCode())) { request.setAttribute("green",
		 * "Now, you can add or update Product."); AddProject.setData(request,
		 * pr); } else { request.setAttribute("red",
		 * "Sorry, Already Product is present the same Name or Batch Code.");
		 * AddProject.setData(request, pr); } } else {
		 * request.setAttribute("red", "Sorry, Fill data First.");
		 * AddProject.setData(request, pr); request.setAttribute("dis_upd",
		 * "null"); AddProject.showData(request, response); } }
		 */
		// To delete Data
		else if (btn != null && btn.equals("Delete"))
		{
			String pName = request.getParameter("proName");

			if (ad.deleteList(pName))
			{

				request.setAttribute("green", pName + " is deleted Succefully...");
				AddProject.showData(request, response);
			}
			else
			{
				request.setAttribute("red", pName + " Data is not deleted!!!");
				AddProject.showData(request, response);
			}

		}
		// Using update button fetch Data in form
		else if (btn != null && btn.equals("Update"))
		{
			String name = request.getParameter("proName");
			Product pr = ad.fetch(name);

			AddProject.setData(request, pr);
			AddProject.showUpdateData(request, response);
		}
		// Update the data
		else if (btn != null && btn.equals("Update Data"))
		{

			Product pr = AddProject.fetchData(request);

			if (!pr.getName().equals("NAN") && pr.getPrice() > 0 && !pr.getBatchCode().equals("NAN") && pr.getQty() > 0)
			{
				if (ad.updateData(pr.getSku(), pr.getName(), pr.getBatchCode(), pr.getCatagory(), pr.getQty(),
						pr.getPrice(), pr.getNote(), pr.isSubPro()))
				{
					request.setAttribute("green", pr.getName() + " is updated Succefully...");
					AddProject.showData(request, response);
				}
				else
				{
					request.setAttribute("red", "Opps, " + pr.getName() + " is not updated...	 Somthing missing");
					AddProject.setData(request, pr);
					AddProject.showUpdateData(request, response);
				}
			}
			else
			{
				request.setAttribute("red", "Sorry, First fill all the details of Product!");
				AddProject.setData(request, pr);
				AddProject.showUpdateData(request, response);
			}
		}
	}

	private static void showUpdateData(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException
	{
		RequestDispatcher reqd = request.getRequestDispatcher("product.jsp");
		List<List<String>> list = ad.showList();
		request.setAttribute("table", list);
		request.setAttribute("dis_add", "disabled");
		request.setAttribute("dis_upd", "null");
		reqd.forward(request, response);
	}

}
