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

@WebServlet("/delete")
public class DeleteServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User user = (User) req.getSession().getAttribute("loggedInUser");
        JdbcUserDao jdbcUserDao = new JdbcUserDao(new DataSource().getBasicDataSourceTest());
        String id = req.getParameter("userIdForDelete");
        if (req.getParameter("successfullyDeleted") == null || Integer.valueOf(req.getParameter("successfullyDeleted")) != 1) {
            if (id == null) {
                req.getServletContext().getRequestDispatcher("/jsp/errorPage.jsp").forward(req, resp);
                return;
            }
        }
        if (user.getId().equals(Long.valueOf(id))) {
            req.getSession().setAttribute("errorDeleting", true);
            resp.sendRedirect("/home");
            return;
        }
        jdbcUserDao.remove(jdbcUserDao.findById(Long.valueOf(id)));
        req.getSession().setAttribute("successfullyDeleted", true);
        req.getSession().setAttribute("users", jdbcUserDao.findAll());
        resp.sendRedirect("/home");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }

}
