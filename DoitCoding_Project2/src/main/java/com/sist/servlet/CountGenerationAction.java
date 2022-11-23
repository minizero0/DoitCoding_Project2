package com.sist.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.sist.dao.BookDAO;

/**
 * Servlet implementation class CountGeneration
 */
@WebServlet("/CountGeneration")
public class CountGenerationAction extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CountGenerationAction() {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		BookDAO dao = BookDAO.getInstance();
		int ticketid = Integer.parseInt(request.getParameter("ticketid"));
		ArrayList<HashMap<String, Integer>> list = new ArrayList<HashMap<String, Integer>>();
		list = dao.countGeneration(ticketid);
		response.setContentType("application/json;charset=utf-8");
		Gson gson = new Gson();
		PrintWriter out = response.getWriter();
		String arr = gson.toJson(list);
		out.print(arr);
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