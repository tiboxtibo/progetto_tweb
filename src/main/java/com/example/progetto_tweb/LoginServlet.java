package com.example.progetto_tweb;

import dao.DAO;


import java.io.*;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.*;
import javax.servlet.annotation.*;


@WebServlet(name = "LoginServlet", value = "/LoginServlet")
public class LoginServlet extends HttpServlet {

    DAO dao = null;

    public void init(ServletConfig config) throws ServletException {


        super.init(config);

        ServletContext ctx = config.getServletContext();

        dao = (DAO) ctx.getAttribute("dao");
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        System.out.println("ciao sono entrato");

        HttpSession s = request.getSession();

        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();


        try {
            String username_utente = request.getParameter("username_utente");
            String password_utente = request.getParameter("password_utente");

            System.out.println(username_utente);


            int ruolo = dao.login(username_utente,password_utente);


            response.setContentType("text/plain");
            if(ruolo==0 ){//utente
                s.setAttribute("username_utente",username_utente);
                s.setAttribute("ruolo",ruolo);

                out.println("Benvenuto:  " + s.getAttribute("username_utente"));
                out.println("Hai effettuato l'accesso come:  " + s.getAttribute("ruolo") );
            }
            else if(ruolo==1){//amministratore
                s.setAttribute("username_utente",username_utente);
                s.setAttribute("ruolo",ruolo);

                out.println("Benvenuto:  " + s.getAttribute("username_utente") );
                out.println("Hai effettuato l'accesso come:  " + s.getAttribute("ruolo"));
            }
            else{
                s.setAttribute("username_utente","guest");
                s.setAttribute("ruolo",2);

                out.println("Credenziali NON valide ");
                out.println("Benvenuto:  " + s.getAttribute("username_utente"));
                out.println("Hai effettuato l'accesso come:  " + s.getAttribute("ruolo") );
            }


            out.flush();
        } finally {
            out.close();
        }

    }



    public void destroy() {
    }
}