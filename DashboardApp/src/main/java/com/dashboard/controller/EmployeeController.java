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
import java.util.List;

@WebServlet(name = "EmpServlet", value = "/user/employee/actions")
public class EmployeeController extends HttpServlet {
    private UserDaoImpl userDao;

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
        String action = req.getParameter("action");

        if (action == null) {
            req.getRequestDispatcher("/emp-form.jsp").forward(req, resp);
        } else {
            try {
                switch (action) {
                    case "allemp":
                        selectAllOfEmployees(req,resp);
                        break;
                    case "edit":
                        showEditEmployee(req,resp);
                        break;
                    case "update":
                        updateEmp(req, resp);
                        break;
                    case "statistics":
                        statisticResult(req,resp);
                        break;
                    default:
                        resp.sendRedirect("login.jsp");
                }
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
    }
    private void selectAllOfEmployees (HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
        List<Employees> employees = userDao.selectAllEmployees();
        req.setAttribute("emps", employees);
        req.getRequestDispatcher("/emp.jsp").forward(req, resp);
    }
    private void showEditEmployee (HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        int id = Integer.parseInt(req.getParameter("id"));
        Employees employees = userDao.getEmployee(id);
        req.setAttribute("emps", employees);
        req.getRequestDispatcher("/emp-form.jsp").forward(req, resp);
    }

    private void updateEmp (HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id = Integer.parseInt(req.getParameter("id"));
        String name = req.getParameter("name");
        String surname = req.getParameter("surname");
        String age=req.getParameter("age");
        String email = req.getParameter("email");
        String password = req.getParameter("password");
        String department = req.getParameter("department");
        String gender=req.getParameter("gender");

        Employees employees = userDao.getEmployee(id);
        employees.setName(name);
        employees.setSurname(surname);
        employees.setGender(gender);
        employees.setEmail(email);
        employees.setAge(Integer.parseInt(age));
        employees.setDepartment(department);
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(password.getBytes());
            byte[] hash = md.digest();
            StringBuilder sb = new StringBuilder();
            for (byte b : hash) {
                sb.append(String.format("%02x", b & 0xff));
            }
            employees.setPassword(sb.toString());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        userDao.updateEmployee(employees);
        resp.sendRedirect(req.getContextPath() + "/user/employee");
    }

    private void statisticResult (HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        List<Employees> empls = userDao.percentageOfDep();
        req.setAttribute("numofemp",userDao.numberOfEmployees());
        req.setAttribute("stats", empls);
        req.getRequestDispatcher("/statistic.jsp").forward(req, resp);
    }
}
