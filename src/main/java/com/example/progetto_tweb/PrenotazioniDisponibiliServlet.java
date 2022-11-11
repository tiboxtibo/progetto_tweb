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


@WebServlet(name = "PrenotazioniDisponibiliServlet", value = "/prenotazioni-disponibili-servlet")
public class PrenotazioniDisponibiliServlet extends HttpServlet {
    private String message;
    DAO dao = null;

    public void init(ServletConfig config) throws ServletException {


        super.init(config);

        ServletContext ctx = config.getServletContext();

        dao = (DAO) ctx.getAttribute("dao");
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        ArrayList<prenotazione> prenotazioni = dao.view_prenotazioni_prenotabili();
        response.setContentType("text/html");

        // Hello
        PrintWriter out = response.getWriter();
        out.println("<html><body>");
        out.println("<h1>" + message + "</h1>");
        for (prenotazione pren: prenotazioni) {
            out.println("<p>" + pren + "</p>");
        }
        out.println("</body></html>");
    }

    public void destroy() {
    }
}