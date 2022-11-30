package com.example.progetto_tweb;

import com.google.gson.Gson;
import dao.DAO;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "ConfermaPrenotazioneServletFlutter", value = "/ConfermaPrenotazioneServletFlutter")
public class ConfermaPrenotazioneServletFlutter extends HttpServlet {
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
            String nome_corso = request.getParameter("nome_corso");
            String username_docente = request.getParameter("username_docente");
            String username_utente = request.getParameter("username_utente");
            String giorno = request.getParameter("giorno");
            int ora = Integer.parseInt(request.getParameter("ora"));
            String id_prenotazione = request.getParameter("id_prenotazione");


            System.out.println(nome_corso + username_docente + username_utente + giorno + ora + id_prenotazione);

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

    public void destroy() {
    }
}