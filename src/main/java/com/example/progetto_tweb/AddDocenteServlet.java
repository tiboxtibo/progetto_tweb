package com.example.progetto_tweb;

import com.google.gson.Gson;
import dao.DAO;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "AddDocenteServlet", value = "/AddDocenteServlet")
public class AddDocenteServlet extends HttpServlet {

    DAO dao = null;

    public void init(ServletConfig config) throws ServletException {

        super.init(config);

        ServletContext ctx = config.getServletContext();
        dao = (DAO) ctx.getAttribute("dao");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //HttpSession s = request.getSession();

        response.setContentType("text/plain");
        PrintWriter out = response.getWriter();
        String message = null;

        try {
                String nome_docente = request.getParameter("nomeDocente");
                String cognome_docente = request.getParameter("cognomeDocente");
                String username_docente = request.getParameter("usernameDocente");
            System.out.println("Nome docente: " + nome_docente + " Cognome Docente: " + cognome_docente + "Username Docente: " + username_docente);

                if (nome_docente != null && !nome_docente.equals("") &&
                    cognome_docente != null && !cognome_docente.equals("") &&
                    username_docente != null && !username_docente.equals("")) {

                    if (dao.add_docente(nome_docente, cognome_docente, username_docente)) {
                        System.out.println();
                        message = "Valori Inseriti Correttamente";
                    } else
                        message = "Valori Non Inseriti";

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
