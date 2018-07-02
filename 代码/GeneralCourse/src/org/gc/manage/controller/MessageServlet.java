package org.gc.manage.controller;

import com.alibaba.fastjson.JSON;
import org.gc.manage.service.MessageService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;

@WebServlet(name = "MessageServlet", urlPatterns = "/Message")
public class MessageServlet extends HttpServlet {
    MessageService messageService = new MessageService();
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String type = request.getParameter("type");

        if (type.equals("look")) {
            try {
                String id = request.getParameter("id");
                List<HashMap<String, String>> list = messageService.getMessageById(id, request);

                response.setContentType("text/html;charset=UTF-8");
                response.getWriter().print(JSON.toJSON(list));
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } else if (type.equals("update")) {
            String id = request.getParameter("id");
            String reply = request.getParameter("reply");
            String employeeId = request.getParameter("employeeId");

            try {
                messageService.updateMessageById(id, reply, employeeId, request);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } else if (type.equals("delete")) {
            String id = request.getParameter("id");
            try {
                messageService.deleteMessageById(id, request);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }



    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            List<HashMap<String, String>> list = messageService.getAllMessage(request);

            response.setContentType("text/html;charset=UTF-8");
            response.getWriter().print(JSON.toJSON(list));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
