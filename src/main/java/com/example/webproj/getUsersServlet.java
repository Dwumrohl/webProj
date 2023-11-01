package com.example.webproj;

import jakarta.ejb.EJB;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Objects;

@WebServlet("/getUsersServlet")
public class getUsersServlet extends HttpServlet {
    @EJB
    loginDbEjb ldbe = new loginDbEjb();

    ArrayList<user> users= new ArrayList<>();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String blocked=request.getParameter("blocked");
        Integer id= Integer.parseInt(request.getParameter("id"));
        ldbe.blockUnb(id,blocked);
        String message="";
        if(Objects.equals(blocked, "true")){
            message="Пользователь разблокирован!";
        }
        else message="Пользователь заблокирован!";
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        out.println("<script type='text/javascript'>alert('"+message+"');location='profile.jsp';</script>");
        //request.getRequestDispatcher("profile.jsp").forward(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        users=ldbe.getUsers();
        request.setAttribute("users",users);
        loginDbEjb.setShowUsers(true);
        request.getRequestDispatcher("profile.jsp").forward(request,response);
    }
}
