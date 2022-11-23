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
import com.sist.dao.SeatDAO;
import com.sist.vo.SeatVO;


/**
 * Servlet implementation class InsertBook
 */
@WebServlet("/ListSeat")
public class ListSeat extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ListSeat() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int ticketid = Integer.parseInt(request.getParameter("ticketid"));
		response.setContentType("application/json;charset=utf-8");
		SeatDAO dao = SeatDAO.getInstance();
		ArrayList<SeatVO> list = dao.listSeatByTicketid(ticketid);
		Gson json = new Gson();
		String str = json.toJson(list);
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


