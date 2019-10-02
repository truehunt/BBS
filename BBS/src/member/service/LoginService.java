package member.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import javax.naming.NamingException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import jdbc.JdbcUtil;
import jdbc.connection.ConnectionProvider;
import member.dao.MemberDao;
import member.model.Member;

public class LoginService {
	private MemberDao dao = new MemberDao();
	
	

	public void login(LoginRequest loginreq, HttpServletRequest req, Map<String, Boolean> errors) {
		boolean result = false;
		Connection conn = null;
		try {
			conn = ConnectionProvider.getConnection(); 
			Member bean = dao.selectById(conn, loginreq.getId());
					
			
			result =  dao.isExisted(conn, new Member(loginreq.getId(),loginreq.getPassword()));
			System.out.println("1111:"+ result);
			check(errors, result, "result");
			System.out.println("2222:" + result);
			if (!result) {
				JdbcUtil.rollback(conn);
				throw new DuplicateIdException();		
			}
			HttpSession session = req.getSession();
	        session.setAttribute("isLogon", true);
			
		} catch (NamingException e) {
			JdbcUtil.rollback(conn);
			throw new RuntimeException(e);
		} catch (SQLException e) {
			JdbcUtil.rollback(conn);
			throw new RuntimeException(e);
		} finally {
			JdbcUtil.close(conn);
		}
	}
	
	private void check(Map<String, Boolean> errors, boolean result, String fieldName) {
		if(!result) {
			errors.put(fieldName, Boolean.TRUE);
			throw new DuplicateIdException();
		}
		System.out.println("333333:" + result);
	}
	
}
