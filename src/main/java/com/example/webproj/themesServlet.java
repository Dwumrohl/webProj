package com.example.webproj;

import jakarta.ejb.EJB;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;

@WebServlet("/themesServlet")
public class themesServlet extends HttpServlet {
    @EJB
    mainEjb mejb = new mainEjb();
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Integer propertyId= Integer.parseInt(request.getParameter("propertyId"));
        request.setAttribute("announcements",mejb.getAnnouncements(propertyId));
        request.getRequestDispatcher("resultPage.jsp").forward(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
