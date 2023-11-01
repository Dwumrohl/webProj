package com.example.webproj;

import jakarta.ejb.EJB;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/alterTextServlet")
public class alterTextServlet extends HttpServlet {
    @EJB
    mainEjb mejb = new mainEjb();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Integer id = Integer.parseInt(request.getParameter("idAnn"));
        String text=request.getParameter("alteredText");
        mejb.alterText(id,text);
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        out.println("<script type='text/javascript'>alert('Запись изменена!'); window.location.replace('/webProj_war_exploded/getThemesServlet');</script>");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
