package com.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import com.domain.Article;
import com.domain.Article2;
//import com.util.StringUtil;

public class ArticleDao {

    private static ArticleDao instance =new ArticleDao();
    Article ac = new Article();

    //�̱��� ����
    private ArticleDao(){}
    public static ArticleDao getInstance(){
        return instance;
    }


    //getConnection : JDBC DB���� 
    private Connection getConnection() throws Exception{
        Context initCtx= new InitialContext();
        Context envCtx=(Context)initCtx.lookup("java:comp/env");
        DataSource ds=(DataSource)envCtx.lookup("jdbc/oracle");

        return ds.getConnection();
    }

    //Article 테이블에 넣기
    public void insertArticle1(Article2 article)throws Exception{
    	

        Connection conn= null;
        PreparedStatement pstmt = null;
        String sql=null;
        int cnt = 0;
        try{
            conn= getConnection();
			sql = "insert into ARTICLE (article_no, writer_id, writer_name, title, moddate, regdate, read_cnt, password) " 
                + "values(article_no_seq.nextval,1,?,?,sysdate,sysdate,0,?)";
            
            System.out.println("query:"+sql);
            pstmt = conn.prepareStatement(sql);
            System.out.println(article.getTitle());
            System.out.println(article.getWriter_name());
            System.out.println(article.getPassword());
            pstmt.setString(++cnt, article.getWriter_name());
            pstmt.setString(++cnt, article.getTitle());
            pstmt.setString(++cnt, article.getPassword());
            pstmt.executeUpdate();
        }catch(Exception e){
            e.printStackTrace();
        }finally{
            execClose(null,pstmt,conn);
        }
    }
    
    //Article_content 테이블에 넣기
    public void insertArticle2(Article2 article)throws Exception{

        Connection conn= null;
        PreparedStatement pstmt = null;
        String sql2=null;
        int cnt = 0;        

        try{
            conn= getConnection();
            sql2 = "insert into ARTICLE_content (article_no, content) " 
                    + "values(article_no_seq.currval,?)";
            System.out.println("query:"+sql2);
            pstmt = conn.prepareStatement(sql2);
            pstmt.setString(++cnt, article.getContent());             
            pstmt.executeUpdate();
        }catch(Exception e){
            e.printStackTrace();
        }finally{
            execClose(null,pstmt,conn);
        }
    }
    
    
    //�۰���
    public int getArticleCount(String keyField,String keyWord)throws Exception{
        Connection conn= null;
        PreparedStatement pstmt =null;
        ResultSet rs= null;
        int count =0;
        String sql =null;
        
        try{
            conn=getConnection();
            if(keyWord == null || "".equals(keyWord.trim())){
                sql="select count(*) from ARTICLE";
                pstmt =conn.prepareStatement(sql);
            }else{
                sql="select count(*) from ARTICLE where "+keyField+" like ?";
                pstmt =conn.prepareStatement(sql);
                pstmt.setString(1, "%"+keyWord+"%");
            }
            rs =pstmt.executeQuery();
            if (rs.next()){
                count =rs.getInt(1);
            }
        }catch(Exception ex){
            ex.printStackTrace();
        }finally{
            execClose(rs,pstmt,conn);
        }            
        return count;
    }
    
    //����Ʈ�̱�
    public List<Article> getArticles(int startRow, int endRow, String keyField,String keyWord)throws Exception{
        Connection conn= null;
        PreparedStatement pstmt =null;
        ResultSet rs= null;
        List<Article> list =null;
        String sql=null;
        
        try{
            conn =getConnection();
            if(keyWord == null || "".equals(keyWord.trim())){
                sql ="select * from (select a.*, rownum rnum from (select * from ARTICLE order by article_no desc)a) where rnum >=? and rnum <=?";
                pstmt =conn.prepareStatement(sql);            
                pstmt.setInt(1, startRow);
                pstmt.setInt(2, endRow);    
            }else{
                sql ="select * from(select a.*, rownum rnum from(select * from ARTICLE where "+keyField+" like ? order by article_no desc)a) where rnum >=? and rnum <=?";
                pstmt =conn.prepareStatement(sql);    
                pstmt.setString(1, "%"+keyWord+"%");
                pstmt.setInt(2, startRow);
                pstmt.setInt(3, endRow);
            }
            rs = pstmt.executeQuery();
            if(rs.next()){
                list= new ArrayList<Article>();                
                do{
                    Article article =new Article();
                    article.setArticle_no(rs.getInt("article_no"));
                    article.setTitle(rs.getString("title"));
                    article.setWriter_name(rs.getString("writer_name"));                                     
                    article.setRead_cnt(rs.getInt("read_cnt"));
                    article.setRegdate(rs.getTimestamp("regdate"));
                  
                    list.add(article);
                }while(rs.next());
            }else{
                list = Collections.EMPTY_LIST;
            }            
        }catch(Exception ex){
            ex.printStackTrace();
        }finally{
            execClose(rs,pstmt,conn);
        }        
        return list;
    }
    
    //��������
//    public Article getArticle(int num)throws Exception{
//        Connection conn =null;
//        PreparedStatement  pstmt= null;
//        ResultSet rs = null;
//        Article article =null;
//        String sql=null;
//        
//        try{
//            conn=getConnection();
//            sql ="select * from ARTICLE where article_no = ? ";
//
//            pstmt=conn.prepareStatement(sql);
//            pstmt.setInt(1, num);
//            rs=pstmt.executeQuery();
//            
//            if(rs.next()){
//                article =new Article();
//                article.setArticle_no(rs.getInt("article_no"));
//                article.setWriter_id(rs.getString("writer_id"));
//                article.setWriter_name(rs.getString("writer_name"));
//                article.setTitle(rs.getString("title"));
//                article.setRegdate(rs.getTimestamp("regdate"));
//                article.setModdate(rs.getTimestamp("moddate"));                   
//                article.setRead_cnt(rs.getInt("read_cnt"));
//                article.setContent(StringUtil.clobToString(rs,"content"));
//            }
//        }catch(Exception ex){
//            ex.printStackTrace();
//        }finally{
//            execClose(rs,pstmt,conn);
//        }
//        return article;
//    }

    //execClose : �ڿ�����
    public void execClose(ResultSet rs, PreparedStatement pstmt, Connection conn)throws Exception{
        if(rs !=null) try{rs.close();}catch(SQLException sqle){}
        if(pstmt !=null) try{pstmt.close();}catch(SQLException sqle){}
        if(conn !=null) try{conn.close();}catch(SQLException sqle){}
    }
}