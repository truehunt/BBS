package guestbook.service;

import guestbook.dao.GuestbookDAO;
import guestbook.model.GuestbookVO;

public class PwdCheckService{
	private GuestbookDAO dao = new GuestbookDAO();

	public boolean delete(GuestbookVO vo) {
		boolean result = dao.PwdCheck(vo);
		if(result) {
			dao.GuestbookDelete(vo);
			return true;
		}
		return false;
	
	}
}