package org.gc.manage.service;

import org.gc.manage.dao.util.DBUtil;
import org.gc.manage.entity.*;
import org.gc.manage.service.impl.ModuleServiceImpl;

import javax.servlet.http.HttpServletRequest;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ModuleService implements ModuleServiceImpl {
    public List<HashMap<String, String>> getModules( String employeeId, HttpServletRequest request) throws SQLException {
        List<HashMap<String, String>> list = new ArrayList<HashMap<String, String>>();
        String sql = "select * from " + Module.class.getAnnotation(Table.class).name() + " WHERE\n" +
                "\t`status` = 1 \n" +
                "\tAND id IN (\n" +
                "SELECT\n" +
                "\tmoduleID \n" +
                "FROM\n" +
                "\t" + Permission.class.getAnnotation(Table.class).name() + " \n" +
                "WHERE\n" +
                "\tid IN ( SELECT permissionID FROM " + RolePermission.class.getAnnotation(Table.class).name() + " WHERE roleId IN ( SELECT roleId FROM " + Employee.class.getAnnotation(Table.class).name() + " WHERE id = '" + employeeId + "' ) ) \n" +
                "\t)";
//        System.out.println(sql);
        ResultSet resultSet = DBUtil.query(sql);

        while (resultSet.next()) {
            HashMap<String, String> hashMap = new HashMap<String, String>();
            hashMap.put("id", resultSet.getString(1));
            hashMap.put("name", resultSet.getString(2));
            hashMap.put("status", String.valueOf(resultSet.getInt(3)));

            list.add(hashMap);
        }

        return list;
    }

    public List<HashMap<String, String>> getSonModules(String level, String parentId, String employeeId, HttpServletRequest request) throws SQLException {
        List<HashMap<String, String>> list = new ArrayList<HashMap<String, String>>();
        String sql = "select * from " + SonModule.class.getAnnotation(Table.class).name() + " where status = 1 and level = " + level + " and parentId = '" + parentId + "' AND id IN (\n" +
                "SELECT\n" +
                "\tmoduleID \n" +
                "FROM\n" +
                "\t" + Permission.class.getAnnotation(Table.class).name() + " \n" +
                "WHERE\n" +
                "\tid IN ( SELECT permissionID FROM " + RolePermission.class.getAnnotation(Table.class).name() + " WHERE roleId IN ( SELECT roleId FROM " + Employee.class.getAnnotation(Table.class).name() + " WHERE id = '" + employeeId + "' ) ) \n" +
                "\t)";
//        System.out.println(sql);
        ResultSet resultSet = DBUtil.query(sql);

        while (resultSet.next()) {
            HashMap<String, String> hashMap = new HashMap<String, String>();
            hashMap.put("id", resultSet.getString(1));
            hashMap.put("name", resultSet.getString(2));
            hashMap.put("status", String.valueOf(resultSet.getInt(3)));
            hashMap.put("parentId", resultSet.getString(4));
            hashMap.put("level", resultSet.getString(5));

            list.add(hashMap);
        }

        return list;
    }

    public List<HashMap<String, String>> getModuleById(String moduleID, HttpServletRequest request) throws SQLException {
        List<HashMap<String, String>> list = new ArrayList<>();

        String sql = "select * from " + SonModule.class.getAnnotation(Table.class).name() + " where id = '" + moduleID + "'";
        System.out.println(sql);
        ResultSet resultSet = DBUtil.query(sql);

        while (resultSet.next()) {
            HashMap<String, String> hashMap = new HashMap<>();
            hashMap.put("id", resultSet.getString(1));
            hashMap.put("name", resultSet.getString(2));
            hashMap.put("status", resultSet.getString(3));
            hashMap.put("parentId", resultSet.getString(4));
            hashMap.getOrDefault("level", resultSet.getString(5));

            list.add(hashMap);
        }

        return list;
    }

    public List<HashMap<String, String>> getModules(HttpServletRequest request) throws SQLException {
        List<HashMap<String, String>> list = new ArrayList<>();
        String sql = "select id, name, status, level from " + SonModule.class.getAnnotation(Table.class).name();
        ResultSet resultSet = DBUtil.query(sql);

        while (resultSet.next()) {
            HashMap<String, String> hashMap = new HashMap<>();
            hashMap.put("id", resultSet.getString(1));
            hashMap.put("name", resultSet.getString(1));
            hashMap.put("status", resultSet.getString(1));
            hashMap.put("level", resultSet.getString(1));

            list.add(hashMap);
        }

        return list;
    }
}
