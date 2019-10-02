package member.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Map;

import javax.naming.NamingException;

import member.dao.MemberDao;
import member.model.Member;


public class LoginRequest {
	private String id;
	private String password;
	
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getId() {
		return id;
	}
	
	public void setId(String id) {
		this.id = id;	
	}
	
	//패스워드 일치 여부

	   public void validate(Map<String, Boolean> errors) throws SQLException{
	      checkEmpty(errors, id, "id");
	      checkEmpty(errors, password, "password");
	      
	      
	   }
   
	
	   private void checkEmpty(Map<String, Boolean> errors, 
	         String value, String fieldName) {
	      if (value == null || value.isEmpty())
	         errors.put(fieldName, Boolean.TRUE);
	   }
	
	

}
