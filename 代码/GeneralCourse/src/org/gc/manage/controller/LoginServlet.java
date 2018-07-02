package org.gc.manage.controller;

import com.alibaba.fastjson.JSON;
import org.gc.manage.service.LoginService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;
import java.util.AbstractList;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class LoginServlet extends HttpServlet {
    private LoginService login = new LoginService();
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        try {
            int type = login.login(username, password, request);
            if (type == 0) {
                response.sendRedirect ("html/test.html");
            } else {
                response.sendRedirect("html/login.html");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        List<HashMap<String, String>> list = login.getUserName(request);
        if (list == null) {
            response.sendRedirect("html/login.html");
        } else {
            response.setContentType("text/html;charset=UTF-8");
            response.getWriter().print(JSON.toJSON(list));
        }
    }
}
