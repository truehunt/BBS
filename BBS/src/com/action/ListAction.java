package com.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.dao.ArticleDao;
import com.domain.Article;
import com.domain.ArticlePage;

import command.CommandHandler;


public class ListAction implements CommandHandler{
    
	@Override
	public String process(HttpServletRequest req, HttpServletResponse res) throws IOException {
			return processSubmit(req, res);
	}

	private String processSubmit(HttpServletRequest req, HttpServletResponse res) throws IOException {
    
    try {
		req.setCharacterEncoding("utf-8");
	} catch (UnsupportedEncodingException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
    
    HttpSession session = req.getSession();
    if(session.getAttribute("isLogon")==null) {
       return "/main.jsp";
    }else {
       boolean isLogon = (boolean)session.getAttribute("isLogon");
       if(!isLogon) {
          return "/main.jsp";
       }
    }
//    String result = (String) session.getAttribute("isLogon");
//	String user_id = req.getParameter("user_id");
//	String user_pw = req.getParameter("user_pw");
//	if(session.isNew()) {
//		//로그인창에서 서브릿으로 요청했다면 id가 null이 아니므로 세션에 id를 바인딩합니다.
//		if(user_id !=null) {
//			session.setAttribute("user_id", user_id);
//			String url=res.encodeURL("login"); // 변수 url에 encodeURL()을 이용해 응답 시 미리 jsessionId를 저장합니다.
//			out.println("<a href='login'>로그인 상태 확인</a>"); // 로그인 상태 확인 클릭시 jsessionID를 서블릿으로 다시 전송.
//		} else {
//			out.println("<a href='login2.html'>다시 로그인 하세요!!</a>");			
//			session.invalidate();
//		}	
//	} else { 
//		//재요청시 세션에서 id를 가져와 이전에 로그인했는지 여부를 확인.
//		user_id = (String)session.getAttribute("user_id");
//		if(user_id !=null && user_id.length() !=0) {
//			out.println("안녕하세요 " + user_id + "님!!!");
//		} else {
//			out.println("<a href='login2.html'>다시 로그인 하세요!!</a>");			
//			session.invalidate();
//		}
//	}
    
//	  HttpSession session = req.getSession();
//	  Boolean result = false;
//	  result = (Boolean) session.getAttribute("isLogon");
//	  System.out.println(session.getAttribute("isLogon"));
//	  if(result) {
//	  	return "/main.jsp";
//	  } else if (!result){
//		  return "/list.do";
//	  }
    
//    String member_id = req.getParameter("member_id");
//    
//    if(member_id==null) {
//    	return "/main.jsp";
//    }
    

    String keyField =req.getParameter("keyField");
    String keyWord =req.getParameter("keyWord");
    if(keyField==null){
        keyField="";
    }
    if(keyWord==null){
        keyWord="";
    }    
    
    String pageNum =req.getParameter("pageNum");
    
    if(pageNum ==null){
        pageNum = "1";
    }    
    
    int pageSize = 10;
    int currentPage = Integer.parseInt(pageNum);
    int startRow =(currentPage-1)*pageSize +1;
    int endRow =currentPage * pageSize;
    int count = 0;
    int number = 0;
    
    List<Article> articleList =null;
    ArticleDao manager = ArticleDao.getInstance();
    try {
		count =manager.getArticleCount(keyField,keyWord);
	} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
    
    if(count>0){
       try {
		articleList = manager.getArticles(startRow, endRow, keyField, keyWord);
	} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
    }
    //가짜 글번호
    number=count-(currentPage-1)*pageSize;

    ArticlePage page= new ArticlePage();
    page.setCount(count);
    page.setCurrentPage(currentPage);
    page.setNumber(number);
    page.setPageSize(pageSize);    
    page.setKeyField(keyField);    
    page.setKeyWord(keyWord);    
    
    req.setAttribute("page", page);
    req.setAttribute("articleList", articleList);

    return "/view/list.jsp";
    }
}