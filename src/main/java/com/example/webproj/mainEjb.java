package com.example.webproj;
import jakarta.ejb.Stateful;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

@Stateful
public class mainEjb {

    public ArrayList<announcement> getAnnouncements(Integer id){
        ArrayList<announcement> announcements = new ArrayList<>();
        try{
            String query="SELECT * FROM announcements WHERE idTypesFk='"+id+"'";
            Context initContext = new InitialContext();
            Context envContext = (Context) initContext.lookup("java:comp/env");
            DataSource ds = (DataSource) envContext.lookup("jdbc/web");
            Connection con = ds.getConnection();
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()){
                announcements.add(new announcement(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5), rs.getString(6), rs.getString(8)));
            }
        }catch (NamingException | SQLException ex) {
            System.err.println(ex);
        }
        return announcements;
    }

    public ArrayList<announcement> getTextInfo(Integer id) {
        ArrayList<announcement> textos = new ArrayList<>();
        try{
            String query="SELECT * FROM announcements WHERE id='"+id+"'";
            Context initContext = new InitialContext();
            Context envContext = (Context) initContext.lookup("java:comp/env");
            DataSource ds = (DataSource) envContext.lookup("jdbc/web");
            Connection con = ds.getConnection();
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()){
                textos.add(new announcement(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5), rs.getString(6), rs.getString(8)));
            }
        }catch (NamingException | SQLException ex) {
            System.err.println(ex);
        }
        return textos;
    }


    public announcement getTextInfo2(Integer id) {
        announcement texto = null;
        try{
            String query="SELECT * FROM announcements WHERE id='"+id+"'";
            Context initContext = new InitialContext();
            Context envContext = (Context) initContext.lookup("java:comp/env");
            DataSource ds = (DataSource) envContext.lookup("jdbc/web");
            Connection con = ds.getConnection();
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()){
                texto = new announcement(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5), rs.getString(6), rs.getString(8));
            }
        }catch (NamingException | SQLException ex) {
            System.err.println(ex);
        }
        return texto;
    }
    
    public ArrayList<theme> getThemes(){
        ArrayList<theme> themes= new ArrayList<>();
        try{
            String query="SELECT * FROM types";
            Context initContext = new InitialContext();
            Context envContext = (Context) initContext.lookup("java:comp/env");
            DataSource ds = (DataSource) envContext.lookup("jdbc/web");
            Connection con = ds.getConnection();
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()){
                themes.add(new theme(rs.getInt(1),rs.getString(2)));
            }
        }catch (NamingException | SQLException ex) {
            System.err.println(ex);
        }
        return themes;
    }

    public void deleteAnn(Integer id){
        try{
            String query="DELETE FROM announcements WHERE id="+id;
            Context initContext = new InitialContext();
            Context envContext = (Context) initContext.lookup("java:comp/env");
            DataSource ds = (DataSource) envContext.lookup("jdbc/web");
            Connection con = ds.getConnection();
            Statement stmt = con.createStatement();
            stmt.executeUpdate(query);
        }catch (NamingException | SQLException ex) {
            System.err.println(ex);
        }
    }

    public void alterText(Integer id, String text){
        try{
            String query="UPDATE announcements set textInfo='"+text+"' WHERE id="+id;
            Context initContext = new InitialContext();
            Context envContext = (Context) initContext.lookup("java:comp/env");
            DataSource ds = (DataSource) envContext.lookup("jdbc/web");
            Connection con = ds.getConnection();
            Statement stmt = con.createStatement();
            stmt.executeUpdate(query);
        }catch (NamingException | SQLException ex) {
            System.err.println(ex);
        }
    }

    public void createNews(announcement ann){
        try{
            if (ann.getImage() == null)
                ann.setImage("styles/images/no-image.png");
            String pause="','";
            String query="INSERT INTO announcements(header,image,type,date,content,idTypesFk,textInfo) VALUES('"+ann.getHeader()+pause+ann.getImage()+pause+ann.getType()+"',CURRENT_TIMESTAMP(),'"+ann.getContent()+pause+ann.getIdTypesFk()+pause+ann.getTextInfo()+"')";
            Context initContext = new InitialContext();
            Context envContext = (Context) initContext.lookup("java:comp/env");
            DataSource ds = (DataSource) envContext.lookup("jdbc/web");
            Connection con = ds.getConnection();
            Statement stmt = con.createStatement();
            stmt.executeUpdate(query);
        }catch (NamingException | SQLException ex) {
            System.err.println(ex);
        }
    }

    public ArrayList<announcement> searchMe(String value){
        ArrayList<announcement> announcements = new ArrayList<>();
        ArrayList<announcement> result = new ArrayList<>();
        try{
            String query="SELECT * FROM announcements";
            Context initContext = new InitialContext();
            Context envContext = (Context) initContext.lookup("java:comp/env");
            DataSource ds = (DataSource) envContext.lookup("jdbc/web");
            Connection con = ds.getConnection();
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()){
                announcements.add(new announcement(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5), rs.getString(6), rs.getString(8)));
            }
            for (announcement x:
                 announcements) {
                if (x.getHeader().contains(value) || x.getContent().contains(value) || x.getTextInfo().contains(value) || x.getType().contains(value) || x.getDate().contains(value))
                    result.add(x);
            }
        }catch (NamingException | SQLException ex) {
            System.err.println(ex);
        }
        return result;
    }

    public ArrayList<announcement> getNews(){
        ArrayList<announcement> announcements = new ArrayList<>();
        try{
            String query="SELECT * FROM announcements ORDER BY date DESC LIMIT 5";
            Context initContext = new InitialContext();
            Context envContext = (Context) initContext.lookup("java:comp/env");
            DataSource ds = (DataSource) envContext.lookup("jdbc/web");
            Connection con = ds.getConnection();
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()){
                announcements.add(new announcement(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5), rs.getString(6), rs.getString(8)));
            }
        }catch (NamingException | SQLException ex) {
            System.err.println(ex);
        }
        return announcements;
    }





    public ArrayList<comment> getComments(Integer id){
        ArrayList<comment> comments=new ArrayList<>();
        try{
            String query="SELECT commentBody, commDate, u.username, u.admin, idComments FROM web.comments c LEFT JOIN web.announcements a ON c.idAnn_FK = a.id " +
                    "LEFT JOIN web.users u ON c.idUser_FK = u.idUsers WHERE a.id="+id;
            Context initContext = new InitialContext();
            Context envContext = (Context) initContext.lookup("java:comp/env");
            DataSource ds = (DataSource) envContext.lookup("jdbc/web");
            Connection con = ds.getConnection();
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()){
                comments.add(new comment(rs.getInt(5),rs.getString(1),rs.getString(2),rs.getString(3), rs.getBoolean(4)));
            }
        }catch (NamingException | SQLException ex) {
            System.err.println(ex);
        }
        return comments;
    }

    public void deleteComment(Integer id){
        try{
            String query="DELETE FROM comments WHERE idComments="+id;
            Context initContext = new InitialContext();
            Context envContext = (Context) initContext.lookup("java:comp/env");
            DataSource ds = (DataSource) envContext.lookup("jdbc/web");
            Connection con = ds.getConnection();
            Statement stmt = con.createStatement();
            stmt.executeUpdate(query);
        }catch (NamingException | SQLException ex) {
            System.err.println(ex);
        }
    }

    public void newComment(Integer id, String text, Integer userId){
        try{
            String pause="','";
            String query="INSERT INTO comments(commentBody, commDate,idAnn_FK, idUser_FK) VALUES('"+text+"',CURRENT_TIMESTAMP(),'"+id+pause+userId+"')";
            Context initContext = new InitialContext();
            Context envContext = (Context) initContext.lookup("java:comp/env");
            DataSource ds = (DataSource) envContext.lookup("jdbc/web");
            Connection con = ds.getConnection();
            Statement stmt = con.createStatement();
            stmt.executeUpdate(query);
        }catch (NamingException | SQLException ex) {
            System.err.println(ex);
        }
    }

    public Integer commentPageCount(Integer id){

        try{
            String query="SELECT count(*) FROM comments c LEFT JOIN announcements a ON c.idAnn_FK = a.id WHERE a.id="+id;
            Context initContext = new InitialContext();
            Context envContext = (Context) initContext.lookup("java:comp/env");
            DataSource ds = (DataSource) envContext.lookup("jdbc/web");
            Connection con = ds.getConnection();
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            rs.next();
            return rs.getInt(1);

        }catch (NamingException | SQLException ex) {
            System.err.println(ex);
        }
        return 0;
    }
}
