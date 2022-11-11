package com.example.progetto_tweb;

import dao.DAO;


import java.io.*;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.*;
import javax.servlet.annotation.*;


@WebServlet(name = "loginServlet", value = "/loginServlet")
public class LoginServlet extends HttpServlet {
    private String message;
    DAO dao = null;

    public void init(ServletConfig config) throws ServletException {


        super.init(config);

        ServletContext ctx = config.getServletContext();

        dao = (DAO) ctx.getAttribute("dao");
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {


        HttpSession s = request.getSession();

        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        try {
            String username_utente = request.getParameter("username_utente");
            String password_utente = request.getParameter("password_utente");

            int ruolo = dao.login(username_utente,password_utente);

            response.setContentType("text/plain");
            if(ruolo==0 ){//utente
                s.setAttribute("username_utente",username_utente);
                s.setAttribute("ruolo",ruolo);
                out.println("<p>"+"Benvenuto:  " + s.getAttribute("username_utente") + "</p>");
                out.println("<p>"+"Hai effettuato l'accesso come:  " + s.getAttribute("ruolo") + "</p>");
            }
            else if(ruolo==1){//amministratore
                s.setAttribute("username_utente",username_utente);
                s.setAttribute("ruolo",ruolo);

                out.println("<p>"+"Benvenuto:  " + s.getAttribute("username_utente") + "</p>");
                out.println("<p>"+"Hai effettuato l'accesso come:  " + s.getAttribute("ruolo") + "</p>");
            }
            else{
                s.setAttribute("username_utente","guest");
                s.setAttribute("ruolo",2);

                out.println("<p>"+"Credenziali NON valide "+"</p>");
                out.println("<p>"+"Benvenuto:  " + s.getAttribute("username_utente") + "</p>");
                out.println("<p>"+"Hai effettuato l'accesso come:  " + s.getAttribute("ruolo") + "</p>");
            }

            out.flush();
        } finally {
            out.close();
        }

    }



    public void destroy() {
    }
}