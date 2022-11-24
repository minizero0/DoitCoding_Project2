package com.sist.dao;

import java.lang.reflect.Array;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import com.sist.vo.MyReviewVO;
import com.sist.vo.RankingVO;
import com.sist.vo.ReviewVO;
import com.sist.vo.TicketVO;

public class ReviewDAO {
	//싱글톤 방식
	private static ReviewDAO reviewDAO;
	private ReviewDAO() {	}
	public static ReviewDAO getInstance() {
		if(reviewDAO == null) {
			reviewDAO = new ReviewDAO();
		}
		return reviewDAO;
	}
	
	//새로운 예매번호 발행
    public int getNextReviewid() {
        int reviewid = 0;
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        String sql = "select nvl(max(reviewid),0) + 1 from review";
        try {
            Context context = new InitialContext();
            DataSource ds = (DataSource)context.lookup("java:/comp/env/mydb");
            conn = ds.getConnection();
            pstmt = conn.prepareStatement(sql);
            rs = pstmt.executeQuery();
            if(rs.next()) {
                reviewid = rs.getInt(1);
            }
        }catch (Exception e) {
            System.out.println(e.getMessage());
        }finally {
            if(pstmt != null) {try {pstmt.close();} catch (SQLException e) {e.printStackTrace();}}
            if(conn != null) {try {conn.close();} catch (SQLException e) {e.printStackTrace();}}
            if(rs != null) {try {rs.close();} catch (SQLException e) {e.printStackTrace();}}
        }
        return reviewid;
    }
	
	//후기 추가
	public int insertReview(ReviewVO r) {
		int re = -1;
		String sql = "insert into review(reviewid,custid,ticketid,score,review_content) values(?,?,?,?,?)";
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			Context context = new InitialContext();
			DataSource ds = (DataSource)context.lookup("java:/comp/env/mydb");
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, r.getReviewid());
			pstmt.setString(2, r.getCustid());
			pstmt.setInt(3, r.getTicketid());
			pstmt.setDouble(4, r.getScore());
			pstmt.setString(5, r.getReview_content());
			System.out.println(r.getReview_content() +"\n"+re);
			re = pstmt.executeUpdate();
			System.out.println(r.getReview_content() +"\n"+re);
		} catch (Exception e) {
			System.out.println("예외발생:"+e.getMessage());
		} finally {
			if(pstmt != null) {try {pstmt.close();} catch (SQLException e) {e.printStackTrace();}}
			if(conn != null) {try {conn.close();} catch (SQLException e) {e.printStackTrace();}}
		}
		return re;
	}
	
		
	//후기 수정
	public int updateReview(ReviewVO r) {
		int re = -1;
		String sql = "update review set review_content=? where custid=? and ticketid=?";
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			Context context = new InitialContext();
			DataSource ds = (DataSource)context.lookup("java:/comp/env/mydb");
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(sql);			
			
			pstmt.setString(1, r.getReview_content());
			pstmt.setString(2, r.getCustid());
			pstmt.setInt(3, r.getTicketid());
			re = pstmt.executeUpdate();
		} catch (Exception e) {
			System.out.println("예외발생:"+e.getMessage());
		} finally {
			if(pstmt != null) {try {pstmt.close();} catch (SQLException e) {e.printStackTrace();}}
			if(conn != null) {try {conn.close();} catch (SQLException e) {e.printStackTrace();}}
		}
		return re;
	}
	
	
	//후기 삭제
	public int deleteReview(ReviewVO r) {
		int re = -1;
		String sql = "delete review where custid=? and ticketid=?";
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			Context context = new InitialContext();
			DataSource ds = (DataSource)context.lookup("java:/comp/env/mydb");
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, r.getCustid());
			pstmt.setInt(2, r.getTicketid());
			re = pstmt.executeUpdate();
		} catch (Exception e) {
			System.out.println("예외발생:"+e.getMessage());
		} finally {
			if(pstmt != null) {try {pstmt.close();} catch (SQLException e) {e.printStackTrace();}}
			if(conn != null) {try {conn.close();} catch (SQLException e) {e.printStackTrace();}}
		}
		return re;
	}
	
	
	//전체 후기 목록 출력
	public ArrayList<ReviewVO> listReview(){
		ArrayList<ReviewVO> list = new ArrayList<ReviewVO>();
		String sql = "select * from review";
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
				ReviewVO r = new ReviewVO();
				r.setReviewid(rs.getInt("reviewid"));
				r.setCustid(rs.getString("custid"));
				r.setTicketid(rs.getInt("ticketid"));
				r.setScore(rs.getInt("score"));
				r.setReview_content(rs.getString("review_content"));
				list.add(r);
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
	
	
	//특정 후기 목록 출력
	public ReviewVO findById(int reviewid) {
		ReviewVO r = null;
		String sql = "select * from review where reviewid=?";
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			Context context = new InitialContext();
			DataSource ds = (DataSource)context.lookup("java:/comp/env/mydb");
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, reviewid);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				r = new ReviewVO();
				r.setReviewid(rs.getInt("reviewid"));
				r.setCustid(rs.getString("custid"));
				r.setTicketid(rs.getInt("ticketid"));
				r.setScore(rs.getInt("score"));
				r.setReview_content(rs.getString("review_content"));
			}
		} catch (Exception e) {
			System.out.println("예외발생:"+e.getMessage());
		} finally {
			if(rs != null) {try {rs.close();} catch (SQLException e) {e.printStackTrace();}}
			if(pstmt != null) {try {pstmt.close();} catch (SQLException e) {e.printStackTrace();}}
			if(conn != null) {try {conn.close();} catch (SQLException e) {e.printStackTrace();}}
		}
		return r;
	}
	
	//사용자 리뷰 내역 출력
		public ArrayList<MyReviewVO> findByCustid(String custid) {
			ArrayList<MyReviewVO> list = new ArrayList<MyReviewVO>();
			String sql = "select * from review r, ticket t where t.ticketid= r.ticketid and custid=?";
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
					MyReviewVO m = new MyReviewVO();
					m.setReviewid(rs.getInt("reviewid"));
					m.setCustid(rs.getString("custid"));
					m.setTicketid(rs.getInt("ticketid"));
					m.setScore(rs.getInt("score"));
					m.setReview_content(rs.getString("review_content"));
					m.setTicket_name(rs.getString("ticket_name"));
					m.setTicket_date(rs.getString("ticket_date"));
					list.add(m);
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
		//랭킹. 리뷰 스코어 높은순으로 정렬. 내가 선호하는 장르가 먼저 나오게. ticketid랑 score 담은 list 반환
				public ArrayList<RankingVO> ranking(int cateid) {
					ArrayList<RankingVO> list = new ArrayList<RankingVO>();
					
					String sql = "select t.ticketid, ticket_name, img_fname, score from ticket t, review r where t.ticketid = r.ticketid and cateid = ? order by score desc";
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
							RankingVO r = new RankingVO();
							r.setTicketid(rs.getInt("ticketid"));
							r.setTicket_name(rs.getString("ticket_name"));			
							r.setImg_fname(rs.getString("img_fname"));
							r.setScore(rs.getInt("score"));
							list.add(r);
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
		
		
	//랭킹. 리뷰 스코어 높은순으로 정렬. 내가 선호하는 장르가 먼저 나오게. ticketid랑 score 담은 list 반환
	/*public ArrayList<ReviewVO> ranking(int cateid) {
		ArrayList<ReviewVO> list = new ArrayList<ReviewVO>();
		String sql = "select t.ticketid,score from ticket t, review r where t.ticketid = r.ticketid and cateid = ? order by score desc";
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
				ReviewVO r = new ReviewVO();
				r.setTicketid(rs.getInt("ticketid"));
				r.setScore(rs.getInt("score"));
				list.add(r);
			}
		} catch (Exception e) {
			System.out.println("예외발생:"+e.getMessage());
		} finally {
			if(rs != null) {try {rs.close();} catch (SQLException e) {e.printStackTrace();}}
			if(pstmt != null) {try {pstmt.close();} catch (SQLException e) {e.printStackTrace();}}
			if(conn != null) {try {conn.close();} catch (SQLException e) {e.printStackTrace();}}
		}
		return list;
	}*/
	
	//해당 작품의 후기 보여주는 메소드. re는 정렬방식. re>0이면 내림차순(desc), re<0이면 오름차순
	public ArrayList<ReviewVO> findByTicketid(int ticketid, int re){
		ArrayList<ReviewVO> list = new ArrayList<ReviewVO>();
		String sql = "select * from review where ticketid = ?";
		if(re>0) {
			sql += " order by score desc";
		}else if(re<0){
			sql += " order by score";
		}else {
			sql += " order by score desc";
		}
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
			
			while(rs.next()) {
				ReviewVO b = new ReviewVO();
				b.setReviewid(rs.getInt("reviewid"));
				b.setCustid(rs.getString("custid"));
				b.setTicketid(ticketid);
				b.setScore(rs.getInt("score"));
				b.setReview_content(rs.getString("review_content"));
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
	
	//해당 작품의 평균 별점 리턴하는 메소드
	public int avgScore(int ticketid) {
		int score=0;
		String sql = "select avg(score) from review where ticketid=?";
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
				score = rs.getInt(1);
			}
		} catch (Exception e) {
			System.out.println("예외발생:"+e.getMessage());
		} finally {
			if(pstmt != null) {try {pstmt.close();} catch (SQLException e) {e.printStackTrace();}}
			if(conn != null) {try {conn.close();} catch (SQLException e) {e.printStackTrace();}}
		}
		return score;
	}
	
	//사용자 리뷰 내역 출력
			public int checkReview(String custid, int ticketid) {
				int re = 0;
				String sql = "select * from review r, ticket t where t.ticketid= r.ticketid and custid=? and r.ticketid = ?";
				Connection conn = null;
				PreparedStatement pstmt = null;
				ResultSet rs = null;
				try {
					Context context = new InitialContext();
					DataSource ds = (DataSource)context.lookup("java:/comp/env/mydb");
					conn = ds.getConnection();
					pstmt = conn.prepareStatement(sql);
					pstmt.setString(1, custid);
					pstmt.setInt(2, ticketid);
					rs = pstmt.executeQuery();
					if(rs.next()) {
						re = 1;
					}
				} catch (Exception e) {
					System.out.println("예외발생:"+e.getMessage());
				} finally {
					if(rs != null) {try {rs.close();} catch (SQLException e) {e.printStackTrace();}}
					if(pstmt != null) {try {pstmt.close();} catch (SQLException e) {e.printStackTrace();}}
					if(conn != null) {try {conn.close();} catch (SQLException e) {e.printStackTrace();}}
				}
				return re;
			}
			
			
			//ticketid로 리뷰내역확인 
			public int CheckReviewByTicketid(int ticketid) {
				int re = -1;
				String sql = "select * from review r, ticket t where t.ticketid= r.ticketid and r.ticketid = ?";
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
						re = 1;
					}
				} catch (Exception e) {
					System.out.println("예외발생:"+e.getMessage());
				} finally {
					if(rs != null) {try {rs.close();} catch (SQLException e) {e.printStackTrace();}}
					if(pstmt != null) {try {pstmt.close();} catch (SQLException e) {e.printStackTrace();}}
					if(conn != null) {try {conn.close();} catch (SQLException e) {e.printStackTrace();}}
				}
				return re;
			}
}