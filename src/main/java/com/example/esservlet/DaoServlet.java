package com.example.esservlet;

import dao.DAO;
import dao.docenti;

import java.io.*;
import java.util.ArrayList;
import java.util.Objects;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.*;
import javax.servlet.annotation.*;




@WebServlet(name = "DaoServlet", value = "/dao-servlet", loadOnStartup = 1, asyncSupported = true)
public class DaoServlet extends HttpServlet {
    DAO dao = null;

    public void init(ServletConfig config) throws ServletException {


        super.init(config);
        ServletContext ctx = config.getServletContext();
        String url = ctx.getInitParameter("DB-URL");
        String user = ctx.getInitParameter("user");
        String password = ctx.getInitParameter("password");

        dao = new DAO(url,user,password);
        ctx.setAttribute("dao",dao);

    }


    public void destroy() {
    }
}