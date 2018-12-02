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

@WebServlet("/home")
public class HomeServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        User loggedInUser = (User) req.getSession().getAttribute("loggedInUser");
        System.out.println(loggedInUser.getLogin());
        if (loggedInUser.getRoleId() == 1) {
            JdbcUserDao jdbcUserDao = new JdbcUserDao(new DataSource().getBasicDataSourceTest());
            req.setAttribute("users", jdbcUserDao.findAll());
            req.getServletContext().getRequestDispatcher("/jsp/admin-home.jsp").forward(req, resp);
            req.getSession().removeAttribute("successfullyDeleted");
            req.getSession().removeAttribute("errorDeleting");
            req.getSession().removeAttribute("successfullyUpdated");
        }
        if (loggedInUser.getRoleId() == 2) {
            req.getServletContext().getRequestDispatcher("/jsp/user-home.jsp").forward(req, resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }

}
