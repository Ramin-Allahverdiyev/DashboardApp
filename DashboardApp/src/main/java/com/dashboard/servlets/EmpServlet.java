package com.dashboard.servlets;

import com.dashboard.dao.UserDaoImpl;
import com.dashboard.model.Employees;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "EmppServlet", value = "/user/employee")
public class EmpServlet extends HttpServlet {
    UserDaoImpl userDao;

    @Override
    public void init() throws ServletException {
        userDao=new UserDaoImpl();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String email = (String) req.getSession().getAttribute("email");
        List<Employees> employees = userDao.selectAllEmployees(email);
        req.setAttribute("emps", employees);
        req.getRequestDispatcher("/emp.jsp").forward(req, resp);

    }
}
