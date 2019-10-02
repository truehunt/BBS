package member.command;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import command.CommandHandler;
import member.service.DuplicateIdException;
import member.service.JoinRequest;
import member.service.JoinService;

public class JoinHandler implements CommandHandler {

	private static final String FORM_VIEW = "/join.jsp";
	private JoinService joinService = new JoinService();

	@Override
	public String process(HttpServletRequest req, HttpServletResponse res) throws UnsupportedEncodingException {
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

	private String processSubmit(HttpServletRequest req, HttpServletResponse res) throws UnsupportedEncodingException {
		
		req.setCharacterEncoding("utf-8");
		
		JoinRequest joinReq = new JoinRequest();
		joinReq.setId(req.getParameter("id"));
		joinReq.setName(req.getParameter("name"));
		joinReq.setPassword(req.getParameter("password"));
		joinReq.setConfirmPassword(req.getParameter("confirmPassword"));

		Map<String, Boolean> errors = new HashMap<>();
		req.setAttribute("errors", errors);

		joinReq.validate(errors);

		if (!errors.isEmpty()) {
			return FORM_VIEW;
		}

		try {
			joinService.join(joinReq);
			return "/joinsuccess.jsp";
		} catch (DuplicateIdException e) {
			errors.put("duplicateId", Boolean.TRUE);
			return FORM_VIEW;
		}
	}

}
