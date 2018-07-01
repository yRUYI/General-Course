package org.gc.manage.controller;

import com.alibaba.fastjson.JSON;
import org.gc.manage.service.ModuleService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;

@WebServlet(name = "ModuleServlet", urlPatterns = "/Module")
public class ModuleServlet extends HttpServlet {
    ModuleService moduleService = new ModuleService();
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String type = request.getParameter("type");

        if (type.equals("getSonModule")) {
            String level = request.getParameter("level");
            String parentId = request.getParameter("parentId");
            try {
                List<HashMap<String, String>> list = moduleService.getSonModules(level, parentId, request);

                response.setContentType("text/html;charset=UTF-8");
                response.getWriter().print(JSON.toJSON(list));
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } else if (type.equals("getModule")){
            List<HashMap<String, String>> list = null;
            try {
                list = moduleService.getModules(request);

                response.setContentType("text/html;charset=UTF-8");
                response.getWriter().print(JSON.toJSON(list));
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
