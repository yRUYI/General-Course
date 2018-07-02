package org.gc.manage.service;

import org.gc.manage.dao.util.DBUtil;
import org.gc.manage.entity.Permission;
import org.gc.manage.entity.SonModule;
import org.gc.manage.entity.Table;
import org.gc.manage.service.impl.PermissionServiceImpl;

import javax.servlet.http.HttpServletRequest;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class PermissionService implements PermissionServiceImpl {
    public List<HashMap<String, String>> getAllPermission(HttpServletRequest request) throws SQLException {
        List<HashMap<String, String>> list = new ArrayList<>();
        String sql = "SELECT\n" +
                "\tpermission.id,\n" +
                "\tsonmodule.`name`,\n" +
                "\tcreateTime,\n" +
                "\tdescription \n" +
                "FROM\n" +
                "\t" + Permission.class.getAnnotation(Table.class).name() + ",\n" +
                "\t" + SonModule.class.getAnnotation(Table.class).name() + " \n" +
                "WHERE\n" +
                "\tmoduleID = sonmodule.id";

        ResultSet resultSet = DBUtil.query(sql);
        while (resultSet.next()) {
            HashMap<String, String> hashMap = new HashMap<>();
            hashMap.put("id", resultSet.getString(1));
            hashMap.put("name", resultSet.getString(2));
            hashMap.put("createTime", resultSet.getString(3));
            hashMap.put("description", resultSet.getString(4));

            list.add(hashMap);
        }

        return list;
    }
}
