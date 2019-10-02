package command;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.ArticleVO;
import dao.BoardDAO;
import dao.ModRequest;
import dao.ViewRequest;
import dao.RemoveService;

public class RemoveHandler implements CommandHandler {
	
	private static final String FORM_VIEW = "/delProc.jsp";
	private RemoveService removeService = new RemoveService();
	ArticleVO articleVO;
	ModRequest modReq;

	@Override
	public String process(HttpServletRequest req, HttpServletResponse res) {
			return processSubmit(req, res);
	}

	private String processSubmit(HttpServletRequest req, HttpServletResponse res) {
		
		System.out.println("removeHandler 실행");
		
		try {
			req.setCharacterEncoding("utf-8");
		} catch (UnsupportedEncodingException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		int article_no = Integer.parseInt(req.getParameter("article_no"));
        
			
        try {
				articleVO = removeService.deleteArticle(article_no);
				req.setAttribute("article", articleVO);
				req.setAttribute("article_no", article_no);
		} catch (Exception e) {
			e.printStackTrace();
		}
        
        req.setAttribute("article_no", article_no);
        
        return "/list.do";
			
	}

}
