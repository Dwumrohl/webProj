package com.example.webproj;

import jakarta.ejb.EJB;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.URI;
import java.net.URISyntaxException;

@WebServlet("/addCommentServlet")
public class addCommentServlet extends HttpServlet {
    @EJB
    mainEjb mejb = new mainEjb();

    @EJB
    loginDbEjb led = new loginDbEjb();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String text = request.getParameter("alteredText");
        String temp = request.getParameter("idAnn").split("=")[1];
        Integer id=Integer.parseInt(temp);
        Integer userId= led.getMyUser(loginDbEjb.getUsername());
        mejb.newComment(id,text,userId);
        String path = null;
        try {
            path = new URI(request.getHeader("referer")).getPath();
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
        StringBuffer buff = new StringBuffer();
        buff.append(path);
        buff.append('?');
        buff.append("id=").append(temp);
        path=buff.toString();
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        out.println("<script type='text/javascript'>window.location.replace('"+path+"');</script>");
    }
}
