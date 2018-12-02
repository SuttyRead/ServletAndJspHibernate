package com.ua.sutty.filters;

import com.ua.sutty.domain.User;
import com.ua.sutty.utils.AvailableUrl;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Objects;

@WebFilter("/*")
public class SecurityFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        AvailableUrl availableUrl = new AvailableUrl();
        List<String> allUrl = availableUrl.getAllUrl();
        List<String> urlForUser = availableUrl.getUrlForUser();
        List<String> urlForGuest = availableUrl.getUrlForGuest();

        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        String servletPath = request.getServletPath();
        User user = (User) request.getSession().getAttribute("loggedInUser");

        if (!allUrl.contains(servletPath)) {
            request.getServletContext().getRequestDispatcher("/jsp/errorPage.jsp").forward(request, response);
            return;
        }
        if (user == null) {
            if (!urlForGuest.contains(servletPath)) {
                response.sendRedirect("/login");
            } else {
                filterChain.doFilter(request, response);
            }
        } else {
            request.setAttribute("userLogin", user.getLogin());
            if (servletPath.equals("/login")) {
                response.sendRedirect("/home");
                return;
            }
            if (user.getRoleId() == 1) {
                filterChain.doFilter(request, response);
            } else {
                if (!urlForUser.contains(servletPath)) {
                    request.getServletContext().getRequestDispatcher("/jsp/accessDenied.jsp").forward(request, response);
                } else {
                    filterChain.doFilter(request, response);
                }
            }
        }
    }

    @Override
    public void destroy() {

    }
}
