package guestbook.service;


import guestbook.dao.GuestbookDAO;
import guestbook.model.GuestbookVO;


public class AddGuestService{
	private GuestbookDAO dao = new GuestbookDAO();

	public void add(GuestbookVO vo) {
		String maxNum =Integer.toString(dao.GuestbookMax());
		vo.setMessageId(maxNum);
		dao.addGuestbook(vo);
	}
}