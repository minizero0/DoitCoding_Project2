package com.sist.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sist.dao.BookDAO;
import com.sist.dao.SeatDAO;
import com.sist.vo.BookVO;

/**
 * Servlet implementation class ListBook
 */
@WebServlet("/RegistBook")
public class RegistBook extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RegistBook() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		BookDAO bd = BookDAO.getInstance();
		
		int bookid = bd.getNextBookid();
//		int ticketid = Integer.parseInt(request.getParameter("ticketid"));
//		String custid = request.getParameter("custid");
//		int seatid = Integer.parseInt(request.getParameter("seatid"));
		
		int ticketid = 19;
		int seatid = 2;
		String custid = "ym1234";
		
		BookVO b = new BookVO();
		b.setBookid(bookid);
		b.setCustid(custid);
		b.setSeatid(seatid);
		b.setTicketid(seatid);
		int re = bd.registBook(b);
		
		if(re == 1) {
			SeatDAO dao = SeatDAO.getInstance();
			re += dao.registSeat(seatid);
		}
		
		PrintWriter out = response.getWriter();
		out.print(re);
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