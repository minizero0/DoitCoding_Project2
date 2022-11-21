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

import com.sist.vo.SeatVO;

public class SeatDAO {
	//싱글톤 방식
	private static SeatDAO seatDAO;
	private SeatDAO() {	}
	public static SeatDAO getInstance() {
		if(seatDAO == null) {
			seatDAO = new SeatDAO();
		}
		return seatDAO;
	}
	
	
	//좌석 추가
	public int insertSeat(SeatVO s) {
		int re = -1;
		String sql = "insert into seat(seatid,placeid,ticketid,seatname,check_seat) values(seq_seat.nextval,?,seq_ticket.nextval,?,?)";
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			Context context = new InitialContext();
			DataSource ds = (DataSource)context.lookup("java:/comp/env/mydb");
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, s.getPlaceid());
			pstmt.setString(2, s.getSeatname());
			pstmt.setString(3, s.getCheck_seat());
			re = pstmt.executeUpdate();
		} catch (Exception e) {
			System.out.println("예외발생:"+e.getMessage());
		} finally {
			if(pstmt != null) {try {pstmt.close();} catch (SQLException e) {e.printStackTrace();}}
			if(conn != null) {try {conn.close();} catch (SQLException e) {e.printStackTrace();}}
		}
		return re;
	}
	
		
	//좌석 정보 수정
	public int updateSeat(SeatVO s) {
		int re = -1;
		String sql = "update seat set placeid=?,ticketid=?,seatname=?,check_seat=? where seatid=?;";
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			Context context = new InitialContext();
			DataSource ds = (DataSource)context.lookup("java:/comp/env/mydb");
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, s.getPlaceid());
			pstmt.setInt(2, s.getTicketid());
			pstmt.setString(3, s.getSeatname());
			pstmt.setString(4, s.getCheck_seat());
			pstmt.setInt(5, s.getSeatid());
			re = pstmt.executeUpdate();
		} catch (Exception e) {
			System.out.println("예외발생:"+e.getMessage());
		} finally {
			if(pstmt != null) {try {pstmt.close();} catch (SQLException e) {e.printStackTrace();}}
			if(conn != null) {try {conn.close();} catch (SQLException e) {e.printStackTrace();}}
		}
		return re;
	}
	
	
	//좌석 삭제
	public int deleteSeat(int seatid) {
		int re = -1;
		String sql = "delete seat where seatid=?";
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			Context context = new InitialContext();
			DataSource ds = (DataSource)context.lookup("java:/comp/env/mydb");
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, seatid);
			re = pstmt.executeUpdate();
		} catch (Exception e) {
			System.out.println("예외발생:"+e.getMessage());
		} finally {
			if(pstmt != null) {try {pstmt.close();} catch (SQLException e) {e.printStackTrace();}}
			if(conn != null) {try {conn.close();} catch (SQLException e) {e.printStackTrace();}}
		}
		return re;
	}
	
	
	//전체 좌석 목록 출력
	public ArrayList<SeatVO> listSeat(){
		ArrayList<SeatVO> list = new ArrayList<SeatVO>();
		String sql = "select * from seat";
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
				SeatVO s = new SeatVO();
				s.setSeatid(rs.getInt("seatid"));
				s.setPlaceid(rs.getString("placeid"));
				s.setTicketid(rs.getInt("ticketid"));
				s.setSeatname(rs.getString("seatname"));
				s.setCheck_seat(rs.getString("check_seat"));
				list.add(s);
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
	
	
	//특정 좌석 목록 출력
	public SeatVO findById(int seatid) {
		SeatVO s = null;
		String sql = "select * from seat where seatid=?";
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			Context context = new InitialContext();
			DataSource ds = (DataSource)context.lookup("java:/comp/env/mydb");
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, seatid);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				s = new SeatVO();
				s.setSeatid(rs.getInt("seatid"));
				s.setPlaceid(rs.getString("placeid"));
				s.setTicketid(rs.getInt("ticketid"));
				s.setSeatname(rs.getString("seatname"));
				s.setCheck_seat(rs.getString("check_seat"));
			}
		} catch (Exception e) {
			System.out.println("예외발생:"+e.getMessage());
		} finally {
			if(rs != null) {try {rs.close();} catch (SQLException e) {e.printStackTrace();}}
			if(pstmt != null) {try {pstmt.close();} catch (SQLException e) {e.printStackTrace();}}
			if(conn != null) {try {conn.close();} catch (SQLException e) {e.printStackTrace();}}
		}
		return s;
	}
}