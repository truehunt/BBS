package guestbook.dao;


import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import guestbook.model.GuestbookVO;

public class MessageList {
	private static final int MESSAGE_COUNT_PER_PAGE = 3;
	GuestbookDAO dao = new GuestbookDAO();
	
	public List message(int pageNumber) {
		List list = new ArrayList();
		int messageTotalCount = dao.GuestbookNum();
		int currentPageNumber = pageNumber;
		try {
			int firstRow = 0;
			int endRow = 0;
			if (messageTotalCount > 0) {
				firstRow = (pageNumber - 1) * MESSAGE_COUNT_PER_PAGE + 1;
				endRow = firstRow + MESSAGE_COUNT_PER_PAGE - 1;
				list = dao.GuestList(firstRow, endRow);
			} else {
				currentPageNumber = 0;
				list = Collections.emptyList();
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	
	public int pageTotalCount() {
		int messageTotalCount = dao.GuestbookNum();
		int pageNum = messageTotalCount / 3;
		if(pageNum == 0) {
			return 1;
		}else if((pageNum !=0) && (messageTotalCount%3==0)) {
			return pageNum;
		}else {
			return pageNum+1;
		}
	}
}
