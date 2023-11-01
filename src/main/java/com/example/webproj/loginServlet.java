package com.example.webproj;

import java.io.*;

import jakarta.ejb.EJB;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

@WebServlet("/logServ")
public class loginServlet extends HttpServlet {

    @EJB
    loginDbEjb ldbe = new loginDbEjb();
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        int flag = Integer.parseInt(request.getParameter("flag"));
        String username;
        String password;
        if (flag == 0) {
            username = request.getParameter("loginText");
            password = request.getParameter("pswText");
            if(ldbe.getUser(username,password) == 1) {
                loginDbEjb.setAuthorized(true);
                if(ldbe.adminCheck(username, password))
                    loginDbEjb.setAdmin(true);
                loginDbEjb.setUsername(username);
                loginDbEjb.setPassword(password);
                response.setContentType("text/html");
                PrintWriter out = response.getWriter();
                out.println("<script type='text/javascript'>alert('Вход подтвержден!'); window.location.replace('main.jsp');</script>");
            }
            else if (ldbe.getUser(username,password) == 7){
                loginDbEjb.setAuthorized(false);
                response.setContentType("text/html");
                PrintWriter out = response.getWriter();
                out.println("<script type='text/javascript'>alert('Пользователь заблокирован, свяжитесь с администратором: test@ebail.com!');location='login.jsp';</script>");
            }
           else{
                loginDbEjb.setAuthorized(false);
                response.setContentType("text/html");
                PrintWriter out = response.getWriter();
                out.println("<script type='text/javascript'>alert('Неверный пользователь или пароль!');location='login.jsp';</script>");
                //request.getRequestDispatcher("login.jsp").forward(request,response);
            }

        }
        else{
            username = request.getParameter("loginTextReg");
            password = request.getParameter("pswTextReg");
            String message = ldbe.setUser(username,password);
            response.setContentType("text/html");
            PrintWriter out = response.getWriter();
            out.println("<script type='text/javascript'>alert('"+message+"'); window.location.replace('main.jsp');</script>");
        }

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        loginDbEjb.setAuthorized(false);
        loginDbEjb.setAdmin(false);
        loginDbEjb.setShowUsers(false);
        loginDbEjb.setUsername(null);
        req.getRequestDispatcher("profile.jsp").forward(req,resp);
    }

    public void destroy() {
    }
}