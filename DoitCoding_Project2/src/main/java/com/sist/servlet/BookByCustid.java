package com.sist.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.sist.dao.TicketDAO;
import com.sist.vo.BookByCustidVO;

/**
 * Servlet implementation class BookByCustid
 */
@WebServlet("/BookByCustid")
public class BookByCustid extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BookByCustid() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		TicketDAO ticketdao = TicketDAO.getInstance();
		String custid = request.getParameter("custid");
		
		ArrayList<BookByCustidVO> list = ticketdao.selectTicketByCustid(custid);
		
		Gson gson = new Gson();
		String str = gson.toJson(list);
		
		response.setContentType("application/json;charset=utf-8");
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
