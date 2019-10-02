package com.action;

import java.io.UnsupportedEncodingException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.dao.ArticleDao;
import com.domain.Article2;

import command.CommandHandler;
import member.model.Member;


public class WriteProAction  implements CommandHandler{

	@Override
	public String process(HttpServletRequest req, HttpServletResponse res) throws Exception {
			return processSubmit(req, res);
	}

	private String processSubmit(HttpServletRequest req, HttpServletResponse res) throws Exception {

    	 req.setCharacterEncoding("utf-8");
        
        Article2 article = new Article2();
        
        article.setWriter_id(req.getParameter("writer_id")); 
        article.setWriter_name(req.getParameter("name")); 
        article.setTitle(req.getParameter("title"));   
        article.setContent(req.getParameter("content")); 
        article.setPassword(req.getParameter("password"));

        ArticleDao manager = ArticleDao.getInstance();
        
        manager.insertArticle1(article);
        manager.insertArticle2(article);
       
        return "/view/writePro.jsp";
    }
}