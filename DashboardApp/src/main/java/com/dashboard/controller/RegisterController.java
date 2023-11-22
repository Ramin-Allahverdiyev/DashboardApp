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

@WebServlet(name = "RegisterController", value = "/register")

public class RegisterController extends HttpServlet {
    private UserDaoImpl userDao;

    @Override
    public void init(){
        userDao=new UserDaoImpl();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.sendRedirect("register.jsp");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        register(req,resp);
    }
    private void register(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String name = req.getParameter("name");
        String surname = req.getParameter("surname");
        String age=req.getParameter("age");
        String email = req.getParameter("email");
        String password = req.getParameter("password");
        String department = req.getParameter("department");
        String gender=req.getParameter("gender");
        Employees employees = new Employees();
        employees.setName(name);
        employees.setSurname(surname);
        employees.setEmail(email);
        employees.setDepartment(department);
        employees.setGender(gender);

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


        employees.setAge(Integer.parseInt(age));


        try {
            userDao.register(employees);
            req.getSession().setAttribute("name", name);
            req.getSession().setAttribute("email", email);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        resp.sendRedirect("user/employee/actions?action=statistics");
    }
}
