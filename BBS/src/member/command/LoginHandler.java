package member.command;

import java.sql.Connection;
import java.sql.SQLException;
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
import member.service.LoginRequest;
import member.service.LoginService;

public class LoginHandler implements CommandHandler {

	private static final String FORM_VIEW = "/login.jsp";
	private static final String FORM_VIEW2 = "/loginafter.jsp";
	private LoginService loginService = new LoginService();

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
		LoginRequest loginreq = new LoginRequest();
		loginreq.setId(req.getParameter("id"));
		loginreq.setPassword(req.getParameter("password"));
		MemberDao dao = new MemberDao();
		Map<String, Boolean> errors = new HashMap<>();
		req.setAttribute("errors", errors);
		
		Connection conn = null;
		String name = dao.name(conn, loginreq.getId());
		
		HttpSession session = req.getSession();
		session.setAttribute("input_id", loginreq.getId());
		session.setAttribute("input_pwd", loginreq.getPassword());
		session.setAttribute("name", name);
		
		loginreq.validate(errors);
		
		if(!errors.isEmpty()) {
			return FORM_VIEW;
		}
		try {
			loginService.login(loginreq, req, errors);;
			return FORM_VIEW2;
			
		}catch(DuplicateIdException e) {
			errors.put("duplicateId", Boolean.TRUE);
			return FORM_VIEW;
		}
		
		
		}
	}

