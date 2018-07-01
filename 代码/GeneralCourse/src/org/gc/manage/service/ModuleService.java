package org.gc.manage.service;

import org.gc.manage.dao.util.DBUtil;
import org.gc.manage.entity.Module;
import org.gc.manage.entity.SonModule;
import org.gc.manage.entity.Table;
import org.gc.manage.service.impl.ModuleServiceImpl;

import javax.servlet.http.HttpServletRequest;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ModuleService implements ModuleServiceImpl {
    public List<HashMap<String, String>> getModules(HttpServletRequest request) throws SQLException {
        List<HashMap<String, String>> list = new ArrayList<HashMap<String, String>>();
        String sql = "select * from " + Module.class.getAnnotation(Table.class).name();
        System.out.println(sql);
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

    public List<HashMap<String, String>> getSonModules(String level, String parentId, HttpServletRequest request) throws SQLException {
        List<HashMap<String, String>> list = new ArrayList<HashMap<String, String>>();
        String sql = "select * from " + SonModule.class.getAnnotation(Table.class).name() + " where level = " + level + " and parentId = '" + parentId + "'";
        System.out.println(sql);
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
}
