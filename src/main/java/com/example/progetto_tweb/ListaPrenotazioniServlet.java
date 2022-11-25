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

@WebServlet(name = "ListaPrenotazioniServlet", value = "/ListaPrenotazioniServlet")
public class ListaPrenotazioniServlet extends HttpServlet {
    DAO dao = null;

    public void init(ServletConfig config) throws ServletException {


        super.init(config);

        ServletContext ctx = config.getServletContext();

        dao = (DAO) ctx.getAttribute("dao");
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        ArrayList<prenotazione> prenotazioni = dao.view_prenotazioni();
        response.setContentType("application/json");


        PrintWriter out = response.getWriter();

        Gson gson = new Gson();

        String s = gson.toJson(prenotazioni);
        out.println(s);
        //System.out.println("STRINGA JSON " + s);

    }

    public void destroy() {
    }
}
