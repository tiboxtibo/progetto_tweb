package com.example.progetto_tweb;

import dao.DAO;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;

@WebServlet(name = "SignUpServlet", value = "/SignUpServlet")
public class SignUpServlet extends HttpServlet {

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
        HttpSession s = request.getSession();

        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        String pulsante = request.getParameter("SignUpBtn");

        ServletContext ctx = getServletContext();
        RequestDispatcher rd = ctx.getRequestDispatcher("/index.html");

        String username_utente = request.getParameter("usrNew");
        String password_utente = request.getParameter("pswNew");

        if (username_utente != null && password_utente != null) {

            dao.add_utente(username_utente, password_utente);

            out.println("<html><body>");
            out.println("<h3> Dati aggiunti </h3>");
            out.println("<a href= index.html = get >Torna alla HomePage</a>");
            out.println("<a href= hello-servlet methods= get >Visualizza le prenotazioni</a>");
            out.println("</body></html>");

        } else {
            out.println("<html><body>");
            out.println("<h3> Dati sbagliati </h3>");
            out.println("<a href= index.html = get >Torna alla HomePage</a>");
            out.println("</body></html>");
        }
    }

    public void destroy() {
    }
}
