package com.example.webproj;

import jakarta.ejb.EJB;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;

@WebServlet("/searchServlet")
public class searchServlet extends HttpServlet {
    @EJB
    mainEjb mejb = new mainEjb();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String value=request.getParameter("searchMe");
        request.setAttribute("announcements",mejb.searchMe(value));
        request.getRequestDispatcher("resultPage.jsp").forward(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
