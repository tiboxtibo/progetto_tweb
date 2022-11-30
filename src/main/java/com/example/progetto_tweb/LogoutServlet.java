package com.example.progetto_tweb;

import com.google.gson.Gson;
import dao.DAO;


import java.io.*;
import java.util.Objects;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.*;
import javax.servlet.annotation.*;

@WebServlet(name = "LogoutServlet", value = "/LogoutServlet")
public class LogoutServlet extends HttpServlet {

    DAO dao = null;

    public void init(ServletConfig config) throws ServletException {

        super.init(config);

        ServletContext ctx = config.getServletContext();

        dao = (DAO) ctx.getAttribute("dao");
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String dispositivo = request.getParameter("dispositivo");
        //System.out.println(dispositivo);

        if(Objects.equals(dispositivo, "browser")){
            String message="";

            HttpSession s = request.getSession();

            response.setContentType("text/html;charset=UTF-8");
            PrintWriter out = response.getWriter();


            ServletContext ctx = getServletContext();
            RequestDispatcher rd = ctx.getRequestDispatcher("/index.html");

            try {
                s.setAttribute("username_utente","");
                s.setAttribute("ruolo",-1);

                response.setContentType("text/plain");

                message = "-1";

                //Gson gson = new Gson();
                //String ss = gson.toJson(message);
                out.println(message);
                out.flush();


            } finally {
                out.close();
            }
        }


    }

    public void destroy() {
    }
}
