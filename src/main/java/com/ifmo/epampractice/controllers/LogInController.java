package com.ifmo.epampractice.controllers;

import com.ifmo.epampractice.dao.UsersDao;
import com.ifmo.epampractice.daoimpl.UsersDaoImpl;
import com.ifmo.epampractice.entity.UsersEntity;
import com.ifmo.epampractice.security.SecureString;
import com.ifmo.epampractice.serviceimpl.LogInServiceImpl;
import com.ifmo.epampractice.services.LogInService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(name = "LogInController", urlPatterns = "/login")
public class LogInController extends HttpServlet {
    private LogInService logInService = new LogInServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        RequestDispatcher dispatcher = req.getRequestDispatcher("login.jsp");
        dispatcher.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws IOException {

        String email = req.getParameter("email");
        String passwordStr = req.getParameter("password");

        SecureString password = new SecureString();
        password.append(passwordStr.toCharArray());

        if (!logInService.getAuth(email, password)) {
            resp.sendRedirect("/login?error=1");
            return;
        }
        HttpSession session = req.getSession();
        session.setAttribute("name", email);

        UsersDao usersDao = new UsersDaoImpl();
        UsersEntity usersEntity = usersDao.getByEmail(email);
        resp.sendRedirect("/login?id=" + usersEntity.getId());
    }
}

