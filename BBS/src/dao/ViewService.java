package dao;

import java.sql.Connection;
import java.sql.SQLException;

import javax.naming.NamingException;

import jdbc.JdbcUtil;
import jdbc.connection.ConnectionProvider;

public class ViewService {
	private BoardDAO boardDAO = new BoardDAO();
	private ArticleVO articleVO = new ArticleVO();

	public ViewService() {
		boardDAO = new BoardDAO();
		articleVO = new ArticleVO();
	}
	
	// 글 상세정보
	public ArticleVO viewArticle(int article_no) {
		ArticleVO article = null;
		article = boardDAO.selectArticle(article_no, 0);
		return article;
	}

}
