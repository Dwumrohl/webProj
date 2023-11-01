package com.example.webproj;

import jakarta.ejb.EJB;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.URI;
import java.net.URISyntaxException;

@WebServlet("/deleteCommentServlet")
public class deleteCommentServlet extends HttpServlet {
    @EJB
    mainEjb mejb = new mainEjb();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Integer id=Integer.parseInt(request.getParameter("id"));
        mejb.deleteComment(id);
        String path = null;
        try {
            path = new URI(request.getHeader("referer")).getPath();
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
        StringBuffer buff = new StringBuffer();
        buff.append(path);
        buff.append('?');
        String param = request.getParameter("try");
        buff.append(param);
        path=buff.toString();
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        out.println("<script type='text/javascript'>alert('Запись удалена!'); window.location.replace('"+path+"');</script>");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
