package com.example.webproj;

import jakarta.ejb.EJB;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;

@WebServlet("/textOutServlet")
public class textOutServlet extends HttpServlet {
    @EJB
    mainEjb mejb = new mainEjb();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Integer id= Integer.parseInt(request.getParameter("id"));
        request.setAttribute("announcement",mejb.getTextInfo2(id));
        request.getRequestDispatcher("alterAnn.jsp").forward(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
