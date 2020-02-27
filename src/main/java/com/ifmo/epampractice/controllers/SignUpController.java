package com.ifmo.epampractice.controllers;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@WebServlet(name = "SignUpController", urlPatterns = "/signup")
public class SignUpController extends HttpServlet {

    private static final DateTimeFormatter dateFormatter
            = DateTimeFormatter.ofPattern("dd.MM.yyyy");

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //TODO: display some kind of signup page or whatever
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        String email = req.getParameter("email");
        String password = req.getParameter("password"); //TODO: How do we get is as char[] array?
        String name = req.getParameter("name");
        String birthDateStr = req.getParameter("birthDate");
        String phone = req.getParameter("phone");
        String address = req.getParameter("address");

        LocalDate birthDate = LocalDate.parse(birthDateStr, dateFormatter);
        //TODO: merge SignUpService into current branch and call it here

        //TODO: display signup result
    }
}
