package native_jdbc_programing.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import native_jdbc_programing.dto.Department;
import native_jdbc_programing.dto.Title;
import native_jdbc_programing.util.JdbcUtil;

public class TransactionService {
	
	public String transAddTitleAndDepartment(Title title,Department dept) {
		String titleSql = "insert into title values(?,?)";
		String deptSql = "insert into department values (?,?,?)";
		Connection con = null;
		PreparedStatement tPstmt = null;
		PreparedStatement dPstmt = null;
		String res;
		try {
			con = JdbcUtil.getConnection();
			con.setAutoCommit(false);
			
			tPstmt = con.prepareStatement(titleSql);
			tPstmt.setInt(1, title.getTno());
			tPstmt.setString(2, title.getTname());
			tPstmt.executeUpdate();
			
			dPstmt = con.prepareStatement(deptSql);
			dPstmt.setInt(1, dept.getDeptNo());
			dPstmt.setString(2, dept.getDeptName());
			dPstmt.setInt(3, dept.getFloor());
			dPstmt.executeUpdate();
			
			con.commit();
			res = "commit";
			
		
			
		}catch(SQLException e) {
			res = "rollback";
			rollbackUtil(con);
		}finally {
			closeUtil(con, tPstmt, dPstmt);
		}
		return res;
	}
	
	public int transRemoveTitleAndDepartment(Title title,Department dept) {
		String titleSql = "delete from title where tno = ?";
		String deptSql = "delete from department where deptno = ?";
		
		Connection con = null;
		PreparedStatement tPstmt = null;
		PreparedStatement dPstmt = null;		
		int res = 0;
		try {
			con = JdbcUtil.getConnection();
			con.setAutoCommit(false);
			
			tPstmt = con.prepareStatement(titleSql);
			tPstmt.setInt(1, title.getTno());
			res = res + tPstmt.executeUpdate();
			System.out.println("res "+ res);
			
			dPstmt = con.prepareStatement(deptSql);
			dPstmt.setInt(1, dept.getDeptNo());
			res = res + dPstmt.executeUpdate();
			System.out.println("res "+ res);
			
			if(res == 2) {
				con.commit();								
			}else {
				throw new SQLException();
			}
		}catch(SQLException e) {			
			rollbackUtil(con);
		}finally {
			closeUtil(con, tPstmt, dPstmt);
		} 
		return res;
	}

	public void closeUtil(Connection con, PreparedStatement tPstmt, PreparedStatement dPstmt) {
		try {
			con.setAutoCommit(true);
			if(tPstmt != null) tPstmt.close();
			if(dPstmt != null) dPstmt.close();
			if(con != null) con.close();
						
		} catch (SQLException e) {
		}
	}

	public void rollbackUtil(Connection con) {
		try {	
			con.rollback();
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
	}
}
