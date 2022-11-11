package com.example.progetto_tweb;

import dao.DAO;

import java.io.*;
import java.util.Objects;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.*;
import javax.servlet.annotation.*;




@WebServlet(name = "helloServlet", value = "/helloServlet")
public class HelloServlet extends HttpServlet {

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

                int r= (int)s.getAttribute("ruolo");
                System.out.println(r);
                response.setContentType("text/plain");

                if(r==1){

                    String nome_docente=request.getParameter("nome_docente");
                    String cognome_docente=request.getParameter("cognome_docente");
                    String username_docente=request.getParameter("username_docente");

                    if(nome_docente!=null &&cognome_docente!=null && username_docente!=null){
                        dao.add_docenti(nome_docente, cognome_docente,username_docente);
                    }
                    //message = "Docente correttamente aggiunto";
                    out.println("<p>"+"Docente correttamente aggiunto" + "</p>");

                }
                else{
                    //message = "Non possiedi i permessi necessari -> Docente NON aggiunto";
                    out.println("<p>"+ "Non possiedi i permessi necessari -> Docente NON aggiunto" + "</p>");
                }

            out.flush();
        } finally {
            out.close();
        }
        /*
        String valore_parametro=request.getParameter("button");
        System.out.println(valore_parametro);

        HttpSession s = request.getSession();

        if(Objects.equals(valore_parametro, "inserisci_docente")){
            int r= (int)s.getAttribute("ruolo");
            System.out.println("ruolo:");
            System.out.println(r);
            if(r==1){
                System.out.println(valore_parametro);

                String nome_docente=request.getParameter("nome_docente");
                String cognome_docente=request.getParameter("cognome_docente");
                String username_docente=request.getParameter("username_docente");

                if(nome_docente!=null &&cognome_docente!=null && username_docente!=null){
                    dao.add_docenti(nome_docente, cognome_docente,username_docente);
                }
                message = "Docente correttamente aggiunto";

            }
            else{
                message = "Non possiedi i permessi necessari -> Docente NON aggiunto";
            }

        }

        else if(Objects.equals(valore_parametro, "inserisci_corso")){

            if((int)s.getAttribute("ruolo")==1){
                String nome_corso=request.getParameter("nome_corso");

                if(nome_corso!=null ){
                    dao.add_corsi(nome_corso);
                }
                message = "Corso correttamente aggiunto";
            }
            else{
                message = "Non possiedi i permessi necessari -> Corso NON aggiunto";
            }

        }
        response.setContentType("text/html");

        // Hello
        PrintWriter out = response.getWriter();
        out.println("<html><body>");
        out.println("<h1>" + message + "</h1>");
        out.println("</body></html>");
        out.println("<a href=" + "index.html" + ">torna indietro</a>");
        */



    }

    public void destroy() {
    }
}