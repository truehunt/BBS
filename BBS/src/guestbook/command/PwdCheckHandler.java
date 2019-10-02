package guestbook.command;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import command.CommandHandler;
import guestbook.service.*;
import guestbook.model.*;

public class PwdCheckHandler implements CommandHandler {
	private static final String FORM_VIEW = "view/deleteGuestbook.jsp";
	private PwdCheckService checkService = new PwdCheckService();

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
		
		GuestbookVO vo = new GuestbookVO();
		vo.setMessageId(req.getParameter("messageId"));
		vo.setPassword(req.getParameter("password"));
		if(checkService.delete(vo)) {
			return FORM_VIEW;
		}else {
			req.setAttribute("check", false);
			return FORM_VIEW;
		}
	}
}
