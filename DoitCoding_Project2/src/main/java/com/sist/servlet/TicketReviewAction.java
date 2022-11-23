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
import com.sist.dao.ReviewDAO;
import com.sist.vo.ReviewVO;

/**
 * Servlet implementation class TicketReview
 */
@WebServlet("/TicketReview")
public class TicketReviewAction extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public TicketReviewAction() {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ReviewDAO dao = ReviewDAO.getInstance();
		ArrayList<ReviewVO> r = dao.findByTicketid(Integer.parseInt(request.getParameter("ticketid")), Integer.parseInt(request.getParameter("re")));
		response.setContentType("application/json;charset=utf-8");
		Gson gson = new Gson();
		PrintWriter out = response.getWriter();
		String str = gson.toJson(r);
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