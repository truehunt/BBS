package member.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Date;
import java.util.Map;

import javax.naming.NamingException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jdbc.JdbcUtil;
import jdbc.connection.ConnectionProvider;
import member.model.Member;
import member.service.LoginRequest;

public class MemberDao {

	public Member selectById(Connection conn, String id) throws SQLException {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = conn.prepareStatement(
					"select * from member where member_id = ?");
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			Member member = null;
			if (rs.next()) {
				member = new Member(
						rs.getString("member_id"), 
						rs.getString("name"), 
						rs.getString("password"),
						toDate(rs.getTimestamp("regdate")));
			}
			return member;
		} finally {
			JdbcUtil.close(rs);
			JdbcUtil.close(pstmt);
		}
	}

	private Date toDate(Timestamp date) {
		return date == null ? null : new Date(date.getTime());
	}

	public void insert(Connection conn, Member mem) throws SQLException {
		try (PreparedStatement pstmt = 
				conn.prepareStatement("insert into member values(?,?,?,?)")) {
			pstmt.setString(1, mem.getId());
			pstmt.setString(2, mem.getName());
			pstmt.setString(3, mem.getPassword());
			pstmt.setTimestamp(4, new Timestamp(mem.getRegDate().getTime()));
			pstmt.executeUpdate();
		}
	}

	public void changepw(Connection conn, Member bean) throws SQLException {
		 	PreparedStatement pstmt = null;
		 	String old_pwd = bean.getPassword();
		 	String new_pwd = bean.getNewPwd();
		 	System.out.println("old_pwd:" + old_pwd);
		 	System.out.println("new_pwd:" + new_pwd);
		 	try {
		 	 conn = ConnectionProvider.getConnection();
		 	 String query = "update member set password = ? where member_id = ?";
			 System.out.println(query);
	    	 pstmt = conn.prepareStatement(query);
	         pstmt.setString(1, bean.getNewPwd());
	         pstmt.setString(2, bean.getId());
	         pstmt.executeUpdate();
	         pstmt.close();
	         conn.close();
	      }catch(Exception e) {
	    	  e.printStackTrace();
	      }
}
	
	public String name(Connection conn, String id) throws SQLException, NamingException {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
	 	try {
	 	 conn = ConnectionProvider.getConnection();
	 	 String query = "select name from member where member_id=?";
		 System.out.println(query);
    	 pstmt = conn.prepareStatement(query);
         pstmt.setString(1, id);
         rs = pstmt.executeQuery();
         Member member = null;
         String name= null;
         if(rs.next()) {
        	 member = new Member(rs.getString("name"));
        	 name = member.getName();
         }
         	rs.close();
         	conn.close();
         	pstmt.close();
			return name;
		} finally {
			JdbcUtil.close(rs);
			JdbcUtil.close(pstmt);
		}
	}
	public boolean isExisted(Connection conn, Member bean) {
		boolean result = false;	
		PreparedStatement pstmt = null;
		String id = bean.getId();
		String pwd = bean.getPassword();
		try {
			conn = ConnectionProvider.getConnection(); 
			String query = "SELECT DECODE(COUNT(*),1,'true','false') AS result FROM member WHERE member_id ='" + id +"' AND password ='" + pwd +"'";
			System.out.println(query);
			pstmt = conn.prepareStatement(query);
			ResultSet rs = pstmt.executeQuery();
			rs.next();
			result = Boolean.parseBoolean(rs.getString("result"));
			rs.close();
			conn.close();
			pstmt.close();
		}catch(Exception e) {
			e.printStackTrace();
		}
		System.out.println(result);
		return result;
	}
	
}

