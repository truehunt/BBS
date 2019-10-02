package dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;


import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import jdbc.JdbcUtil;
import jdbc.connection.ConnectionProvider;
import member.model.Member;

public class BoardDAO {
	private DataSource dataFactory;
	Connection conn;
	PreparedStatement pstmt;
	
	private static BoardDAO instance = new BoardDAO();

	public static BoardDAO getInstance() {
		if (instance == null)              
		   instance = new BoardDAO();
		return instance;
	}

	public BoardDAO() {
		try {
			Context ctx = new InitialContext();
			Context envContext = (Context) ctx.lookup("java:/comp/env");
			dataFactory = (DataSource) envContext.lookup("jdbc/oracle");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public ArticleVO selectArticle(int article_no, int i){ // 게시글 상세 정보 조회 메소드
		ArticleVO article=new ArticleVO();
		try{
		conn = dataFactory.getConnection();
		
		if(i!=-1) { //조회수 증가 메소드
			String query = "update article set read_cnt = read_cnt+1 where article_no = " + article_no;
			System.out.println(query);
			pstmt = conn.prepareStatement(query);
			pstmt.executeUpdate();
		}
		
		String query ="select a.article_no, a.writer_id, a.writer_name, a.title, ac.content, a.regdate, a.moddate, a.read_cnt"
			                     +" from article a, article_content ac" 
			                     +" where a.article_no = ac.article_no"
								 +" and a.article_no=?";
		System.out.println(query);
		pstmt = conn.prepareStatement(query);
		pstmt.setInt(1, article_no);
		ResultSet rs =pstmt.executeQuery();
		rs.next();
		int _article_no =rs.getInt("article_no");
		String writer_id = rs.getString("writer_id");
		String writer_name = rs.getString("writer_name");
		String title = rs.getString("title");
		Date regdate = rs.getDate("regdate");
		Date moddate = rs.getDate("moddate");
		int read_cnt = rs.getInt("read_cnt");
		String content = rs.getString("content");

		article.setArticle_no(_article_no);
		article.setWriter_id(writer_id);
		article.setWriter_name(writer_name);
		article.setTitle(title);
		article.setRegdate(regdate);
		article.setModdate(moddate);
		article.setRead_cnt(read_cnt);
		article.setContent(content);
		rs.close();
		pstmt.close();
		conn.close();
		}catch(Exception e){
		e.printStackTrace();	
		}
		return article;
		}
	
	public boolean PwdCheck(int article_no, String password) { // 게시글 수정, 삭제시 확인 - passChkProc.jsp 에서 사용
		String pwd="";
		boolean result = false;
		try {
			conn = dataFactory.getConnection();
			String query = "select password from article";
				query += " where article_no='"+article_no+"'";
			pstmt = conn.prepareStatement(query);
			System.out.println("prepareStatement:" + query);
			ResultSet rs = pstmt.executeQuery();
			rs.next();
			pwd = rs.getString("password");
			System.out.println(pwd);
			rs.close();
			pstmt.close();
			conn.close();
		}catch(Exception e){
			e.printStackTrace();
		}
		result = password.equals(pwd);
		System.out.println("password : "+password + "//pwd : "+pwd);
		System.out.println("result결과 : "+result);
		return result;
	}
	
	public void update1(ModRequest modReq) throws SQLException { // 게시글 제목, 수정일시 수정 메소드
		conn = dataFactory.getConnection();
		try {
			conn = dataFactory.getConnection();
			String query2 = "update article set title='" + modReq.getTitle() + "'";
			query2 += ", moddate=?";
			query2 += " where article_no=" + modReq.getArticle_no() ;

			System.out.println(query2);
			pstmt = conn.prepareStatement(query2);
			pstmt.setTimestamp(1, new java.sql.Timestamp(System.currentTimeMillis()));
			pstmt.executeUpdate();
			pstmt.close();
			conn.close();
			} catch (Exception e) {
			e.printStackTrace();
		}
				
	}
	
	public void update2(ModRequest modReq) throws SQLException { // 게시글 내용 수정 메소드
		try {
			conn = dataFactory.getConnection();
			String query1 = "update article_content set content='" + modReq.getContent() + "'";
			query1 += " where article_no=" + modReq.getArticle_no() ;
	
			System.out.println(query1);
			pstmt = conn.prepareStatement(query1);
			pstmt.executeUpdate();
			pstmt.close();
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	public ArticleVO deleteArticle1(int article_no) { // 게시글 제목삭제 메소드
		ArticleVO article = new ArticleVO();
		try {
			conn = dataFactory.getConnection();
			String query1 = "DELETE FROM article WHERE article_no=" + article_no;
			System.out.println(query1);
			pstmt = conn.prepareStatement(query1);
			pstmt.executeUpdate();
			pstmt.close();
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return article;
	}
	
	public ArticleVO deleteArticle2(int article_no) { // 게시글 내용삭제 메소드
		ArticleVO article = new ArticleVO();
		try {
			conn = dataFactory.getConnection();
			String query2 = "DELETE FROM article_content WHERE article_no=" + article_no;
			System.out.println(query2);
			pstmt = conn.prepareStatement(query2);
			pstmt.executeUpdate();
			pstmt.close();
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return article;
	}
	
}
