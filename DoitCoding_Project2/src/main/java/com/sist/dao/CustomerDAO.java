package com.sist.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Calendar;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import com.sist.vo.CustomerVO;

public class CustomerDAO {
	//싱글톤 방식
	private static CustomerDAO customerDAO;
	private CustomerDAO() {	}
	public static CustomerDAO getInstance() {
		if(customerDAO == null) {
			customerDAO = new CustomerDAO();
		}
		return customerDAO;
	}
	
	public String findPwd(String custid, String phone) {
		String pwd = null;
		String sql = "select custid from customer where custid = ? and phone = ?";
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			Context context = new InitialContext();
			DataSource ds = (DataSource)context.lookup("java:/comp/env/mydb");
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, custid);
			pstmt.setString(2, phone);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				pwd = rs.getString(1);
			}
		}catch (Exception e) {
			System.out.println(e.getMessage());
		}finally {
			if(pstmt != null) {try {pstmt.close();} catch (SQLException e) {e.printStackTrace();}}
			if(conn != null) {try {conn.close();} catch (SQLException e) {e.printStackTrace();}}
			if(rs != null) {try {rs.close();} catch (SQLException e) {e.printStackTrace();}}
		}
		
		return pwd;
	}
	
	public String findCustid(String name, String phone) {
		String custid = null;
		String sql = "select custid from customer where name = ? and phone = ?";
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			Context context = new InitialContext();
			DataSource ds = (DataSource)context.lookup("java:/comp/env/mydb");
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, name);
			pstmt.setString(2, phone);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				custid = rs.getString(1);
			}
		}catch (Exception e) {
			System.out.println(e.getMessage());
		}finally {
			if(pstmt != null) {try {pstmt.close();} catch (SQLException e) {e.printStackTrace();}}
			if(conn != null) {try {conn.close();} catch (SQLException e) {e.printStackTrace();}}
			if(rs != null) {try {rs.close();} catch (SQLException e) {e.printStackTrace();}}
		}
		
		return custid;
	}
	
	//회원 가입
	public int insertCustomer(CustomerVO c) {
		int re = -1;
		String sql = "insert into customer(custid,pwd,name,birth,email,phone,gender,cateid) values(?,?,?,?,?,?,?,?)";
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			Context context = new InitialContext();
			DataSource ds = (DataSource)context.lookup("java:/comp/env/mydb");
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, c.getCustid());
			pstmt.setString(2, c.getPwd());
			pstmt.setString(3, c.getName());
			pstmt.setString(4, c.getBirth());
			pstmt.setString(5, c.getEmail());
			pstmt.setString(6, c.getPhone());
			pstmt.setString(7, c.getGender());
			pstmt.setInt(8, c.getCateid());
			
			re = pstmt.executeUpdate();
		} catch (Exception e) {
			System.out.println("예외발생:"+e.getMessage());
		} finally {
			if(pstmt != null) {try {pstmt.close();} catch (SQLException e) {e.printStackTrace();}}
			if(conn != null) {try {conn.close();} catch (SQLException e) {e.printStackTrace();}}
		}
		return re;
	}
	
		
	//회원 정보 수정
	public int updateCustomer(CustomerVO c) {
		int re = -1;
		String sql = "update customer set pwd=?,name=?,birth=?,email=?,phone=?,gender=?,cateid=? where custid=?";
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			Context context = new InitialContext();
			DataSource ds = (DataSource)context.lookup("java:/comp/env/mydb");
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, c.getPwd());
			pstmt.setString(2, c.getName());
			pstmt.setString(3, c.getBirth());
			pstmt.setString(4, c.getEmail());
			pstmt.setString(5, c.getPhone());
			pstmt.setString(6, c.getGender());
			pstmt.setInt(7, c.getCateid());
			pstmt.setString(8, c.getCustid());
			re = pstmt.executeUpdate();
		} catch (Exception e) {
			System.out.println("예외발생:"+e.getMessage());
		} finally {
			if(pstmt != null) {try {pstmt.close();} catch (SQLException e) {e.printStackTrace();}}
			if(conn != null) {try {conn.close();} catch (SQLException e) {e.printStackTrace();}}
		}
		return re;
	}
	
	
	//회원 탈퇴
	public int deleteCustomer(String custid) {
		int re = -1;
		String sql = "delete customer where custid=?";
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			Context context = new InitialContext();
			DataSource ds = (DataSource)context.lookup("java:/comp/env/mydb");
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, custid);
			re = pstmt.executeUpdate();
		} catch (Exception e) {
			System.out.println("예외발생:"+e.getMessage());
		} finally {
			if(pstmt != null) {try {pstmt.close();} catch (SQLException e) {e.printStackTrace();}}
			if(conn != null) {try {conn.close();} catch (SQLException e) {e.printStackTrace();}}
		}
		return re;
	}
	
	
	//전체 회원 목록 출력
	public ArrayList<CustomerVO> listCustomer(){
		ArrayList<CustomerVO> list = new ArrayList<CustomerVO>();
		String sql = "select * from customer";
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
				CustomerVO c = new CustomerVO();
				c.setCustid(rs.getString("custid"));
				c.setPwd(rs.getString("pwd"));
				c.setName(rs.getString("name"));
				c.setBirth(rs.getString("birth"));
				c.setEmail(rs.getString("email"));
				c.setPhone(rs.getString("phone"));
				c.setGender(rs.getString("gender"));
				c.setCateid(rs.getInt("cateid"));
				list.add(c);
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
	
	
	//특정 회원 목록 출력
	public CustomerVO findById(String custid) {
		CustomerVO c = null;
		String sql = "select * from customer where custid=?";
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
			if(rs.next()) {
				c = new CustomerVO();
				c.setCustid(rs.getString("custid"));
				c.setPwd(rs.getString("pwd"));
				c.setName(rs.getString("name"));
				c.setBirth(rs.getString("birth"));
				c.setEmail(rs.getString("email"));
				c.setPhone(rs.getString("phone"));
				c.setGender(rs.getString("gender"));
				c.setCateid(rs.getInt("cateid"));
			}
		} catch (Exception e) {
			System.out.println("예외발생:"+e.getMessage());
		} finally {
			if(rs != null) {try {rs.close();} catch (SQLException e) {e.printStackTrace();}}
			if(pstmt != null) {try {pstmt.close();} catch (SQLException e) {e.printStackTrace();}}
			if(conn != null) {try {conn.close();} catch (SQLException e) {e.printStackTrace();}}
		}
		return c;
	}
	
	// 로그인 (아이디랑 패스워드 일치 불일치 확인)
	// login_Flag = true (입력한 custid에 해당하는 pwd를 db에서 추출하여 입력한 pwd와 맞으면 true)
	// 입력한 id : String custid , 입력한 pwd : String pwd
	// db에 있는 pwd : rs.getString(1)
	
	public boolean login(String custid, String pwd) {
		boolean login_Flag = false;
		String sql = "select pwd from customer where custid=?";
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
				if(rs.getString(1).equals(pwd)) {
					login_Flag = true;
				}
			}
			
		}catch(Exception e) {
			System.out.println("예외" + e.getMessage());
			}finally {
				if(rs != null) {try {rs.close();} catch (SQLException e) {e.printStackTrace();}}
				if(pstmt != null) {try {pstmt.close();} catch (SQLException e) {e.printStackTrace();}}
				if(conn != null) {try {conn.close();} catch (SQLException e) {e.printStackTrace();}}
			}
		return login_Flag;
	}
	
	// custid 중복을 확인하는 메소드
	
	public int confirmCustomerID(String custid) {
		CustomerVO c = null;
		int confirm_custid = 0;
		String sql = "select custid from customer where custid=?";
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
				if(rs.getString(1).equals(custid)) {
					confirm_custid = 1;
				}
			}
			
		}catch(Exception e) {
			System.out.println("예외" + e.getMessage());
			}finally {
				if(rs != null) {try {rs.close();} catch (SQLException e) {e.printStackTrace();}}
				if(pstmt != null) {try {pstmt.close();} catch (SQLException e) {e.printStackTrace();}}
				if(conn != null) {try {conn.close();} catch (SQLException e) {e.printStackTrace();}}
			}
		
		return confirm_custid;
	}
	
	// phone 중복을 확인하는 메소드
	
	public int confirmCustomerPhone(String phone) {
		CustomerVO c = null;
		int confirm_phone = 0;
		String sql = "select phone from customer where phone=?";
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			Context context = new InitialContext();
			DataSource ds = (DataSource)context.lookup("java:/comp/env/mydb");
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, phone);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				if(rs.getString(1).equals(phone)) {
					confirm_phone = 1;
				}
			}
			
		}catch(Exception e) {
			System.out.println("예외" + e.getMessage());
			}finally {
				if(rs != null) {try {rs.close();} catch (SQLException e) {e.printStackTrace();}}
				if(pstmt != null) {try {pstmt.close();} catch (SQLException e) {e.printStackTrace();}}
				if(conn != null) {try {conn.close();} catch (SQLException e) {e.printStackTrace();}}
			}
		
		return confirm_phone;
	}
	
	//db에서 생일 가져오기
		public String getBirth(String custid) {
			String birth = null;
			String sql = "select birth from customer where custid=?";
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
			} catch (Exception e) {
				System.out.println("예외발생:"+e.getMessage());
			} finally {
				if(pstmt != null) {try {pstmt.close();} catch (SQLException e) {e.printStackTrace();}}
				if(conn != null) {try {conn.close();} catch (SQLException e) {e.printStackTrace();}}
			}
			return birth;			 
		}
		
		//생년월일로 만 나이 구하는 메소드  ==> 세대별 통계, 관람등급에 사용	
		public int getAge(String custid) {
			//db에 저장된 생일 불러오기
			CustomerVO c = findById(custid);
			String birth = c.getBirth();
			
			int age = 0;
			
			//태어난 년월일 문자열을 숫자로 변환
			String strYear = birth.substring(0, 4);		//인덱스가 0부터 시작, 마지막 인덱스는 포함되지 않음
			String strMonth = birth.substring(5,7);
			String strDay = birth.substring(8, 10);
			int birthYear = Integer.parseInt(strYear);
			int birthMonth = Integer.parseInt(strMonth);
			int birthDay = Integer.parseInt(strDay);
			
			//현재 년월일
			Calendar today = Calendar.getInstance();	//년월일시분초
			int thisYear = today.get(Calendar.YEAR);
			int thisMonth = today.get(Calendar.MONTH)+1;	//0월부터 시작하기 때문에 +1
			int thisDay = today.get(Calendar.DAY_OF_MONTH);
			
			//현재 만 나이 구하기
			age = thisYear-birthYear;
			if(birthMonth*100+birthDay > thisMonth*100+thisDay) {
				age--;		//생일이 지나지 않았으면 1살을 뺌
			}
			return age;
		}
			
		
	
	
	
}
