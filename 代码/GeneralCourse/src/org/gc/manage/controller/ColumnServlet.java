package org.gc.manage.controller;

import com.alibaba.fastjson.JSON;
import org.gc.manage.service.ColumnService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;

@WebServlet(name = "ColumnServlet", urlPatterns = "/Column")
public class ColumnServlet extends HttpServlet {
    ColumnService columnService = new ColumnService();
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String type = request.getParameter("type");

        if (type.equals("getAllColumns")) {
            try {
                List<HashMap<String, String>> list = columnService.getColumns(request);

                response.setContentType("text/html;charset=UTF-8");
                response.getWriter().print(JSON.toJSON(list));
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }else if (type.equals("getColumn")) {
            String level = request.getParameter("level");
            try {
                List<HashMap<String, String>> list = columnService.getColumnsByLevel(level, request);

                response.setContentType("text/html;charset=UTF-8");
                response.getWriter().print(JSON.toJSON(list));
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } else if (type.equals("getSonColumn")) {
            String parentId = request.getParameter("parentId");
            try {
                List<HashMap<String, String>> list = columnService.getColumnsByParentId(parentId, request);

                response.setContentType("text/html;charset=UTF-8");
                response.getWriter().print(JSON.toJSON(list));
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } else if (type.equals("getTheColumn")) {
            String id = request.getParameter("id");
            try {
                List<HashMap<String, String>> list = columnService.getColumnsById(id, request);

                response.setContentType("text/html;charset=UTF-8");
                response.getWriter().print(JSON.toJSON(list));
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } else if (type.equals("editColumn")) {
            String id = request.getParameter("id");
            String name = request.getParameter("name");
            try {
                columnService.editColumn(id, name, request);

            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
