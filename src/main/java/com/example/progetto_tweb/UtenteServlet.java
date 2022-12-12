package com.example.progetto_tweb;

import com.google.gson.Gson;
import dao.DAO;
import dao.prenotazione;


import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Objects;

@WebServlet(name = "UtenteServlet", value = "/UtenteServlet")
public class UtenteServlet extends HttpServlet {
    DAO dao = null;

    public void init(ServletConfig config) throws ServletException {

        super.init(config);

        ServletContext ctx = config.getServletContext();
        dao = (DAO) ctx.getAttribute("dao");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String dispositivo = request.getParameter("dispositivo");
        //System.out.println(dispositivo);

        /** Verifico il dispositivo*/
        if(Objects.equals(dispositivo, "browser")){
            String userOperation = request.getParameter("userOperation");
            //System.out.println(userOperation);

            if(Objects.equals(userOperation, "getPrenotazioniDisponibili")){


                ArrayList<prenotazione> prenotazioni = dao.view_prenotazioni_prenotabili();

                //System.out.println(prenotazioni);
                response.setContentType("application/json");


                PrintWriter out = response.getWriter();

                Gson gson = new Gson();

                String s = gson.toJson(prenotazioni);
                out.println(s);
                //System.out.println("STRINGA JSON " + s);
            }
            if(Objects.equals(userOperation, "getPrenotazioniDisponibiliCorso")){

                String nome_corso = request.getParameter("nome_corso");

                //System.out.println(nome_corso);

                ArrayList<prenotazione> prenotazioni = dao.view_prenotazioni_prenotabili_corso(nome_corso);

                if(prenotazioni==null){
                    String message = "Materia non ancora associata a nessun Docente";

                    PrintWriter out = response.getWriter();

                    Gson gson = new Gson();

                    String s = gson.toJson(message);
                    out.println(s);

                }else{
                    //System.out.println(prenotazioni);
                    response.setContentType("application/json");


                    PrintWriter out = response.getWriter();

                    Gson gson = new Gson();

                    String s = gson.toJson(prenotazioni);
                    out.println(s);
                    //System.out.println("STRINGA JSON " + s);
                }


            }
            if(Objects.equals(userOperation, "getListaPrenotazioni")){

                ArrayList<prenotazione> prenotazioni = dao.view_prenotazioni();
                response.setContentType("application/json");


                PrintWriter out = response.getWriter();

                Gson gson = new Gson();

                String s = gson.toJson(prenotazioni);
                out.println(s);
                //System.out.println("STRINGA JSON " + s);
            }
            if(Objects.equals(userOperation, "getListaPrenotazioniUtente")){

                HttpSession s = request.getSession();

                String username_utente = request.getParameter("username_utente");
                //System.out.println(s.getAttribute("username_utente"));

                //System.out.println(username_utente);

                ArrayList<prenotazione> prenotazioni = dao.view_prenotazioni_utente(username_utente);
                response.setContentType("application/json");


                PrintWriter out = response.getWriter();

                Gson gson = new Gson();

                String ss = gson.toJson(prenotazioni);
                out.println(ss);
                //System.out.println("STRINGA JSON " + s);
            }
        }



    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String dispositivo = request.getParameter("dispositivo");
        //System.out.println(dispositivo);

        /** Verifico il dispositivo*/
        if(Objects.equals(dispositivo, "browser")){
            String userOperation = request.getParameter("userOperation");
            //System.out.println(userOperation);

            HttpSession s = request.getSession();

            //nel caso la sessione utente sia scaduta
            if(s.getAttribute("username_utente")==null){
                String message="Sessione Scaduta! Effettuare nuovamente il login";
                response.setContentType("text/plain");
                PrintWriter out = response.getWriter();
                try{

                    out.println(new Gson().toJson(message));
                    out.flush();
                }
                finally {
                    out.close();
                }
            }else{
                /** Verifico l'azione da compiere*/
                if(Objects.equals(userOperation, "prenota")){
                    String message="";

                    //System.out.println(s.getId());

                    response.setContentType("text/html;charset=UTF-8");
                    PrintWriter out = response.getWriter();


                    ServletContext ctx = getServletContext();
                    RequestDispatcher rd = ctx.getRequestDispatcher("/index.html");


                    try {
                        String nome_corso = request.getParameter("nome_corso");
                        String username_docente = request.getParameter("username_docente");
                        String username_utente = (String) s.getAttribute("username_utente");
                        String giorno = request.getParameter("giorno");
                        int ora = Integer.parseInt(request.getParameter("ora"));

                        if(s.getAttribute("username_utente")==null){
                            message="Sessione Scaduta! Effettuare nuovamente il login";
                        }
                        else{
                            //System.out.println(nome_corso + username_docente + username_utente + giorno + ora);

                            if(dao.add_prenotazione(nome_corso,username_utente,username_docente, giorno, ora)){
                                message="Lezione Correttamente prenotata";
                            }
                            else{
                                message="Lezione NON prenotata! Utente già occupato!";
                            }
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
                if(Objects.equals(userOperation, "delPrenotazione")){
                    String message="";


                    response.setContentType("text/html;charset=UTF-8");
                    PrintWriter out = response.getWriter();


                    ServletContext ctx = getServletContext();
                    RequestDispatcher rd = ctx.getRequestDispatcher("/index.html");



                    try {
                        if(s.getAttribute("username_utente")==null){
                            message="Sessione Scaduta! Effettuare nuovamente il login";
                        }
                        else{
                            String nome_corso = request.getParameter("nome_corso");
                            String username_docente = request.getParameter("username_docente");
                            String username_utente = (String) s.getAttribute("username_utente");
                            String giorno = request.getParameter("giorno");
                            int ora = Integer.parseInt(request.getParameter("ora"));
                            String id_prenotazione = request.getParameter("id_prenotazione");


                            //System.out.println(nome_corso + username_docente + username_utente + giorno + ora + id_prenotazione);
                            if(dao.del_prenotazione(nome_corso,username_utente,username_docente, giorno, ora,id_prenotazione)){
                                message="Lezione Correttamente cancellata";
                            }
                            else{
                                message="Lezione NON cancellata! ";
                            }
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
                if(Objects.equals(userOperation, "prenotaPrenotazione")){
                    String message="";

                    response.setContentType("text/html;charset=UTF-8");
                    PrintWriter out = response.getWriter();


                    ServletContext ctx = getServletContext();
                    RequestDispatcher rd = ctx.getRequestDispatcher("/index.html");

                    try {
                        if(s.getAttribute("username_utente")==null){
                            message="Sessione Scaduta! Effettuare nuovamente il login";
                        }
                        else{
                            String nome_corso = request.getParameter("nome_corso");
                            String username_docente = request.getParameter("username_docente");
                            String username_utente = (String) s.getAttribute("username_utente");
                            String giorno = request.getParameter("giorno");
                            int ora = Integer.parseInt(request.getParameter("ora"));
                            String id_prenotazione = request.getParameter("id_prenotazione");


                            //System.out.println(nome_corso + username_docente + username_utente + giorno + ora);
                            if(dao.prenota_prenotazione(nome_corso,username_utente,username_docente, giorno, ora,id_prenotazione)){
                                message="Lezione Correttamente prenotata";
                            }
                            else{
                                message="Lezione NON prenotata!";
                            }
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
            }


        }

        /** Verifico il dispositivo -> parte di flutter*/
        if(Objects.equals(dispositivo, "flutter")){
            String userOperation = request.getParameter("userOperation");
            //System.out.println(userOperation);

            /** Verifico l'azione da compiere*/
            if(Objects.equals(userOperation, "prenota")){
                String message="";

                HttpSession s = request.getSession();

                response.setContentType("text/html;charset=UTF-8");
                PrintWriter out = response.getWriter();


                ServletContext ctx = getServletContext();
                RequestDispatcher rd = ctx.getRequestDispatcher("/index.html");


                try {
                    String nome_corso = request.getParameter("nome_corso");
                    String username_docente = request.getParameter("username_docente");
                    String username_utente = request.getParameter("username_utente");
                    String giorno = request.getParameter("giorno");
                    int ora = Integer.parseInt(request.getParameter("ora"));

                    //System.out.println(nome_corso + username_docente + username_utente + giorno + ora);

                    if(dao.add_prenotazione(nome_corso,username_utente,username_docente, giorno, ora)){
                        message="Lezione Correttamente prenotata";
                    }
                    else{
                        message="Lezione NON prenotata!";
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
            if(Objects.equals(userOperation, "prenotazioniDisponibili")){
                ArrayList<prenotazione> prenotazioni = dao.view_prenotazioni_prenotabili();
                response.setContentType("application/json");


                PrintWriter out = response.getWriter();

                Gson gson = new Gson();

                String s = gson.toJson(prenotazioni);
                out.println(s);
                //System.out.println("STRINGA JSON " + s);
            }
            if(Objects.equals(userOperation, "eliminaPrenotazione")){
                String message="";

                //HttpSession s = request.getSession();

                response.setContentType("text/html;charset=UTF-8");
                PrintWriter out = response.getWriter();


                ServletContext ctx = getServletContext();
                RequestDispatcher rd = ctx.getRequestDispatcher("/index.html");

                try {
                    String nome_corso = request.getParameter("nome_corso");
                    String username_docente = request.getParameter("username_docente");
                    String username_utente = request.getParameter("username_utente");
                    String giorno = request.getParameter("giorno");
                    int ora = Integer.parseInt(request.getParameter("ora"));
                    String id_prenotazione = request.getParameter("id_prenotazione");


                    //System.out.println(nome_corso + username_docente + username_utente + giorno + ora + id_prenotazione);
                    if(dao.del_prenotazione(nome_corso,username_utente,username_docente, giorno, ora,id_prenotazione)){
                        message="Lezione Correttamente cancellata";
                    }
                    else{
                        message="Lezione NON cancellata! ";
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
            if(Objects.equals(userOperation, "confermaPrenotazione")){
                String message="";

                //HttpSession s = request.getSession();

                response.setContentType("text/html;charset=UTF-8");
                PrintWriter out = response.getWriter();


                ServletContext ctx = getServletContext();
                RequestDispatcher rd = ctx.getRequestDispatcher("/index.html");

                try {
                    String nome_corso = request.getParameter("nome_corso");
                    String username_docente = request.getParameter("username_docente");
                    String username_utente = request.getParameter("username_utente");
                    String giorno = request.getParameter("giorno");
                    int ora = Integer.parseInt(request.getParameter("ora"));
                    String id_prenotazione = request.getParameter("id_prenotazione");


                    //System.out.println(nome_corso + username_docente + username_utente + giorno + ora + id_prenotazione);

                    if(dao.prenota_prenotazione(nome_corso,username_utente,username_docente, giorno, ora,id_prenotazione)){
                        message="Lezione Correttamente prenotata";
                    }
                    else{
                        message="Lezione NON prenotata!";
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
            if(Objects.equals(userOperation, "listaPrenotazioniPersonale")){
                HttpSession s = request.getSession();

                String username_utente = request.getParameter("username_utente");
                //System.out.println(s.getAttribute("username_utente"));

                //System.out.println(username_utente);

                ArrayList<prenotazione> prenotazioni = dao.view_prenotazioni_utente(username_utente);
                response.setContentType("application/json");


                PrintWriter out = response.getWriter();

                Gson gson = new Gson();

                String ss = gson.toJson(prenotazioni);
                out.println(ss);
                //System.out.println("STRINGA JSON " + s);
            }
        }





    }
}
