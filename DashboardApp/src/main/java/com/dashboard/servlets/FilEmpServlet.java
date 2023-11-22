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

@WebServlet(name ="FilEmpServlet", value = "/user/filtered")
public class FilEmpServlet extends HttpServlet {
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
        String department = req.getParameter("departmentss");
        String gender = req.getParameter("genderr");

        List<Employees> employees = userDao.filteredDataWithoutName(department,gender);
        req.setAttribute("emps", employees);
        req.getRequestDispatcher("/emp.jsp").forward(req, resp);

    }
}
