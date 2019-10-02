package member.command;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import command.CommandHandler;
import member.service.DuplicateIdException;

public class LogoutHandler implements CommandHandler {

	private static final String FORM_VIEW = "/main.jsp";

	@Override
	public String process(HttpServletRequest req, HttpServletResponse res) {
			return processSubmit(req, res);
	}

	private String processSubmit(HttpServletRequest req, HttpServletResponse res) {
		System.out.println("logout : ");
			HttpSession session = req.getSession();
			session.invalidate();
						
			
			return FORM_VIEW;
	}

}

