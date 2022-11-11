package com.example.esservlet;

import dao.DAO;
import dao.docenti;


import java.io.*;
import java.util.ArrayList;
import java.util.Objects;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.*;
import javax.servlet.annotation.*;

import static dao.DAO.*;


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

        /*
        String valore_parametro=request.getParameter("button");
        System.out.println(valore_parametro);

        if(Objects.equals(valore_parametro, "login")){
            System.out.println(valore_parametro);

            String username_utente = request.getParameter("username_utente");
            String password = request.getParameter("password");

            HttpSession s = request.getSession();

            if(username_utente!=null && password!=null){
                int ruolo = dao.login(username_utente,password);

                if(ruolo==0 ){//utente
                    s.setAttribute("username_utente",username_utente);
                    s.setAttribute("ruolo",ruolo);
                    message = "Login effettuato correttamente come utente";
                }
                else if(ruolo==1){//amministratore
                    s.setAttribute("username_utente",username_utente);
                    s.setAttribute("ruolo",ruolo);
                    message = "Login effettuato correttamente come amministratore";
                }
                else{
                    message = "Login NON effettuato correttamente";
                }
            }

            response.setContentType("text/html");

            // Hello
            PrintWriter out = response.getWriter();
            out.println("<html><body>");
            out.println("<h1>" + message + "</h1>");
            out.println("</body></html>");



            if((int)s.getAttribute("ruolo")==1 || (int)s.getAttribute("ruolo")==0){
                out.println("<p>"+"Benvenuto:  " + s.getAttribute("username_utente") + "</p>");
                out.println("<p>"+"Hai effettuato l'accesso come:  " + s.getAttribute("ruolo") + "</p>");

            }
            out.println("<p> <a href=" + "index.html" + ">torna indietro</a> </p>");


        }

         */
        HttpSession s = request.getSession();

        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        try {
            String username_utente = request.getParameter("username_utente");
            String password_utente = request.getParameter("password_utente");

            int ruolo = dao.login(username_utente,password_utente);

            if(ruolo==0 ){//utente
                s.setAttribute("username_utente",username_utente);
                s.setAttribute("ruolo",ruolo);
                message = "Login effettuato correttamente come utente";
            }
            else if(ruolo==1){//amministratore
                s.setAttribute("username_utente",username_utente);
                s.setAttribute("ruolo",ruolo);
                message = "Login effettuato correttamente come amministratore";
            }
            else{
                message = "Login NON effettuato correttamente";
            }

            response.setContentType("text/plain");
            out.println(message);

            if((int)s.getAttribute("ruolo")==1 || (int)s.getAttribute("ruolo")==0){
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