package com.example.progetto_tweb;

import com.google.gson.Gson;
import dao.DAO;
import dao.corso_docente;

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

@WebServlet(name = "DelCorsoDocenteServlet", value = "/DelCorsoDocenteServlet")
public class DelCorsoDocenteServlet extends HttpServlet {
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
        System.out.println("===========CorsoDocente=================");
        Gson gson = new Gson(); // traduttore da e verso formato JSON

        ArrayList<corso_docente> viewCorDoc = dao.view_corso_docente();

        out.println(gson.toJson(viewCorDoc));
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //HttpSession s = request.getSession();

        response.setContentType("text/plain");
        PrintWriter out = response.getWriter();
        String message = null;

        try {
            String username_docente = request.getParameter("usernameDocente1");
            String nome_corso = request.getParameter("nomeCorso1");
            System.out.println("Username Docente: " + username_docente + "Nome Corso: " + nome_corso);

            if (nome_corso != null && !nome_corso.equals("") &&
                username_docente != null && !username_docente.equals("")) {

                if (dao.del_corso_docente(username_docente, nome_corso);) {
                    System.out.println();
                    message = ("Il docente: " + username_docente + " è stato eliminato");
                } else
                    message = ("Il docente: " + username_docente + " non è stato eliminato");

            } else
                message = "Dati Sbagliati, Riprova";

            System.out.println(message);
            out.println(new Gson().toJson(message));
            out.flush();
        } finally {
            out.close();
        }

    }
}
