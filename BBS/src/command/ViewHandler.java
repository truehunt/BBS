package command;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.ArticleVO;
import dao.BoardDAO;
import dao.ViewRequest;
import dao.ViewService;

public class ViewHandler implements CommandHandler {
	
	private static final String FORM_VIEW = "/contentdetail.jsp";
	private ViewService viewService = new ViewService();
	ArticleVO articleVO;
	ViewRequest viewReq;
	
	@Override
	public String process(HttpServletRequest req, HttpServletResponse res) {
			return processSubmit(req, res);
	}

	private String processSubmit(HttpServletRequest req, HttpServletResponse res) {
		
		System.out.println("viewHandler 실행");
		
		try {
			req.setCharacterEncoding("utf-8");
		} catch (UnsupportedEncodingException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		res.setContentType("text/html; charset=utf-8");
		String action = req.getPathInfo();
		System.out.println("action:" + action);
		
		String article_no = req.getParameter("article_no");
		
		try {
			List<ArticleVO> articlesList = new ArrayList<ArticleVO>();
			
				article_no = req.getParameter("article_no");
				articleVO = viewService.viewArticle(Integer.parseInt(article_no));
				req.setAttribute("article", articleVO);
				req.setAttribute("article_no", article_no);
		} catch (Exception e) {
			e.printStackTrace();
		}
        
        return "/contentdetail.jsp?article_no='" + article_no + "'";
		
	}

}


