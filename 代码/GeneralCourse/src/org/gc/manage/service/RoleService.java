package org.gc.manage.service;

import org.gc.manage.dao.util.DBUtil;
import org.gc.manage.entity.Role;
import org.gc.manage.entity.Table;
import org.gc.manage.service.impl.RoleServiceImpl;

import javax.servlet.http.HttpServletRequest;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class RoleService implements RoleServiceImpl {
    public List<HashMap<String, String>> getAllRole(HttpServletRequest r ) throws SQLException {
        List<HashMap<String, String>> list = new ArrayList<>();
        String sql = "select * from " + Role.class.getAnnotation(Table.class).name();
        ResultSet resultSet = DBUtil.query(sql);

        while (resultSet.next()) {
            HashMap<String, String> hashMap = new HashMap<>();
            hashMap.put("id", resultSet.getString(1));
            hashMap.put("name", resultSet.getString(2));
            hashMap.put("createTime", resultSet.getString(3));

            list.add(hashMap);
        }

        return list;
    }
}
