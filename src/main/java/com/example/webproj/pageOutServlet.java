package com.example.webproj;

import jakarta.ejb.EJB;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;

@WebServlet("/pageOutServlet")
public class pageOutServlet extends HttpServlet {
    @EJB
    mainEjb mejb = new mainEjb();
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Integer id= Integer.parseInt(request.getParameter("id"));
        request.setAttribute("announcements",mejb.getTextInfo(id));
        request.setAttribute("comments",mejb.getComments(id));
        request.setAttribute("commentCount",mejb.commentPageCount(id));
        request.getRequestDispatcher("pageOut.jsp").forward(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
