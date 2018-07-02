package org.gc.manage.controller;

import com.alibaba.fastjson.JSON;
import org.gc.manage.service.PermissionService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;

@WebServlet(name = "PermissionServlet", urlPatterns = "/Permission")
public class PermissionServlet extends HttpServlet {
    PermissionService permissionService = new PermissionService();
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            List<HashMap<String, String>> list = permissionService.getAllPermission(request);

            response.setContentType("text/html;charset=UTF-8");
            response.getWriter().print(JSON.toJSON(list));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
