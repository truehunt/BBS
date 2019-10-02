package member.command;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

import javax.naming.NamingException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import command.CommandHandler;
import member.dao.MemberDao;
import member.model.Member;
import member.service.DuplicateIdException;
import member.service.ChangePwRequest;

public class ChangePwHandler implements CommandHandler {

	private static final String FORM_VIEW = "/changepw.jsp";
	private static final String LOGIN_AFTER = "/loginafter.jsp";

	@Override
	public String process(HttpServletRequest req, HttpServletResponse res) throws SQLException, NamingException {
		if (req.getMethod().equalsIgnoreCase("GET")) {
			return processForm(req, res);
		} else if (req.getMethod().equalsIgnoreCase("POST")) {
			return processSubmit(req, res);
		} else {
			res.setStatus(HttpServletResponse.SC_METHOD_NOT_ALLOWED);
			return null;
		}
	}

	private String processForm(HttpServletRequest req, HttpServletResponse res) {
		return FORM_VIEW;
	}

	private String processSubmit(HttpServletRequest req, HttpServletResponse res) throws SQLException, NamingException {
		ChangePwRequest changereq = new ChangePwRequest();
		changereq.setOld_pwd(req.getParameter("old_pwd"));
		changereq.setNew_pwd(req.getParameter("new_pwd"));
		String old_pwd = changereq.getOld_pwd();
		MemberDao dao = new MemberDao();
		Connection conn = null;
		HttpSession session = req.getSession();
		
		String id = (String) session.getAttribute("input_id");
		String pwd = (String) session.getAttribute("input_pwd");
		String name = dao.name(conn, id);
		req.setAttribute("name", name);;
		System.out.println("id:"+id);
		System.out.println("pw:"+pwd);
		if (old_pwd.equals(pwd)) {
			session.setAttribute("input_pwd", changereq.getNew_pwd());
			dao.changepw(conn, new Member(id, changereq.getOld_pwd(), changereq.getNew_pwd()));
			return LOGIN_AFTER;
		} else {
				return FORM_VIEW;
		}
	}
}
