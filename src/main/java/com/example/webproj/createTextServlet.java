package com.example.webproj;

import jakarta.ejb.EJB;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.*;

@WebServlet("/createTextServlet")
@MultipartConfig(fileSizeThreshold=1024*1024*2,
        maxFileSize=1024*1024*10,
        maxRequestSize=1024*1024*50)
 public class createTextServlet extends HttpServlet {
   @EJB
   mainEjb mejb = new mainEjb();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String header=request.getParameter("header");
        Integer sel=Integer.parseInt(request.getParameter("typeS"));
        String type=null;
        switch(sel){
            case 1:
                type="обновление";
                break;
            case 2:
                type="гайд";
                break;
            case 3:
                type="пасхалка";
                break;
        }
        String content=request.getParameter("contentS");
        String text=request.getParameter("alteredText");
        announcement ann= null;
        if (request.getPart("file").getSize()!=0){

            final String path = "C:/webProj/target/webProj-1.0-SNAPSHOT/styles/images";
            String relative = "styles/images";
            String photo = "";
            String fileName = "";
            response.setContentType("text/html;charset=UTF-8");
            try {
                Part filePart = request.getPart("file");
                File file = new File(path);
                file.mkdir();
                fileName = getFileName(filePart);

                OutputStream out = null;

                InputStream filecontent = null;

                out = new FileOutputStream(path + File.separator
                        + fileName);

                filecontent = filePart.getInputStream();


                int read = 0;
                final byte[] bytes = new byte[1024];
                anotherFile(filePart);

                while ((read = filecontent.read(bytes)) != -1) {
                    out.write(bytes, 0, read);

                    photo = path + "/" + fileName;
                }
            } finally {

            }
             ann = new announcement(header, relative + "/" + fileName, type, content, text, sel);

        }
        else
            ann= new announcement(header,type,content,text,sel);

        mejb.createNews(ann);
        response.setContentType("text/html");
        PrintWriter outa = response.getWriter();
        outa.println("<script type='text/javascript'>alert('Запись создана!'); window.location.replace('/webProj_war_exploded/getThemesServlet');</script>");

    }

    private String getFileName(final Part part) {

        for (String content : part.getHeader("content-disposition").split(";")) {
            if (content.trim().startsWith("filename")) {
                return content.substring(
                        content.indexOf('=') + 1).trim().replace("\"", "");
            }
        }
        return null;
    }

    private void anotherFile(Part filea){
        final String path = "C:/webProj/src/main/webapp/styles/images";
        String relative = "styles/images";
        String photo = "";
        String fileName = "";
        try {
            Part filePart = filea;
            File file = new File(path);
            file.mkdir();
            fileName = getFileName(filePart);

            OutputStream out = null;

            InputStream filecontent = null;

            out = new FileOutputStream(path + File.separator
                    + fileName);

            filecontent = filePart.getInputStream();


            int read = 0;
            final byte[] bytes = new byte[1024];

            while ((read = filecontent.read(bytes)) != -1) {
                out.write(bytes, 0, read);

                photo = path + "/" + fileName;
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {

        }
    }


}
