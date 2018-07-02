package org.gc.manage.controller;

import com.alibaba.fastjson.JSON;
import org.gc.manage.service.ArticleService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;

@WebServlet(name = "ArticleServlet", urlPatterns = "/Article")
public class ArticleServlet extends HttpServlet {
    ArticleService articleService = new ArticleService();
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String type = request.getParameter("type");

        System.out.println();
        if (type.equals("review")) {
            try {
                List<HashMap<String, String>> list = articleService.getReviewArticle(request);

                response.setContentType("text/html;charset=UTF-8");
                response.getWriter().print(JSON.toJSON(list));
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } else if (type.equals("manage")) {
            try {
                List<HashMap<String, String>> list = articleService.getArticle(request);

                response.setContentType("text/html;charset=UTF-8");
                response.getWriter().print(JSON.toJSON(list));
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } else if (type.equals("getArticleById")) {
            try {
                String id = request.getParameter("id");
                List<HashMap<String, String>> list = articleService.getArticleById(id, request);

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
