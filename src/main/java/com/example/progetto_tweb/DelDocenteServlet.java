package com.example.progetto_tweb;

import com.google.gson.Gson;
import dao.DAO;
import dao.docente;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

@WebServlet(name = "DelDocenteServlet", value = "/DelDocenteServlet")
public class DelDocenteServlet extends HttpServlet {
    DAO dao = null;

    public void init(ServletConfig config) throws ServletException {

        super.init(config);

        ServletContext ctx = config.getServletContext();
        dao = (DAO) ctx.getAttribute("dao");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("application/json");
        PrintWriter out = response.getWriter();
        //System.out.println("===========Docente=================");
        Gson gson = new Gson(); // traduttore da e verso formato JSON

        ArrayList<docente> viewDoc = dao.view_docente();

        out.println(gson.toJson(viewDoc));
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //HttpSession s = request.getSession();

        response.setContentType("text/plain");
        PrintWriter out = response.getWriter();
        String message = null;

        try {
            String username_docente = request.getParameter("usernameDocente");
            //System.out.println("Username Docente: " + username_docente);

            if (username_docente != null && !username_docente.equals("")) {

                if (dao.del_docente(username_docente)) {
                    System.out.println();
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
}
