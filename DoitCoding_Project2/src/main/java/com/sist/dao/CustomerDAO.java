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
	
	
	
}
