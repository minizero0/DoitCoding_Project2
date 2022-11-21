package com.sist.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import com.sist.vo.TicketVO;

public class TicketDAO {
	//싱글톤 방식
	private static TicketDAO ticketDAO;
	private TicketDAO() {	}
	public static TicketDAO getInstance() {
		if(ticketDAO == null) {
			ticketDAO = new TicketDAO();
		}
		return ticketDAO;
	}
	
	
	//티켓 추가
	public int insertTicket(TicketVO t) {
		int re = -1;
		String sql = "insert into ticket(ticketid,cateid,placeid,ticket_name,price,ticket_date,min_age,runtime,cast,content,img_fname,vid_url,loc,lat,lng)"
				+ " values(seq_ticketid.nextval,seq_cateid.nextval,?,?,?,?,?,?,?,?,?,?,?,?,?)";
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			Context context = new InitialContext();
			DataSource ds = (DataSource)context.lookup("java:/comp/env/mydb");
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, t.getPlaceid());
			pstmt.setString(2, t.getTicket_name());
			pstmt.setInt(3, t.getPrice());
			pstmt.setString(4, t.getTicket_date());
			pstmt.setInt(5, t.getMin_age());
			pstmt.setInt(6, t.getRuntime());
			pstmt.setString(7, t.getCast());
			pstmt.setString(8, t.getContent());
			pstmt.setString(9, t.getImg_fname());
			pstmt.setString(10, t.getVid_url());
			pstmt.setString(11, t.getLoc());
			pstmt.setDouble(12, t.getLat());
			pstmt.setDouble(13, t.getLng());
			re = pstmt.executeUpdate();
		} catch (Exception e) {
			System.out.println("예외발생:"+e.getMessage());
			
		} finally {
			if(pstmt != null) {try {pstmt.close();} catch (SQLException e) {e.printStackTrace();}}
			if(conn != null) {try {conn.close();} catch (SQLException e) {e.printStackTrace();}}
		}
		return re;
	}
	
		
	//티켓 정보 수정
	public int updateTicket(TicketVO t) {
		int re = -1;
		String sql = "update ticket set"
				+ " cateid=?,placeid=?,ticket_name=?,price=?,ticket_date=?,min_age=?,runtime=?,cast=?,content=?,img_fname=?,vid_url=?,loc=?,lat=?,lng=?"
				+ " where ticketid=?";
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			Context context = new InitialContext();
			DataSource ds = (DataSource)context.lookup("java:/comp/env/mydb");
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, t.getCateid());
			pstmt.setString(2, t.getPlaceid());
			pstmt.setString(3, t.getTicket_name());
			pstmt.setInt(4, t.getPrice());
			pstmt.setString(5, t.getTicket_date());
			pstmt.setInt(6, t.getMin_age());
			pstmt.setInt(7, t.getRuntime());
			pstmt.setString(8, t.getCast());
			pstmt.setString(9, t.getContent());
			pstmt.setString(10, t.getImg_fname());
			pstmt.setString(11, t.getVid_url());
			pstmt.setString(12, t.getLoc());
			pstmt.setDouble(13, t.getLat());
			pstmt.setDouble(14, t.getLng());
			pstmt.setInt(15, t.getTicketid());
			re = pstmt.executeUpdate();
		} catch (Exception e) {
			System.out.println("예외발생:"+e.getMessage());
		} finally {
			if(pstmt != null) {try {pstmt.close();} catch (SQLException e) {e.printStackTrace();}}
			if(conn != null) {try {conn.close();} catch (SQLException e) {e.printStackTrace();}}
		}
		return re;
	}
	
	
	//티켓 삭제
	public int deleteTicket(int ticketid) {
		int re = -1;
		String sql = "delete ticket where ticketid=?";
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			Context context = new InitialContext();
			DataSource ds = (DataSource)context.lookup("java:/comp/env/mydb");
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, ticketid);
			re = pstmt.executeUpdate();
		} catch (Exception e) {
			System.out.println("예외발생:"+e.getMessage());
		} finally {
			if(pstmt != null) {try {pstmt.close();} catch (SQLException e) {e.printStackTrace();}}
			if(conn != null) {try {conn.close();} catch (SQLException e) {e.printStackTrace();}}
		}
		return re;
	}
	
	
	//전체 티켓 목록 출력
	public ArrayList<TicketVO> listTicket(){
		ArrayList<TicketVO> list = new ArrayList<TicketVO>();
		String sql = "select * from ticket";
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
				TicketVO t = new TicketVO();
				t.setTicketid(rs.getInt("ticketid"));
				t.setCateid(rs.getInt("cateid"));
				t.setPlaceid(rs.getString("placeid"));
				t.setTicket_name(rs.getString("ticket_name"));
				t.setPrice(rs.getInt("price"));
				t.setTicket_date(rs.getString("ticket_date"));
				t.setMin_age(rs.getInt("min_age"));
				t.setRuntime(rs.getInt("runtime"));
				t.setCast(rs.getString("cast"));
				t.setContent(rs.getString("content"));
				t.setImg_fname(rs.getString("img_fname"));
				t.setVid_url(rs.getString("vid_url"));
				t.setLoc(rs.getString("loc"));
				t.setLat(rs.getDouble("lat"));
				t.setLng(rs.getDouble("lng"));
				list.add(t);
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
	
	
	//특정 티켓 내역 출력
	public TicketVO findById(int ticketid) {
		TicketVO t = null;
		String sql = "select * from ticket where ticketid=?";
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			Context context = new InitialContext();
			DataSource ds = (DataSource)context.lookup("java:/comp/env/mydb");
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, ticketid);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				t = new TicketVO();
				t.setTicketid(rs.getInt("ticketid"));
				t.setCateid(rs.getInt("cateid"));
				t.setPlaceid(rs.getString("placeid"));
				t.setTicket_name(rs.getString("ticket_name"));
				t.setPrice(rs.getInt("price"));
				t.setTicket_date(rs.getString("ticket_date"));
				t.setMin_age(rs.getInt("min_age"));
				t.setRuntime(rs.getInt("runtime"));
				t.setCast(rs.getString("cast"));
				t.setContent(rs.getString("content"));
				t.setImg_fname(rs.getString("img_fname"));
				t.setVid_url(rs.getString("vid_url"));
				t.setLoc(rs.getString("loc"));
				t.setLat(rs.getDouble("lat"));
				t.setLng(rs.getDouble("lng"));
			}
		} catch (Exception e) {
			System.out.println("예외발생:"+e.getMessage());
		} finally {
			if(rs != null) {try {rs.close();} catch (SQLException e) {e.printStackTrace();}}
			if(pstmt != null) {try {pstmt.close();} catch (SQLException e) {e.printStackTrace();}}
			if(conn != null) {try {conn.close();} catch (SQLException e) {e.printStackTrace();}}
		}
		return t;
	}
	
	//현재 날짜 리턴하는 메소드
	public String todayDate() {
		 // 현재 날짜 구하기
        LocalDate now = LocalDate.now();
 
        // 포맷 정의
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd");
 
        // 포맷 적용
        String formatedNow = now.format(formatter);
        
		return formatedNow;
	}
	
	//현재 시간 리턴하는 메소드
	public String nowTime() {
		// 현재 시간
        LocalTime now = LocalTime.now();
 
        // 현재시간 출력
        System.out.println(now);  // 06:20:57.008731300
 
        // 포맷 정의하기
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH시 mm분 ss초");
 
        // 포맷 적용하기
        String formatedNow = now.format(formatter);
        
		return formatedNow;
	}
}
