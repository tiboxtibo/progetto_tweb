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

@WebServlet(name = "ListaPrenotazioniPersonaleServlet", value = "/ListaPrenotazioniPersonaleServlet")
public class ListaPrenotazioniPersonaleServlet extends HttpServlet {
    DAO dao = null;

    public void init(ServletConfig config) throws ServletException {


        super.init(config);

        ServletContext ctx = config.getServletContext();

        dao = (DAO) ctx.getAttribute("dao");
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession s = request.getSession();

        String username_utente = request.getParameter("username_utente");
        //System.out.println(s.getAttribute("username_utente"));

        System.out.println(username_utente);

        ArrayList<prenotazione> prenotazioni = dao.view_prenotazioni_utente(username_utente);
        response.setContentType("application/json");


        PrintWriter out = response.getWriter();

        Gson gson = new Gson();

        String ss = gson.toJson(prenotazioni);
        out.println(ss);
        System.out.println("STRINGA JSON " + s);

    }

    public void destroy() {
    }
}
