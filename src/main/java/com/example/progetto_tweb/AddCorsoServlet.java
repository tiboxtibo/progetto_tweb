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

@WebServlet(name = "AddCorsoServlet", value = "/AddCorsoServlet")
public class AddCorsoServlet extends HttpServlet {

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
            String nome_corso = request.getParameter("nomeCorso");
            System.out.println("Nome corso: " + nome_corso);

            if (nome_corso != null && !nome_corso.equals("")) {

                if (dao.add_corso(nome_corso);) {
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
