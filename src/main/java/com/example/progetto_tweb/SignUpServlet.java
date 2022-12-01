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
        request.setCharacterEncoding("UTF-8"); // per essere robusti rispetto a caratteri speciali (', etc)
        ServletContext ctx = getServletContext();
        String action = request.getParameter("action");
        RequestDispatcher rd = ctx.getRequestDispatcher("/index.html");
        if (action!=null) {
            rd = ctx.getRequestDispatcher("/signUp.html");
            rd.forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession s = request.getSession();

        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        String pulsante = request.getParameter("SignUpBtn");

        ServletContext ctx = getServletContext();
        RequestDispatcher rd = ctx.getRequestDispatcher("/index.html");

        String username_utente = request.getParameter("username");
        String password_utente = request.getParameter("password");

        if (username_utente != null && password_utente != null) {
            //se i dati sono corretti allora riporta alla pagina di login
            if(dao.add_utente(username_utente, password_utente)){
                out.println("<html><head>");
                out.println("<meta http-equiv = \"refresh\" content = \"0 ; url = index.html\"/>");
                out.println("</head></html>");
            }




        }
    }

    public void destroy() {
    }
}
