package com.example.progetto_tweb;

import com.google.gson.Gson;
import dao.DAO;
import dao.corso;
import dao.corso_docente;
import dao.docente;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Objects;

@WebServlet(name = "AdminServlet", value = "/AdminServlet")
public class AdminServlet extends HttpServlet {
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
            String adminOperation = request.getParameter("adminOperation");
            //System.out.println(adminOperation);

            /** Verifico l'azione da compiere*/
            if(Objects.equals(adminOperation, "getCorso")){
                response.setContentType("application/json");
                PrintWriter out = response.getWriter();
                //System.out.println("===========ciao=================");
                Gson gson = new Gson(); // traduttore da e verso formato JSON

                ArrayList<corso> viewCorso = dao.view_corso();

                out.println(gson.toJson(viewCorso));
            }
            if(Objects.equals(adminOperation, "getCorsoDocente")){
                response.setContentType("application/json");
                PrintWriter out = response.getWriter();
                //System.out.println("===========CorsoDocente=================");
                Gson gson = new Gson(); // traduttore da e verso formato JSON

                ArrayList<corso_docente> viewCorDoc = dao.view_corso_docente();

                out.println(gson.toJson(viewCorDoc));
            }
            if(Objects.equals(adminOperation, "getDocente")){
                response.setContentType("application/json");
                PrintWriter out = response.getWriter();
                //System.out.println("===========Docente=================");
                Gson gson = new Gson(); // traduttore da e verso formato JSON

                ArrayList<docente> viewDoc = dao.view_docente();

                out.println(gson.toJson(viewDoc));
            }
        }



    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String dispositivo = request.getParameter("dispositivo");
        //System.out.println(dispositivo);

        /** Verifico il dispositivo*/
        if(Objects.equals(dispositivo, "browser")){
            String adminOperation = request.getParameter("adminOperation");
            //System.out.println(adminOperation);

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
            }
            else{
                if(Objects.equals(adminOperation, "addDocente")){
                    response.setContentType("text/plain");
                    PrintWriter out = response.getWriter();
                    String message = null;

                    try {
                        String nome_docente = request.getParameter("nomeDocente");
                        String cognome_docente = request.getParameter("cognomeDocente");
                        String username_docente = request.getParameter("usernameDocente");
                        //System.out.println("Nome docente: " + nome_docente + " Cognome Docente: " + cognome_docente + "Username Docente: " + username_docente);

                        if (nome_docente != null && !nome_docente.equals("") &&
                                cognome_docente != null && !cognome_docente.equals("") &&
                                username_docente != null && !username_docente.equals("")) {

                            if (dao.add_docente(nome_docente, cognome_docente, username_docente)) {

                                message = "Valori Inseriti Correttamente";
                            } else
                                message = "Valori Non Inseriti";

                        } else
                            message = "Dati Sbagliati, Riprova";

                        //System.out.println(message);
                        out.println(new Gson().toJson(message));
                        out.flush();
                    } finally {
                        out.close();
                    }
                }
                if(Objects.equals(adminOperation, "delDocente")){
                    //HttpSession s = request.getSession();

                    response.setContentType("text/plain");
                    PrintWriter out = response.getWriter();
                    String message = null;

                    try {
                        String username_docente = request.getParameter("usernameDocente");
                        //System.out.println("Username Docente: " + username_docente);

                        if (username_docente != null && !username_docente.equals("")) {

                            if (dao.del_docente(username_docente)) {

                                message = ("Il docente: " + username_docente + " è stato eliminato");
                            } else
                                message = ("Il docente: " + username_docente + " non è stato eliminato");

                        } else
                            message = "Dati Sbagliati, Riprova";

                        //System.out.println(message);
                        out.println(new Gson().toJson(message));
                        out.flush();
                    } finally {
                        out.close();
                    }

                }
                if(Objects.equals(adminOperation, "addCorsoDocente")){
                    //HttpSession s = request.getSession();

                    response.setContentType("text/plain");
                    PrintWriter out = response.getWriter();
                    String message = null;

                    try {
                        String username_docente = request.getParameter("usernameDocente1");
                        String nome_corso = request.getParameter("nomeCorso1");
                        //System.out.println("Username Docente: " + username_docente + "Nome Corso: " + nome_corso);

                        if (nome_corso != null && !nome_corso.equals("") &&
                                username_docente != null && !username_docente.equals("")) {

                            if (dao.add_corso_docente(username_docente, nome_corso)) {

                                message = "Valori Inseriti Correttamente";
                            } else
                                message = "Valori Non Inseriti";

                        } else
                            message = "Dati Sbagliati, Riprova";

                        //System.out.println(message);
                        out.println(new Gson().toJson(message));
                        out.flush();
                    } finally {
                        out.close();
                    }
                }
                if(Objects.equals(adminOperation, "delCorsoDocente")){
                    //HttpSession s = request.getSession();

                    response.setContentType("text/plain");
                    PrintWriter out = response.getWriter();
                    String message = null;

                    try {
                        String username_docente = request.getParameter("usernameDocente1");
                        String nome_corso = request.getParameter("nomeCorso1");
                        //System.out.println("Username Docente: " + username_docente + "Nome Corso: " + nome_corso);

                        if (nome_corso != null && !nome_corso.equals("") &&
                                username_docente != null && !username_docente.equals("")) {

                            if (dao.del_corso_docente(username_docente, nome_corso)) {

                                message = ("Il docente: " + username_docente + " è stato eliminato");
                            } else
                                message = ("Il docente: " + username_docente + " non è stato eliminato");

                        } else
                            message = "Dati Sbagliati, Riprova";

                        //System.out.println(message);
                        out.println(new Gson().toJson(message));
                        out.flush();
                    } finally {
                        out.close();
                    }
                }
                if(Objects.equals(adminOperation, "addCorso")) {
                    //HttpSession s = request.getSession();

                    response.setContentType("text/plain");
                    PrintWriter out = response.getWriter();
                    String message = null;

                    try {
                        String nome_corso = request.getParameter("nomeCorso");
                        //System.out.println("Nome corso: " + nome_corso);

                        if (nome_corso != null && !nome_corso.equals("")) {

                            if (dao.add_corso(nome_corso)) {

                                message = "Valori Inseriti Correttamente";
                            } else
                                message = "Valori Non Inseriti";

                        } else
                            message = "Dati Sbagliati, Riprova";

                        //System.out.println(message);
                        out.println(new Gson().toJson(message));
                        out.flush();
                    } finally {
                        out.close();
                    }
                }
                if(Objects.equals(adminOperation, "delCorso")){
                    //HttpSession s = request.getSession();

                    response.setContentType("text/plain");
                    PrintWriter out = response.getWriter();
                    String message = null;

                    try {
                        String nome_corso = request.getParameter("nomeCorso");
                        //System.out.println("Nome Corso: " + nome_corso);

                        if (nome_corso != null && !nome_corso.equals("")) {

                            if (dao.del_corso(nome_corso)) {

                                message = ("La materia: " + nome_corso + " è stata eliminata");
                            } else
                                message = ("La materia: " + nome_corso + " non è stata eliminata");

                        } else
                            message = "Dati Sbagliati, Riprova";

                        //System.out.println(message);
                        out.println(new Gson().toJson(message));
                        out.flush();
                    } finally {
                        out.close();
                    }
                }
                if(Objects.equals(adminOperation, "delPrenotazioneAmm")){
                    String message="";

                    response.setContentType("text/html;charset=UTF-8");
                    PrintWriter out = response.getWriter();


                    ServletContext ctx = getServletContext();
                    RequestDispatcher rd = ctx.getRequestDispatcher("/index.html");

                    try {
                        String nome_corso = request.getParameter("nome_corso");
                        String username_docente = request.getParameter("username_docente");
                        String username_utente = request.getParameter("cusername_utente");//varia per l'username da elimina prenotazione Servlet
                        String giorno = request.getParameter("giorno");
                        int ora = Integer.parseInt(request.getParameter("ora"));
                        String id_prenotazione = request.getParameter("id_prenotazione");


                        //System.out.println(nome_corso + username_docente + username_utente + giorno + ora);
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
            }


        }




    }
}
