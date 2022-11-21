package com.sist.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sist.dao.CustomerDAO;
import com.sist.vo.CustomerVO;

/**
 * Servlet implementation class ConfirmCustomerId
 */
@WebServlet("/ConfirmCustomerId")
public class ConfirmCustomerId extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ConfirmCustomerId() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String custid = request.getParameter("custid");
		int confirm_custid;
		
		CustomerDAO customerdao = CustomerDAO.getInstance();
		
		confirm_custid = customerdao.confirmCustomerID(custid);
		
		response.setContentType("text/plain");
		PrintWriter out = response.getWriter();
		out.print(confirm_custid);
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
