package member.model;

import java.util.Date;

public class Member {

	private String id;
	private String name;
	private String password;
	private Date regDate;
	private String new_pwd;
	
	public Member(String id, String name, String password, Date regDate) {
		this.id = id;
		this.name = name;
		this.password = password;
		this.regDate = regDate;
	}
		
	public Member(String id, String password) {
		this.id = id;
		this.password = password;
	}
		
	public Member(String id, String password, String new_pwd) {
		super();
		this.id = id;
		this.password = password;
		this.new_pwd = new_pwd;
	}

	public Member() {
		
	}
	
	
	
	
	public void setName(String name) {
		this.name = name;
	}

	public Member(String name) {
		super();
		this.name = name;
	}

	public String getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public String getPassword() {
		return password;
	}
	
	public Date getRegDate() {
		return regDate;
	}
	
	public String getNewPwd() {
		return new_pwd;
	}
	
	public boolean matchPassword(String pwd) {
		return password.equals(pwd);
	}

	public void changePassword(String newPwd) {
		this.password = newPwd;
	}

}
