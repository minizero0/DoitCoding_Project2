package com.sist.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sist.dao.ReviewDAO;
import com.sist.vo.ReviewVO;

/**
 * Servlet implementation class InsertReview
 */
@WebServlet("/InsertReview")
public class InsertReview extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public InsertReview() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=utf-8");
		ReviewDAO dao = ReviewDAO.getInstance();
		ReviewVO rv = new ReviewVO();
		
		int reviewid = Integer.parseInt(request.getParameter("reviewid"));
		String custid = request.getParameter("custid");
		int ticketid = Integer.parseInt(request.getParameter("ticketid"));
		double score = (double)Integer.parseInt(request.getParameter("score"));
		String review_content = request.getParameter("review_content");
		
		rv.setReviewid(reviewid);
		rv.setCustid(custid);
		rv.setTicketid(ticketid);
		rv.setScore(score);
		rv.setReview_content(review_content);
		
		int re = dao.insertReview(rv);
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
