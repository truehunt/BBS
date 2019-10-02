package guestbook.dao;

import java.lang.Character.Subset;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import guestbook.model.GuestbookVO;

public class GuestbookDAO {

	private Connection con;
	private PreparedStatement pstmt;
	private DataSource dataFactory;
	
	public GuestbookDAO() {
		try {
			Context ctx = new InitialContext();
			Context envContext = (Context)ctx.lookup("java:/comp/env");
			dataFactory = (DataSource) envContext.lookup("jdbc/oracle");
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public void addGuestbook(GuestbookVO vo) {
		try {
			con = dataFactory.getConnection();
			String messageId = vo.getMessageId();
			String password = vo.getPassword();
			String name = vo.getName();
			String message = vo.getMessage();
			System.out.println("message ID : "+messageId);
			String query = "insert into guestbook_message";
			query += " (message_id, password, guest_name, message)";
			query += " values(?,?,?,?)";
			System.out.println("prepareStatement : " + query);
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, messageId);
			pstmt.setString(2, password);
			pstmt.setString(3, name);
			pstmt.setString(4, message);
			pstmt.executeUpdate();
			pstmt.close();
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public List GuestList(int firstRow, int endRow) {
		List list = new ArrayList();
		try {
			con = dataFactory.getConnection();
			String query = "select * ";
				query += " from (select rownum rnum, a.*";
				query += " from guestbook_message a order by message_id desc) ";
				query += "where rnum >= ? and rnum <= ? ";
			pstmt = con.prepareStatement(query);
			pstmt.setInt(1, firstRow);
			pstmt.setInt(2, endRow);
			
			System.out.println("prepareStatement:" + query);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				String message_id = rs.getString("message_id");
				String password = rs.getString("password");
				String guest_name = rs.getString("guest_name");
				String message = rs.getString("message");
				GuestbookVO vo = new GuestbookVO();
				vo.setMessageId(message_id);
				vo.setPassword(password);
				vo.setName(guest_name);
				vo.setMessage(message);
				list.add(vo);
			}
			rs.close();
			pstmt.close();
			con.close();
		}catch(Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	
	public GuestbookVO SelectById(String id) {
		GuestbookVO vo = new GuestbookVO();
		try {
			con = dataFactory.getConnection();
			String query = "select * ";
				query += " from guestbook_message";
				query += " where message_id=?";
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, id);
			System.out.println("prepareStatement:" + query);
			ResultSet rs = pstmt.executeQuery();
			rs.next();
			String message_id = rs.getString("message_id");
			String password = rs.getString("password");
			String guest_name = rs.getString("guest_name");
			String message = rs.getString("message");
			vo.setMessageId(message_id);
			vo.setPassword(password);
			vo.setName(guest_name);
			vo.setMessage(message);
			rs.close();
			pstmt.close();
			con.close();
		}catch(Exception e) {
			e.printStackTrace();
		}
		return vo;
	}
	
	public void GuestbookDelete(GuestbookVO vo) {
		String message_id = vo.getMessageId();
		try {
			System.out.println("delete 메소드 실행 : " + message_id);
			con = dataFactory.getConnection();
			String query = "delete from guestbook_message";
				query += " where message_id='"+message_id+"'";
			System.out.println("prepareStatement : "+  query);
			pstmt = con.prepareStatement(query);
			pstmt.executeUpdate();
			pstmt.close();
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public boolean PwdCheck(GuestbookVO vo) {	
		String id = vo.getMessageId();
		String delPwd = vo.getPassword();
		String pwd="";
		boolean result = false;
		try {
			con = dataFactory.getConnection();
			String query = "select password from guestbook_message";
				query += " where message_id='"+id+"'";
			pstmt = con.prepareStatement(query);
			System.out.println("prepareStatement:" + query);
			ResultSet rs = pstmt.executeQuery();
			rs.next();
			pwd = rs.getString("password");
			rs.close();
			pstmt.close();
			con.close();
		}catch(Exception e){
			e.printStackTrace();
		}
		result = delPwd.equals(pwd);
		System.out.println("delpwd : "+delPwd + "//pwd : "+pwd);
		System.out.println("result결과 : "+result);
		return result;
	}
	
	public int GuestbookMax() {
		System.out.println("guestbookmax실행");
		String max = "0";
		try {
			con = dataFactory.getConnection();
			String query = "select MAX(message_id) as max from guestbook_message";
			pstmt = con.prepareStatement(query);
			System.out.println("prepareStatement:" + query);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				max = rs.getString("max");
			}
			rs.close();
			pstmt.close();
			con.close();
			return Integer.parseInt(max)+1;
		}catch(Exception e) {
			return 1;
		}
	}
	
	public int GuestbookNum() {
		String num = "0";
		try {
			con = dataFactory.getConnection();
			String query = "select COUNT(message_id) as num "
					+ "from guestbook_message";
			pstmt = con.prepareStatement(query);
			System.out.println("prepareStatement:" + query);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				num = rs.getString("num");
			}
			rs.close();
			pstmt.close();
			con.close();
		}catch(Exception e) {
			e.printStackTrace();
		}
		return Integer.parseInt(num);
	}
	
	public boolean Modified(GuestbookVO vo) {
		String id = vo.getMessageId();
		String message = vo.getMessage();
		String name = vo.getName();
		String password = vo.getPassword();
		try {
			con = dataFactory.getConnection();
			String query = "UPDATE guestbook_message SET";
			if((name!=null)&&(name!="")) {
				query +=" guest_name='"+name+"',";
			}if((message!=null)&&(message!="")) {
				query +=" message='"+message+"',";
			}if((password!=null)&&(password!="")) {
				query +=" password='"+password+"',";
			}
				query = query.substring(0, query.length()-1);
				query += " WHERE message_id='"+id+"'";
			System.out.println("prepareStatement : " + query);
			pstmt = con.prepareStatement(query);
			pstmt.executeUpdate();
			pstmt.close();
		}catch(Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}
	
}
