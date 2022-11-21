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
import com.sist.vo.DrawVO;

public class DrawDAO {
	//싱글톤 방식
	private static DrawDAO drawDAO;
	private DrawDAO() {	}
	public static DrawDAO getInstance() {
		if(drawDAO == null) {
			drawDAO = new DrawDAO();
		}
		return drawDAO;
	}
	
	
	//드로우 추가
	public int insertDraw(DrawVO d) {
		int re = -1;
		String sql = "insert into draw(drawid,custid,ticketid,seatid) values(?,?,seq_ticket.nextval,seq_seat.nextval)";
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			Context context = new InitialContext();
			DataSource ds = (DataSource)context.lookup("java:/comp/env/mydb");
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, d.getDrawid());
			pstmt.setString(2, d.getCustid());
			re = pstmt.executeUpdate();
		} catch (Exception e) {
			System.out.println("예외발생:"+e.getMessage());
		} finally {
			if(pstmt != null) {try {pstmt.close();} catch (SQLException e) {e.printStackTrace();}}
			if(conn != null) {try {conn.close();} catch (SQLException e) {e.printStackTrace();}}
		}
		return re;
	}
	
		
	//드로우 내역 수정
	public int updateDraw(DrawVO d) {
		int re = -1;
		String sql = "update draw set custid=?,ticketid=?,seatid=? where drawid=?";
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			Context context = new InitialContext();
			DataSource ds = (DataSource)context.lookup("java:/comp/env/mydb");
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, d.getCustid());
			pstmt.setInt(2, d.getTicketid());
			pstmt.setInt(3, d.getSeatid());
			pstmt.setInt(4, d.getDrawid());
			re = pstmt.executeUpdate();
		} catch (Exception e) {
			System.out.println("예외발생:"+e.getMessage());
		} finally {
			if(pstmt != null) {try {pstmt.close();} catch (SQLException e) {e.printStackTrace();}}
			if(conn != null) {try {conn.close();} catch (SQLException e) {e.printStackTrace();}}
		}
		return re;
	}
	
	
	//드로우 취소
	public int deleteDraw(int drawid) {
		int re = -1;
		String sql = "delete draw where drawid=?";
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			Context context = new InitialContext();
			DataSource ds = (DataSource)context.lookup("java:/comp/env/mydb");
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, drawid);
			re = pstmt.executeUpdate();
		} catch (Exception e) {
			System.out.println("예외발생:"+e.getMessage());
		} finally {
			if(pstmt != null) {try {pstmt.close();} catch (SQLException e) {e.printStackTrace();}}
			if(conn != null) {try {conn.close();} catch (SQLException e) {e.printStackTrace();}}
		}
		return re;
	}
	
	
	//전체 드로우 목록 출력
	public ArrayList<DrawVO> listDraw(){
		ArrayList<DrawVO> list = new ArrayList<DrawVO>();
		String sql = "select * from draw";
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
				DrawVO d = new DrawVO();
				d.setDrawid(rs.getInt("drawid"));
				d.setCustid(rs.getString("custid"));
				d.setTicketid(rs.getInt("ticketid"));
				d.setSeatid(rs.getInt("seatid"));
				list.add(d);
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
	
	
	//특정 드로우 내역 출력
	public DrawVO findById(int drawid) {
		DrawVO d = null;
		String sql = "select * from draw where drawid=?";
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			Context context = new InitialContext();
			DataSource ds = (DataSource)context.lookup("java:/comp/env/mydb");
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, drawid);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				d = new DrawVO();
				d.setDrawid(rs.getInt("drawid"));
				d.setCustid(rs.getString("custid"));
				d.setTicketid(rs.getInt("ticketid"));
				d.setSeatid(rs.getInt("seatid"));
			}
		} catch (Exception e) {
			System.out.println("예외발생:"+e.getMessage());
		} finally {
			if(rs != null) {try {rs.close();} catch (SQLException e) {e.printStackTrace();}}
			if(pstmt != null) {try {pstmt.close();} catch (SQLException e) {e.printStackTrace();}}
			if(conn != null) {try {conn.close();} catch (SQLException e) {e.printStackTrace();}}
		}
		return d;
	}
	
	//사용자 드로우 내역 출력
		public ArrayList<DrawVO> findByCustid(int custid) {
			ArrayList<DrawVO> list = new ArrayList<>();
			String sql = "select * from draw where custid=?";
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
					DrawVO b = new DrawVO();
					b.setDrawid(rs.getInt("drawid"));
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
