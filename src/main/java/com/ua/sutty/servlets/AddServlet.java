package com.ua.sutty.servlets;

import com.ua.sutty.domain.User;
import com.ua.sutty.repository.impl.JdbcUserDao;
import com.ua.sutty.utils.DataSource;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Date;
import java.time.LocalDate;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@WebServlet("/add")
public class AddServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getServletContext().getRequestDispatcher("/jsp/add.jsp").forward(req, resp);
        req.getSession().removeAttribute("passwordNotEquals");
        req.getSession().removeAttribute("incorrectDate");
        req.getSession().removeAttribute("loginNotPattern");
        req.getSession().removeAttribute("passwordNotPattern");
        req.getSession().removeAttribute("emailNotPattern");
        req.getSession().removeAttribute("firstNameNotPattern");
        req.getSession().removeAttribute("lastNameNotPattern");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = req.getParameter("id");
        String login = req.getParameter("login");
        String password = req.getParameter("password");
        String confirmPassword = req.getParameter("confirmPassword");
        String email = req.getParameter("email");
        String firstName = req.getParameter("firstName");
        String lastName = req.getParameter("lastName");
        String birthday = req.getParameter("birthday");
        String role = req.getParameter("role");

        Pattern pattern = Pattern.compile("^[a-zA-Z][a-zA-Z0-9-_\\.]{1,20}$");
        Matcher matcher = pattern.matcher(login);
        if (!matcher.matches()) {
            req.getSession().setAttribute("loginNotPattern", true);
            resp.sendRedirect("/add");
            return;
        }

        pattern = Pattern.compile("(?=^.{8,}$)((?=.*\\d)|(?=.*\\W+))(?![.\\n])(?=.*[A-Z])(?=.*[a-z]).*$");
        matcher = pattern.matcher(password);
        if (!matcher.matches()) {
            req.getSession().setAttribute("passwordNotPattern", true);
            resp.sendRedirect("/add");
            return;
        }

        if (!password.equals(confirmPassword)) {
            req.getSession().setAttribute("passwordNotEquals", true);
            resp.sendRedirect("/add");
            return;
        }

        pattern = Pattern.compile("\\w+([\\.-]?\\w+)*@\\w+([\\.-]?\\w+)*\\.\\w{2,4}");
        matcher = pattern.matcher(email);
        if (!matcher.matches()) {
            req.getSession().setAttribute("emailNotPattern", true);
            resp.sendRedirect("/add");
            return;
        }

        pattern = Pattern.compile("^[A-Z]{1}[a-z]{1,25}");
        matcher = pattern.matcher(firstName);
        if (!matcher.matches()) {
            req.getSession().setAttribute("firstNameNotPattern", true);
            resp.sendRedirect("/add");
            return;
        }

        pattern = Pattern.compile("^[A-Z]{1}[a-z]{1,25}");
        matcher = pattern.matcher(lastName);
        if (!matcher.matches()) {
            req.getSession().setAttribute("lastNameNotPattern", true);
            resp.sendRedirect("/add");
            return;
        }

        LocalDate localDate = Date.valueOf(birthday).toLocalDate();
        LocalDate now = LocalDate.now();
        if (localDate.isAfter(now)) {
            req.getSession().setAttribute("incorrectDate", true);
            resp.sendRedirect("/add");
            return;
        }

        JdbcUserDao jdbcUserDao = new JdbcUserDao(new DataSource().getBasicDataSourceTest());
        if (jdbcUserDao.findByLogin(login).getLogin() != null) {
            req.setAttribute("existLogin", true);
            req.getServletContext().getRequestDispatcher("/jsp/add.jsp").forward(req, resp);
            return;
        } else {
            req.getSession().removeAttribute("existLogin");
        }
        User user = new User(login, password, email, firstName, lastName, Date.valueOf(birthday), Long.valueOf(role));
        jdbcUserDao.create(user);
        req.setAttribute("successfullyAdded", 1);
        doGet(req, resp);
        req.getSession().removeAttribute("successfullyAdded");
    }

}
