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

import com.sist.vo.CategoryVO;

public class CategoryDAO {
	//싱글톤 방식
	private static CategoryDAO categoryDAO;
	private CategoryDAO() {	}
	public static CategoryDAO getInstance() {
		if(categoryDAO == null) {
			categoryDAO = new CategoryDAO();
		}
		return categoryDAO;
	}
	
	
	//카테고리 추가
	public int insertCategory(CategoryVO c) {
		int re = -1;
		String sql = "insert into category(cateid,catename) values(seq_category.nextval,?)";
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			Context context = new InitialContext();
			DataSource ds = (DataSource)context.lookup("java:/comp/env/mydb");
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, c.getCatename());
			re = pstmt.executeUpdate();
		} catch (Exception e) {
			System.out.println("예외발생:"+e.getMessage());
		} finally {
			if(pstmt != null) {try {pstmt.close();} catch (SQLException e) {e.printStackTrace();}}
			if(conn != null) {try {conn.close();} catch (SQLException e) {e.printStackTrace();}}
		}
		return re;
	}
	
		
	//카테고리 수정
	public int updateCategory(CategoryVO c) {
		int re = -1;
		String sql = "update category set catename=? where cateid=?";
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			Context context = new InitialContext();
			DataSource ds = (DataSource)context.lookup("java:/comp/env/mydb");
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, c.getCatename());
			pstmt.setInt(2, c.getCateid());
			re = pstmt.executeUpdate();
		} catch (Exception e) {
			System.out.println("예외발생:"+e.getMessage());
		} finally {
			if(pstmt != null) {try {pstmt.close();} catch (SQLException e) {e.printStackTrace();}}
			if(conn != null) {try {conn.close();} catch (SQLException e) {e.printStackTrace();}}
		}
		return re;
	}
	
	
	//카테고리 삭제
	public int deleteCategory(int cateid) {
		int re = -1;
		String sql = "delete category where cateid=?";
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			Context context = new InitialContext();
			DataSource ds = (DataSource)context.lookup("java:/comp/env/mydb");
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, cateid);
			re = pstmt.executeUpdate();
		} catch (Exception e) {
			System.out.println("예외발생:"+e.getMessage());
		} finally {
			if(pstmt != null) {try {pstmt.close();} catch (SQLException e) {e.printStackTrace();}}
			if(conn != null) {try {conn.close();} catch (SQLException e) {e.printStackTrace();}}
		}
		return re;
	}
	
	
	//전체 카테고리 목록 출력
	public ArrayList<CategoryVO> listCategory(){
		ArrayList<CategoryVO> list = new ArrayList<CategoryVO>();
		String sql = "select * from category";
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
				CategoryVO c = new CategoryVO();
				c.setCateid(rs.getInt("cateid"));
				c.setCatename(rs.getString("catename"));
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
	
	
	//특정 카테고리 목록 출력
	public CategoryVO findById(int cateid) {
		CategoryVO c = null;
		String sql = "select * from category where cateid=?";
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
			if(rs.next()) {
				c = new CategoryVO();
				c.setCateid(rs.getInt("cateid"));
				c.setCatename(rs.getString("catename"));
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
}
