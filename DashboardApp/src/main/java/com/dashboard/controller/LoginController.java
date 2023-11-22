package com.dashboard.controller;
import com.dashboard.dao.UserDaoImpl;
import com.dashboard.model.Employees;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.security.MessageDigest;

@WebServlet(name = "LoginController" , value = "/login")
public class LoginController extends HttpServlet {
    UserDaoImpl userDao=null;

    @Override
    public void init() throws ServletException {
        userDao=new UserDaoImpl();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.sendRedirect("login.jsp");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        checkUser(req, resp);
    }

    private void checkUser(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String email = req.getParameter("email");
        String password = req.getParameter("password");
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(password.getBytes());
            byte[] hash = md.digest();
            StringBuilder sb = new StringBuilder();
            for (byte b : hash) {
                sb.append(String.format("%02x", b & 0xff));
            }

            Employees employees = userDao.getUser(email, sb.toString());
            if (employees != null) {
                req.getSession().setAttribute("name", employees.getName());
                req.getSession().setAttribute("email", employees.getEmail());
                resp.sendRedirect("user/employee/actions?action=statistics");
//                req.getRequestDispatcher("statistic.jsp").forward(req, resp);
            } else {
                req.setAttribute("loginError", "Username or Password is incorrect!");
                req.getRequestDispatcher("login.jsp").forward(req, resp);
            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }


    }
}
