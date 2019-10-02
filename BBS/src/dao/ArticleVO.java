package dao;

import java.sql.Date;

public class ArticleVO {
	// article 테이블
	private int article_no; // 게시글 번호
	private String writer_id; // 등록자 Id
	private String writer_name; // 등록자 이름
	private String title; // 제목
	private Date regdate; // 등록일시
	private Date moddate; // 수정일시
	private int read_cnt; // 조회수 count
	
	// article_content 테이블
	private String content; // 게시내용
	
	public ArticleVO() {
		
	}

	public ArticleVO(int article_no, String writer_id, String writer_name, String title, Date regdate, Date moddate,
			int read_cnt, String content) {
		this.article_no = article_no;
		this.writer_id = writer_id;
		this.writer_name = writer_name;
		this.title = title;
		this.regdate = regdate;
		this.moddate = moddate;
		this.read_cnt = read_cnt;
		this.content = content;
	}

	public int getArticle_no() {
		return article_no;
	}

	public void setArticle_no(int article_no) {
		this.article_no = article_no;
	}

	public String getWriter_id() {
		return writer_id;
	}

	public void setWriter_id(String writer_id) {
		this.writer_id = writer_id;
	}

	public String getWriter_name() {
		return writer_name;
	}

	public void setWriter_name(String writer_name) {
		this.writer_name = writer_name;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Date getRegdate() {
		return regdate;
	}

	public void setRegdate(Date regdate) {
		this.regdate = regdate;
	}

	public Date getModdate() {
		return moddate;
	}

	public void setModdate(Date moddate) {
		this.moddate = moddate;
	}

	public int getRead_cnt() {
		return read_cnt;
	}

	public void setRead_cnt(int read_cnt) {
		this.read_cnt = read_cnt;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

}
