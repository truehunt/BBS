package dao;

import java.lang.reflect.Member;
import java.sql.Connection;
import java.sql.Date;
import java.sql.SQLException;

import javax.naming.NamingException;

import jdbc.JdbcUtil;
import jdbc.connection.ConnectionProvider;

public class ModService {
	private BoardDAO boardDAO = new BoardDAO();
	private ArticleVO articleVO = new ArticleVO();

	public ModService() {
		boardDAO = new BoardDAO();
		articleVO = new ArticleVO();
	}

	public void mod(ModRequest modReq) {
			try {
				boardDAO.update1(modReq);
				boardDAO.update2(modReq);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	}
	
	public ArticleVO viewArticle(int article_no) { // 업데이트된 글 가져오는 메소드
		ArticleVO article = null;
		article = boardDAO.selectArticle(article_no, 0);
		return article;
	}
	
}
