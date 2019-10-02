package dao;

import java.util.List;

public class RemoveService {
	
	private BoardDAO boardDAO = new BoardDAO();
	private ArticleVO articleVO = new ArticleVO();

	public RemoveService() {
		boardDAO = new BoardDAO();
		articleVO = new ArticleVO();
	}
	
//	public boolean chk(int article_no, String password) {
//		boolean result = boardDAO.PwdCheck(article_no, password);
//		return result;
//	}
	
	public ArticleVO deleteArticle(int article_no) {
		ArticleVO article = null;
		article = boardDAO.deleteArticle1(article_no);
		article = boardDAO.deleteArticle2(article_no);
		return article;
	}

}
