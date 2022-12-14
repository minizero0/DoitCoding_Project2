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

import com.sist.vo.BookByCustidVO;
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
		System.out.println(ticketid);
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
        //System.out.println(now);  // 06:20:57.008731300
        // 포맷 정의하기
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss");
        // 포맷 적용하기
        String formatedNow = now.format(formatter);
        //System.out.println("현재:"+formatedNow);
		return formatedNow;
	}
	
	//잔여좌석수 반환하는 메소드
	public int leftSeat(int ticketid) {
		int num = 0;
		String sql = "select count(*) from seat where ticketid = ? and check_seat= 'n'";
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
				num = rs.getInt(1);
			}
		} catch (Exception e) {
			System.out.println("예외발생:"+e.getMessage());
		} finally {
			if(pstmt != null) {try {pstmt.close();} catch (SQLException e) {e.printStackTrace();}}
			if(conn != null) {try {conn.close();} catch (SQLException e) {e.printStackTrace();}}
		}
		return num;
	}
	
	//예매 오픈 날짜를 리턴하는 메소드
		public String openDate(int ticketid) {
			System.out.println("다오에서 ticketid:"+ticketid);
			String open="";
			String sql="select to_char(to_date(ticket_date, 'yyyy/mm/dd hh24:mi:ss')-7,'yyyy/mm/dd') open from ticket where ticketid=?";
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
					open = rs.getString(1);
				}
			} catch (Exception e) {
				System.out.println("예외발생:"+e.getMessage());
			} finally {
				if(rs != null) {
					try {
						rs.close();
					} catch (Exception e2) {
						// TODO: handle exception
					} finally {
						
					}
				}
				if(pstmt != null) {try {pstmt.close();} catch (SQLException e) {e.printStackTrace();}}
				if(conn != null) {try {conn.close();} catch (SQLException e) {e.printStackTrace();}}
			}
			System.out.println("오픈일:"+open);
			return open;
		}
	
	//검색 기능 메소드. ticket * 리턴. 검색 키워드는 제목, 캐스트
	public ArrayList<TicketVO> searchTicket(String keyword){
		ArrayList<TicketVO> list = new ArrayList<TicketVO>();
		String sql = "select * from ticket where ticket_name like '%"+keyword+"%' or cast like '%"+keyword+"%'";
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
	
	//예매하기 버튼 닫는 날짜를 리턴하는 메소드
	public String closeDate(int ticketid) {
	    System.out.println("다오에서 ticketid:"+ticketid);
	    String close="";
	    String sql="select to_char(to_date(ticket_date, 'yyyy/mm/dd hh24:mi:ss')+1,'yyyy/mm/dd') close from ticket where ticketid=?";
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
	            close = rs.getString(1);
	        }
	    } catch (Exception e) {
	        System.out.println("예외발생:"+e.getMessage());
	    } finally {
	        if(rs != null) {try {rs.close();} catch (SQLException e) {e.printStackTrace();}}
	        if(pstmt != null) {try {pstmt.close();} catch (SQLException e) {e.printStackTrace();}}
	        if(conn != null) {try {conn.close();} catch (SQLException e) {e.printStackTrace();}}
	    }
	    System.out.println("마감일:"+close);
	    return close;
	}
	
	// 메인페이지에서 카테고리 별로 현재 상영작, 미래 상영작 출력하기
		public ArrayList<TicketVO> selectTicketByCategory(int time, int cateid){
			ArrayList<TicketVO> list = new ArrayList<TicketVO>();

			// time = 0이면 과거 상영작, 1이면 현재 상영작, 2이면 미래 상영작
			
			String sql = "";
			
			if(time == 0) { // 과거
				sql = "select * from ticket where cateid=? and ticket_date < to_char(sysdate, 'yyyy/mm/dd')";
			}
			
			else if(time == 2) { //미래
				sql = "select * from ticket where cateid=? and ticket_date > to_char(sysdate+7, 'yyyy/mm/dd')";
			}
			else if(time==1){ //현재
				sql = "select * from ticket where cateid=? and ticket_date > to_char(sysdate, 'yyyy/mm/dd') and ticket_date <= to_char(sysdate+7, 'yyyy/mm/dd')";
			}
			
			Connection conn = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			try {
		
				Context context = new InitialContext();
				DataSource ds = (DataSource)context.lookup("java:/comp/env/mydb");
				conn = ds.getConnection();
				pstmt = conn.prepareStatement(sql);
				pstmt.setInt(1, cateid);
				
				rs = pstmt.executeQuery();
				
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
				if(pstmt != null) {try {pstmt.close();} catch (SQLException e) {e.printStackTrace();}}
				if(conn != null) {try {conn.close();} catch (SQLException e) {e.printStackTrace();}}
			}
			return list;
		}
		
		
		public ArrayList<BookByCustidVO> selectTicketByCustid (String custid){
			ArrayList<BookByCustidVO> list = new ArrayList<>();
			BookByCustidVO vo = null;
			String sql = "select custid, ticket_name, img_fname, t.ticketid, s.seatid, b.bookid, ticket_date, loc, seatnum, seatname "
					+ "from ticket t, book b, seat s, place p "
					+ "where t.ticketid=b.ticketid and b.seatid = s.seatid and s.placeid = p.placeid "
					+ "and custid=?";
			Connection conn = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			try {
				Context context = new InitialContext();
				DataSource ds = (DataSource)context.lookup("java:/comp/env/mydb");
				
				conn = ds.getConnection();
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, custid);
				rs = pstmt.executeQuery();
				while(rs.next()) {
					vo = new BookByCustidVO();
					
					vo.setBookid(rs.getInt("bookid"));
					vo.setCustid(rs.getString("custid"));
					vo.setImg_fname(rs.getString("img_fname"));
					vo.setLoc(rs.getString("loc"));
					vo.setSeatid(rs.getInt("seatid"));
					vo.setSeatname(rs.getString("seatname"));
					vo.setSeatnum(rs.getInt("seatnum"));
					vo.setTicket_date(rs.getString("ticket_date"));
					vo.setTicket_name(rs.getString("ticket_name"));
					vo.setTicketid(rs.getInt("ticketid"));
					
					list.add(vo);
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