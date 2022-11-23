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
 * Servlet implementation class UpdateCustomerAction
 */
@WebServlet("/UpdateCustomerAction")
public class UpdateCustomerAction extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdateCustomerAction() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("UpdateCustomerAction 동작함!");
		String custid = request.getParameter("custid");
		String pwd = request.getParameter("pwd");
		String name = request.getParameter("name");
		String birth = request.getParameter("birth");
		String email = request.getParameter("email");
		String phone = request.getParameter("phone");
		String gender = request.getParameter("gender");
		int cateid = Integer.parseInt(request.getParameter("cateid"));
		
		System.out.println("custid " +custid);
		System.out.println("pwd " +pwd);
		System.out.println("name "+name);
		System.out.println("birth " +birth);
		System.out.println("email " +email);
		System.out.println("phone " +phone);
		System.out.println("gender " +gender);
		System.out.println("cateid " +cateid);
		
		CustomerDAO customerDAO = CustomerDAO.getInstance();
		CustomerVO c = new CustomerVO();
		c.setCustid(custid);
		c.setPwd(pwd);
		c.setName(name);
		c.setBirth(birth);
		c.setEmail(email);
		c.setPhone(phone);
		c.setGender(gender);
		c.setCateid(cateid);
		
		int re = customerDAO.updateCustomer(c);
		
		response.setContentType("text/plain;charset=utf-8");
		PrintWriter out = response.getWriter();
		out.print(re);
		out.close();
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		doGet(request, response);
	}

}
