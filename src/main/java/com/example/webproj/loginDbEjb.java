package com.example.webproj;

import jakarta.ejb.Stateful;
import javax.sql.DataSource;
import javax.swing.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.Objects;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
@Stateful
public class loginDbEjb {
    public static boolean authorized = false;
    public static boolean admin = false;
    public static boolean showUsers=false;
    private static String username;
    private static String password;
    public String setUser(String username, String password){
        try {
            String query = "INSERT INTO users(username,password,admin,blocked) VALUES('" + username + "','" + password+"','0','0')";
            Context initContext = new InitialContext();
            Context envContext = (Context) initContext.lookup("java:comp/env");
            DataSource ds = (DataSource) envContext.lookup("jdbc/web");
            Connection con = ds.getConnection();
            Statement stmt = con.createStatement();
            try {
                ResultSet rs = stmt.executeQuery("SELECT username from users where username='" + username + "'");
                rs.next();
                rs.getString("username");
                return "Регистрация не удалась, данный пользователь уже существует!";
            }catch (SQLException ex) {
                stmt.executeUpdate(query);
                return "Регистрация успешна";
            }
        }catch (NamingException | SQLException ex) {
            System.err.println(ex);
            return "Регистрация не удалась, данный пользователь уже существует!";
        }
    }

    public int getUser(String username, String password){
        try{
            String query="SELECT count(*) as count from users where username='"+username+"' AND password='"+password+"';";
            Context initContext = new InitialContext();
            Context envContext = (Context) initContext.lookup("java:comp/env");
            DataSource ds = (DataSource) envContext.lookup("jdbc/web");
            Connection con = ds.getConnection();
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            rs.next();
            int res = rs.getInt("count");
            con.close();
            String query2="SELECT * from users where username='"+username+"' AND password='"+password+"';";
            initContext = new InitialContext();
            envContext = (Context) initContext.lookup("java:comp/env");
            ds = (DataSource) envContext.lookup("jdbc/web");
            Connection con2 = ds.getConnection();
            Statement stmt3 = con2.createStatement();
            ResultSet rs1 = stmt3.executeQuery(query2);
            rs1.next();
            if (rs1.getBoolean("blocked")) {
                con2.close();
                return 7;
            }
            return res;
        }catch (NamingException | SQLException ex) {
            System.err.println(ex);
            return 0;
        }
    }

    public ArrayList<user> getUsers(){
        ArrayList<user> users = new ArrayList<>();
        try{
            String query="SELECT idUsers,username,admin,blocked from users";
            Context initContext = new InitialContext();
            Context envContext = (Context) initContext.lookup("java:comp/env");
            DataSource ds = (DataSource) envContext.lookup("jdbc/web");
            Connection con = ds.getConnection();
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            while(rs.next()){
                users.add(new user(rs.getInt(1),rs.getString(2), rs.getBoolean(3),rs.getBoolean(4)));
            }
        }catch (NamingException | SQLException ex) {
            System.err.println(ex);
        }
        return users;
    }

    public static boolean isAuthorized() {
        return authorized;
    }

    public static void setAuthorized(boolean authorized) {
        loginDbEjb.authorized = authorized;
    }

    public static String getUsername() {
        return username;
    }

    public static void setUsername(String username) {
        loginDbEjb.username = username;
    }

    public static String getPassword() {
        return password;
    }

    public static void setPassword(String password) {
        loginDbEjb.password = password;
    }

    public static boolean isShowUsers() {
        return showUsers;
    }

    public static void setShowUsers(boolean showUsers) {
        loginDbEjb.showUsers = showUsers;
    }

    public static boolean isAdmin() {
        return admin;
    }

    public static void setAdmin(boolean admin) {
        loginDbEjb.admin = admin;
    }

    public boolean adminCheck(String username, String password){
        try{
            String query="SELECT admin from users where username='"+username+"' AND password='"+password+"';";
            Context initContext = new InitialContext();
            Context envContext = (Context) initContext.lookup("java:comp/env");
            DataSource ds = (DataSource) envContext.lookup("jdbc/web");
            Connection con = ds.getConnection();
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            rs.next();
            return rs.getBoolean(1);
        }catch (NamingException | SQLException ex) {
            System.err.println(ex);
            return false;
        }
    }


    public void blockUnb(Integer id, String blocked){
        try{
            int status=0;
            if (Objects.equals(blocked, "true")){
                status=0;
            }
            else status=1;
            String query="UPDATE users set blocked='"+status+"' where idUsers='"+id+"'";
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

    public Integer getMyUser(String username){
        try{
            String query="SELECT idUsers from users WHERE username='"+ username+"'";
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

    public static Integer commentCount(){
        try{
            String query="SELECT count(*) FROM comments c LEFT JOIN users u ON c.idUser_FK = u.idUsers WHERE u.username='"+username+"'";
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
