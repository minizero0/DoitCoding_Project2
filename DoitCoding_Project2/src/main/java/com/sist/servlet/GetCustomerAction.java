package com.sist.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.sist.dao.CustomerDAO;
import com.sist.vo.CustomerVO;

/**
 * Servlet implementation class GetCustomerAction
 */
@WebServlet("/GetCustomerAction")
public class GetCustomerAction extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GetCustomerAction() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		CustomerDAO customerDAO = CustomerDAO.getInstance();		
		String custid = request.getParameter("custid");
		CustomerVO c = customerDAO.findById(custid);
		response.setContentType("application/json;charset=utf-8");
		Gson gson = new Gson();
		String str = gson.toJson(c);
		PrintWriter out = response.getWriter();
		out.print(str);
		out.close();
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
