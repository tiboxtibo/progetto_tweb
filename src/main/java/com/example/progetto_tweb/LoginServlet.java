package com.example.progetto_tweb;

import com.google.gson.Gson;
import dao.DAO;


import java.io.*;
import javax.servlet.RequestDispatcher;
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
        String message="";

        HttpSession s = request.getSession();

        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();


        ServletContext ctx = getServletContext();
        RequestDispatcher rd = ctx.getRequestDispatcher("/index.html");

        try {
            String username_utente = request.getParameter("username_utente");
            String password_utente = request.getParameter("password_utente");

            //System.out.println(username_utente);

            int ruolo = dao.login(username_utente,password_utente);

            if(ruolo>=0){
                s.setAttribute("username_utente",username_utente);
                s.setAttribute("ruolo",ruolo);

                if(ruolo==0 ){//utente
                    message="Accesso effettuato come Utente";

                }
                else if(ruolo==1){//amministratore
                    message="Accesso effettuato come Amministratore";

                }
                else if(ruolo==2){//ospite
                    message="Accesso effettuato come Guest";

                }
                else{
                    message="Credenziali Errate! Riprova!";
                }

            }else{
                message="Credenziali Errate! Riprova!";
            }

            response.setContentType("text/plain");

            Gson gson = new Gson();

            String ss = gson.toJson(message);
            out.println(ss);
            out.flush();


        } finally {
            out.close();
        }

    }





    public void destroy() {
    }
}