package com.sist.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import com.sist.vo.BookVO;

public class BookDAO {
	//싱글톤 방식
	private static BookDAO bookDAO;
	private BookDAO() {	}
	public static BookDAO getInstance() {
		if(bookDAO == null) {
			bookDAO = new BookDAO();
		}
		return bookDAO;
	}
	
	
	//예매 추가
	public int insertBook(BookVO b) {
		int re = -1;
		String sql = "insert into book(bookid,custid,ticketid,seatid) values(?,?,seq_ticket.nextval,seq_seat.nextval)";
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			Context context = new InitialContext();
			DataSource ds = (DataSource)context.lookup("java:/comp/env/mydb");
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, b.getBookid());
			pstmt.setString(2, b.getCustid());
			re = pstmt.executeUpdate();
		} catch (Exception e) {
			System.out.println("예외발생:"+e.getMessage());
		} finally {
			if(pstmt != null) {try {pstmt.close();} catch (SQLException e) {e.printStackTrace();}}
			if(conn != null) {try {conn.close();} catch (SQLException e) {e.printStackTrace();}}
		}
		return re;
	}
	
		
	//예매 내역 수정
	public int updateBook(BookVO b) {
		int re = -1;
		String sql = "update book set custid=?,ticketid=?,seatid=? where bookid=?";
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			Context context = new InitialContext();
			DataSource ds = (DataSource)context.lookup("java:/comp/env/mydb");
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, b.getCustid());
			pstmt.setInt(2, b.getTicketid());
			pstmt.setInt(3, b.getSeatid());
			pstmt.setInt(4, b.getBookid());
			re = pstmt.executeUpdate();
		} catch (Exception e) {
			System.out.println("예외발생:"+e.getMessage());
		} finally {
			if(pstmt != null) {try {pstmt.close();} catch (SQLException e) {e.printStackTrace();}}
			if(conn != null) {try {conn.close();} catch (SQLException e) {e.printStackTrace();}}
		}
		return re;
	}
	
	
	//예매 취소
	public int deleteBook(int bookid) {
		int re = -1;
		String sql = "delete book where bookid=?";
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			Context context = new InitialContext();
			DataSource ds = (DataSource)context.lookup("java:/comp/env/mydb");
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, bookid);
			re = pstmt.executeUpdate();
		} catch (Exception e) {
			System.out.println("예외발생:"+e.getMessage());
		} finally {
			if(pstmt != null) {try {pstmt.close();} catch (SQLException e) {e.printStackTrace();}}
			if(conn != null) {try {conn.close();} catch (SQLException e) {e.printStackTrace();}}
		}
		return re;
	}
	
	
	//전체 예매 목록 출력
	public ArrayList<BookVO> listBook(){
		ArrayList<BookVO> list = new ArrayList<BookVO>();
		String sql = "select * from book";
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		try {
			Context context = new InitialContext();
			DataSource ds = (DataSource)context.lookup("java:/comp/env/mydb");
			conn = ds.getConnection();
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			while(rs.next()) {
				BookVO b = new BookVO();
				b.setBookid(rs.getInt("bookid"));
				b.setCustid(rs.getString("custid"));
				b.setTicketid(rs.getInt("ticketid"));
				b.setSeatid(rs.getInt("seatid"));
				list.add(b);
			}
		} catch (Exception e) {
			System.out.println("예외발생:"+e.getMessage());
		} finally {
			if(rs != null) {try {rs.close();} catch (SQLException e) {e.printStackTrace();}}
			if(stmt != null) {try {stmt.close();} catch (SQLException e) {e.printStackTrace();}}
			if(conn != null) {try {conn.close();} catch (SQLException e) {e.printStackTrace();}}
		}
		return list;
	}
	
	
	//특정 예매 내역 출력
	public BookVO findById(int bookid) {
		BookVO b = null;
		String sql = "select * from book where bookid=?";
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			Context context = new InitialContext();
			DataSource ds = (DataSource)context.lookup("java:/comp/env/mydb");
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, bookid);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				b = new BookVO();
				b.setBookid(rs.getInt("bookid"));
				b.setCustid(rs.getString("custid"));
				b.setTicketid(rs.getInt("ticketid"));
				b.setSeatid(rs.getInt("seatid"));
			}
		} catch (Exception e) {
			System.out.println("예외발생:"+e.getMessage());
		} finally {
			if(rs != null) {try {rs.close();} catch (SQLException e) {e.printStackTrace();}}
			if(pstmt != null) {try {pstmt.close();} catch (SQLException e) {e.printStackTrace();}}
			if(conn != null) {try {conn.close();} catch (SQLException e) {e.printStackTrace();}}
		}
		return b;
	}
	//사용자 예매 내역 출력
	public ArrayList<BookVO> findByCustid(int custid) {
		ArrayList<BookVO> list = new ArrayList<>();
		String sql = "select * from book where custid=?";
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			Context context = new InitialContext();
			DataSource ds = (DataSource)context.lookup("java:/comp/env/mydb");
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, custid);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				BookVO b = new BookVO();
				b.setBookid(rs.getInt("bookid"));
				b.setCustid(rs.getString("custid"));
				b.setTicketid(rs.getInt("ticketid"));
				b.setSeatid(rs.getInt("seatid"));
				list.add(b);
			}
		} catch (Exception e) {
			System.out.println("예외발생:"+e.getMessage());
		} finally {
			if(rs != null) {try {rs.close();} catch (SQLException e) {e.printStackTrace();}}
			if(pstmt != null) {try {pstmt.close();} catch (SQLException e) {e.printStackTrace();}}
			if(conn != null) {try {conn.close();} catch (SQLException e) {e.printStackTrace();}}
		}
		return list;
	}
}
