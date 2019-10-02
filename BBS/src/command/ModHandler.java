package command;

import java.io.UnsupportedEncodingException;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.ArticleVO;
import dao.BoardDAO;
import dao.ModRequest;
import dao.ModService;
import dao.ViewRequest;

public class ModHandler implements CommandHandler {
	
	private static final String FORM_VIEW = "/contentalter.jsp";
	private ModService modService = new ModService();
	ArticleVO articleVO;
	ModRequest modReq;

	@Override
	public String process(HttpServletRequest req, HttpServletResponse res) {
			return processSubmit(req, res);
	}

	private String processSubmit(HttpServletRequest req, HttpServletResponse res) {
		
		System.out.println("modHandler 실행");
		try {
			req.setCharacterEncoding("utf-8");
		} catch (UnsupportedEncodingException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		ModRequest modReq = new ModRequest();
		modReq.setArticle_no(Integer.parseInt(req.getParameter("article_no")));
		modReq.setTitle(req.getParameter("title"));
		modReq.setContent(req.getParameter("content"));
		
        
        String article_no = req.getParameter("article_no");
        
        try {
    			article_no = req.getParameter("article_no");
				modService.mod(modReq);
				articleVO = modService.viewArticle(Integer.parseInt(article_no));
				req.setAttribute("article", articleVO);
				req.setAttribute("article_no", article_no);
        
		} catch (Exception e) {
			e.printStackTrace();
		}
        
        return "/contentdetail.jsp?article_no=" + article_no;
		
	}

}
