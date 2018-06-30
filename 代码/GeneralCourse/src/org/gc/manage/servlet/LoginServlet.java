package org.gc.manage.servlet;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class LoginServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        System.out.println(request.getParameter("username"));
        System.out.println(request.getParameter("password"));

        response.sendRedirect("html/main.html");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {

    }
}
