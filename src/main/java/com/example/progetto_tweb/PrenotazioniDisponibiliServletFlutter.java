package com.example.progetto_tweb;

import dao.DAO;
import dao.prenotazione;

import java.io.*;
import java.util.ArrayList;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.*;
import javax.servlet.annotation.*;

import com.google.gson.Gson;

@WebServlet(name = "PrenotazioniDisponibiliServletFlutter", value = "/PrenotazioniDisponibiliServletFlutter")
public class PrenotazioniDisponibiliServletFlutter extends HttpServlet {
    DAO dao = null;

    public void init(ServletConfig config) throws ServletException {


        super.init(config);

        ServletContext ctx = config.getServletContext();

        dao = (DAO) ctx.getAttribute("dao");
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        ArrayList<prenotazione> prenotazioni = dao.view_prenotazioni_prenotabili();
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