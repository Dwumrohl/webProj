package com.example.webproj;

import jakarta.ejb.EJB;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;
import java.util.ArrayList;

@WebServlet("/getNewsServlet")
public class getNewsServlet extends HttpServlet {

    @EJB
    mainEjb mejb = new mainEjb();
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ArrayList<announcement> announcements= mejb.getNews();
        request.setAttribute("announcements", announcements);
        request.getRequestDispatcher("resultPage.jsp").forward(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
