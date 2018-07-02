package org.gc.manage.service;

import org.gc.manage.dao.util.DBUtil;
import org.gc.manage.entity.Columns;
import org.gc.manage.entity.Table;
import org.gc.manage.service.impl.ColumnServiceImpl;

import javax.servlet.http.HttpServletRequest;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ColumnService implements ColumnServiceImpl {
    public List<HashMap<String, String>> getColumns(HttpServletRequest request) throws SQLException {
        System.out.println("----获取所有栏目----");
        List<HashMap<String, String>> list = new ArrayList<HashMap<String, String>>();
        String sql = "select * from " + Columns.class.getAnnotation(Table.class).name();
        System.out.println("sql: " + sql);

        ResultSet resultSet = DBUtil.query(sql);
        while (resultSet.next()) {
            HashMap<String, String> hashMap = new HashMap<>();
            hashMap.put("id", resultSet.getString(1));
            hashMap.put("name", resultSet.getString(2));
            hashMap.put("level", String.valueOf(resultSet.getInt(3)));
            hashMap.put("parentId", resultSet.getString(4));

            list.add(hashMap);
        }

        return list;
    }

    public List<HashMap<String, String>> getColumnsByParentId(String parentId, HttpServletRequest request) throws SQLException {
        System.out.println("----获取所有栏目----");
        List<HashMap<String, String>> list = new ArrayList<HashMap<String, String>>();
        String sql = "select * from " + Columns.class.getAnnotation(Table.class).name() + " where parent = '" + parentId + "'";
        System.out.println("sql: " + sql);

        ResultSet resultSet = DBUtil.query(sql);
        while (resultSet.next()) {
            HashMap<String, String> hashMap = new HashMap<>();
            hashMap.put("id", resultSet.getString(1));
            hashMap.put("name", resultSet.getString(2));
            hashMap.put("level", String.valueOf(resultSet.getInt(3)));
            hashMap.put("parentId", resultSet.getString(4));

            list.add(hashMap);
        }

        return list;
    }

    public List<HashMap<String, String>> getColumnsByLevel(String level, HttpServletRequest request) throws SQLException {
        System.out.println("----获取所有栏目----");
        List<HashMap<String, String>> list = new ArrayList<HashMap<String, String>>();
        String sql = "select * from " + Columns.class.getAnnotation(Table.class).name() + " where level = " + level;
        System.out.println("sql: " + sql);

        ResultSet resultSet = DBUtil.query(sql);
        while (resultSet.next()) {
            HashMap<String, String> hashMap = new HashMap<>();
            hashMap.put("id", resultSet.getString(1));
            hashMap.put("name", resultSet.getString(2));
            hashMap.put("level", String.valueOf(resultSet.getInt(3)));
            hashMap.put("parentId", resultSet.getString(4));

            list.add(hashMap);
        }

        return list;
    }

    public List<HashMap<String, String>> getColumnsById(String id, HttpServletRequest request) throws SQLException {
        System.out.println("----获取所有栏目----");
        List<HashMap<String, String>> list = new ArrayList<HashMap<String, String>>();
        String sql = "select * from " + Columns.class.getAnnotation(Table.class).name() + " where id = '" + id + "'";
        System.out.println("sql: " + sql);

        ResultSet resultSet = DBUtil.query(sql);
        while (resultSet.next()) {
            HashMap<String, String> hashMap = new HashMap<>();
            hashMap.put("id", resultSet.getString(1));
            hashMap.put("name", resultSet.getString(2));
            hashMap.put("level", String.valueOf(resultSet.getInt(3)));

            list.add(hashMap);
        }

        return list;
    }

    public void editColumn(String id, String name, HttpServletRequest request) throws SQLException {
        System.out.println("----编辑栏目----");
        String sql = "update " + Columns.class.getAnnotation(Table.class).name() + " set name = '" + name + "' where id = '" + id + "'";
        System.out.println(sql);
        DBUtil.execute(sql);
    }

}
