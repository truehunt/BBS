package guestbook.command;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import command.CommandHandler;
import guestbook.service.*;
import guestbook.model.*;

public class AddGuestbookHandler implements CommandHandler {
	private static final String FORM_VIEW = "view/addGuestbook.jsp";
	private AddGuestService addGuest = new AddGuestService();

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
		String message = req.getParameter("message");
		String name = req.getParameter("guest_name");
		String pwd = req.getParameter("password");
		
		vo.setMessage(message);
		vo.setName(name);
		vo.setPassword(pwd);
		try {
			if((message!=null && message.length()!=0)
					&&(name!=null && name.length()!=0)
					&&(pwd!=null && pwd.length()!=0)) {
				addGuest.add(vo);
				return FORM_VIEW;
			}else {
				throw new Exception();
			}
		} catch (Exception e) {
			req.setAttribute("check", false);
			return FORM_VIEW;
		}
	}

}
