package org.gc.manage.controller;

import com.alibaba.fastjson.JSON;
import org.gc.manage.service.ColumnService;
import org.gc.manage.service.OptionService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;

@WebServlet(name = "OptionServlet", urlPatterns = "/Option")
public class OptionServlet extends HttpServlet {
    OptionService optionService = new OptionService();
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String moduleType = request.getParameter("moduleType");

//        System.out.println(moduleType);
        if (moduleType.equals("留言管理")) {

        } else if (moduleType.equals("文章管理")) {

        } else if (moduleType.equals("文章审核")) {

        } else if (moduleType.equals("栏目管理")) {
            try {
                ColumnService columnService = new ColumnService();
                List<HashMap<String, String>> list = columnService.getColumns(request);

                response.setContentType("text/html;charset=UTF-8");
                response.getWriter().print(JSON.toJSON(list));
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } else if (moduleType.equals("权限管理")) {

        } else if (moduleType.equals("人员管理")) {

        } else if (moduleType.equals("角色管理")) {

        } else if (moduleType.equals("角色分配")) {

        } else if (moduleType.equals("模块管理")) {

        }



    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
