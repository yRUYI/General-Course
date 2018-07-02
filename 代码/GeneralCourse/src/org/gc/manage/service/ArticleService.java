package org.gc.manage.service;

import org.gc.manage.dao.util.DBUtil;
import org.gc.manage.entity.Article;
import org.gc.manage.entity.Column;
import org.gc.manage.entity.Employee;
import org.gc.manage.entity.Table;
import org.gc.manage.service.impl.ArticleServiceImpl;

import javax.servlet.http.HttpServletRequest;
import java.lang.annotation.Target;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ArticleService implements ArticleServiceImpl{
    public List<HashMap<String, String>> getArticle(HttpServletRequest request) throws SQLException {
        List<HashMap<String, String>> list = new ArrayList<>();

        String sql = "SELECT\n" +
                "\tarticle.id,\n" +
                "\ttitle,\n" +
                "\temployee.name,\n" +
                "\tcreateTime,\n" +
                "\tcolumns.NAME,\n" +
                "\twhetherTop \n" +
                "FROM\n" +
                "\tarticle,\n" +
                "\temployee,\n" +
                "\tcolumns\n" +
                "WHERE\n" +
                "\tauthor = employee.id \n" +
                "\tAND columnID = columns.id \n";

        System.out.println(sql);
        ResultSet resultSet = DBUtil.query(sql);
        while (resultSet.next()) {
            HashMap<String, String> hashMap = new HashMap<>();
            hashMap.put("id", resultSet.getString(1));
            hashMap.put("title", resultSet.getString(2));
            hashMap.put("author", resultSet.getString(3));
            hashMap.put("createTime", resultSet.getString(4));
            hashMap.put("columnName", resultSet.getString(5));
            hashMap.put("whether_top", String.valueOf(resultSet.getInt(6)));

            list.add(hashMap);
        }

        return list;
    }

    public List<HashMap<String, String>> getReviewArticle(HttpServletRequest request) throws SQLException {
        String sql = "SELECT\n" +
                "\tarticle.id,\n" +
                "\ttitle,\n" +
                "\temployee.name,\n" +
                "\tcreateTime,\n" +
                "\tcolumns.NAME,\n" +
                "\tstatus \n" +
                "FROM\n" +
                "\t" + Article.class.getAnnotation(Table.class).name() + ",\n" +
                "\t" + Employee.class.getAnnotation(Table.class).name() + ",\n" +
                "\t" + Column.class.getAnnotation(Table.class).name() + "\n" +
                "WHERE\n" +
                "\tauthor = employee.id \n" +
                "\tAND columnID = columns.id \n" +
                "\tAND status = 0 \n";
        System.out.println(sql);
        
        List<HashMap<String, String>> list = new ArrayList<>();
        ResultSet resultSet = DBUtil.query(sql);
        while (resultSet.next()) {
            HashMap<String, String> hashMap = new HashMap<>();
            hashMap.put("id", resultSet.getString(1));
            hashMap.put("title", resultSet.getString(2));
            hashMap.put("author", resultSet.getString(3));
            hashMap.put("createTime", resultSet.getString(4));
            hashMap.put("columnName", resultSet.getString(5));
            hashMap.put("status", String.valueOf(resultSet.getInt(6)));

            list.add(hashMap);
        }

        return list;
    }

    public List<HashMap<String, String>> getArticleById(String id, HttpServletRequest request) throws SQLException {
        String sql = "";
        System.out.println(sql);

        List<HashMap<String, String>> list = new ArrayList<>();
        ResultSet resultSet = DBUtil.query(sql);
        while (resultSet.next()) {
            HashMap<String, String> hashMap = new HashMap<>();
            hashMap.put("id", resultSet.getString(1));
            hashMap.put("title", resultSet.getString(2));
            hashMap.put("author", resultSet.getString(3));
            hashMap.put("createTime", resultSet.getString(4));
            hashMap.put("columnName", resultSet.getString(5));
            hashMap.put("status", String.valueOf(resultSet.getInt(6)));

            list.add(hashMap);
        }

        return list;
    }
}
