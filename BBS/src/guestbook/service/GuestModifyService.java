package guestbook.service;

import guestbook.dao.GuestbookDAO;
import guestbook.model.GuestbookVO;

public class GuestModifyService {
	private GuestbookDAO dao = new GuestbookDAO();

	public boolean modifyCheck(GuestbookVO vo) {
		boolean result = dao.PwdCheck(vo);
		if(result) {
			return true;
		}
		return false;
	}
	
	public boolean modify(GuestbookVO vo) {
		boolean result = dao.Modified(vo);
		if(result) {
			return true;
		}
		return false;
	}
	public GuestbookVO selectById(String message_id) {
		GuestbookVO vo = dao.SelectById(message_id);
		return vo;
	}
	
}
