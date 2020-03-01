package com.ifmo.epampractice.controllers;

import com.ifmo.epampractice.dao.UsersDao;
import com.ifmo.epampractice.daoimpl.UsersDaoImpl;
import com.ifmo.epampractice.enums.UserRole;
import com.ifmo.epampractice.security.SecureString;
import com.ifmo.epampractice.serviceimpl.PasswordHashServiceImpl;
import com.ifmo.epampractice.serviceimpl.SignUpServiceImpl;
import com.ifmo.epampractice.services.PasswordHashService;
import com.ifmo.epampractice.services.SignUpService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@WebServlet(name = "SignUpController", urlPatterns = "/signup")
public class SignUpController extends HttpServlet {

    private static UsersDao usersDao = new UsersDaoImpl();
    private static PasswordHashService passwordHashService = new PasswordHashServiceImpl();
    private static SignUpService signUpService = new SignUpServiceImpl(usersDao, passwordHashService);

    private static final BigDecimal INITIAL_REPUTATION = BigDecimal.valueOf(1.0);

    private static final DateTimeFormatter dateFormatter
            = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        RequestDispatcher dispatcher = req.getRequestDispatcher("signup.jsp");
        dispatcher.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws IOException {

        String email = req.getParameter("email");
        String passwordStr = req.getParameter("password"); //TODO: How do we get is as char[] array?
        String name = req.getParameter("name");
        String birthDateStr = req.getParameter("birthDate");
        String phone = req.getParameter("phone");
        String address = req.getParameter("address");

        SecureString password = new SecureString();
        password.append(passwordStr.toCharArray());

        LocalDate birthDate = LocalDate.parse(birthDateStr, dateFormatter);

        signUpService.signUp(email, password, name, birthDate, phone, address,
                UserRole.CLIENT, INITIAL_REPUTATION);

        resp.sendRedirect("/signup?success=1");
    }
}
