package guestbook.command;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import command.CommandHandler;
import guestbook.service.*;
import guestbook.model.*;


public class ModifyHandler implements CommandHandler{
	private static final String FORM_VIEW = "view/modifyResult.jsp";
	private GuestModifyService checkService = new GuestModifyService();
	
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
		vo.setMessage(req.getParameter("message"));
		vo.setPassword(req.getParameter("password"));
		vo.setName(req.getParameter("guest_name"));
		System.out.println(vo.getName());
		if(checkService.modify(vo)) {
			return FORM_VIEW;
		}else {
			req.setAttribute("check", false);
			return FORM_VIEW;
		}
	}
}
