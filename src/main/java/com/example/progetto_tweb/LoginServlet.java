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

            response.setContentType("text/plain");
            if(ruolo==0 ){//utente
                s.setAttribute("username_utente",username_utente);
                s.setAttribute("ruolo",ruolo);

                message="Accesso effettuato come Utente";

                //out.println("Benvenuto:  " + s.getAttribute("username_utente"));
                //out.println("Hai effettuato l'accesso come:  " + s.getAttribute("ruolo") );
            }
            else if(ruolo==1){//amministratore
                s.setAttribute("username_utente",username_utente);
                s.setAttribute("ruolo",ruolo);

                message="Accesso effettuato come Amministratore";

                //out.println("Benvenuto:  " + s.getAttribute("username_utente") );
                //out.println("Hai effettuato l'accesso come:  " + s.getAttribute("ruolo"));
            }
            else if(ruolo==2){//ospite
                s.setAttribute("username_utente","guest");
                s.setAttribute("ruolo",2);

                message="Accesso effettuato come Guest";

                //out.println("Credenziali NON valide ");
                //out.println("Benvenuto:  " + s.getAttribute("username_utente"));
                //out.println("Hai effettuato l'accesso come:  " + s.getAttribute("ruolo") );
            }
            else{
                message="Credenziali Errate! Riprova!";
            }


            Gson gson = new Gson();

            String ss = gson.toJson(message);
            out.println(ss);
            out.flush();

            processRequest(request, response);


        } catch (ServletException e) {
            e.printStackTrace();
        } finally {
            out.close();
        }

    }

    private void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        System.out.println("111111");
        request.setCharacterEncoding("UTF-8");
        ServletContext ctx = getServletContext();
        HttpSession s = request.getSession();
        int ruolo = (int) s.getAttribute("ruolo");

        RequestDispatcher rd = ctx.getRequestDispatcher("/index.html");

        if(ruolo==1 || ruolo==2 || ruolo==0){
            System.out.println("222222");
            System.out.println(ruolo);
            rd = ctx.getRequestDispatcher("/vueApplication.html");
            rd.forward(request,response);

        }


    }



    public void destroy() {
    }
}