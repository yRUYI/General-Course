package org.gc.manage.service;

import org.gc.manage.dao.util.DBUtil;
import org.gc.manage.entity.Employee;
import org.gc.manage.entity.Role;
import org.gc.manage.entity.Table;
import org.gc.manage.service.impl.EmployeeServiceImpl;

import javax.servlet.http.HttpServletRequest;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class EmployeeService implements EmployeeServiceImpl {
    public List<HashMap<String, String>> getAllEmployee(HttpServletRequest request) throws SQLException {
        List<HashMap<String, String>> list = new ArrayList<>();
        String sql ="SELECT\n" +
                "\temployee.id,\n" +
                "\temployee.NAME,\n" +
                "\tsex,\n" +
                "\tcreateTime,\n" +
                "\taccount,\n" +
                "\trole.NAME \n" +
                "FROM\n" +
                "\t" + Employee.class.getAnnotation(Table.class).name() + ",\n" +
                "\t" + Role.class.getAnnotation(Table.class).name() + " \n" +
                "WHERE\n" +
                "\temployee.roleID = role.id\n";

        ResultSet resultSet = DBUtil.query(sql);
        while (resultSet.next()) {
            HashMap<String, String> hashMap = new HashMap<>();
            hashMap.put("id", resultSet.getString(1));
            hashMap.put("name", resultSet.getString(2));
            hashMap.put("sex", resultSet.getString(3));
            hashMap.put("account", resultSet.getString(4));
            hashMap.put("createTime", resultSet.getString(5));
            hashMap.put("roleName", resultSet.getString(6));

            list.add(hashMap);
        }

        return list;
    }

}
