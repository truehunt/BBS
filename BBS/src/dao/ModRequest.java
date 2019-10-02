package dao;

import java.sql.Date;


public class ModRequest {
	
	private int article_no; // 게시글 번호
	private String title; // 제목
	private Date moddate; // 수정일시
	
	private String content; // 게시내용
	
	 private String password; // 게시글 비밀번호

	 
	 

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public int getArticle_no() {
		return article_no;
	}

	public void setArticle_no(int article_no) {
		this.article_no = article_no;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Date getModdate() {
		return moddate;
	}

	public void setModdate(Date moddate) {
		this.moddate = moddate;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

}
