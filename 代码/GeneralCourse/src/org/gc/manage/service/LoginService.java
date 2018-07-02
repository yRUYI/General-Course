package org.gc.manage.service;

import org.gc.manage.dao.util.DBUtil;
import org.gc.manage.entity.Employee;
import org.gc.manage.entity.Table;
import org.gc.manage.service.impl.LoginServiceImpl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class LoginService implements LoginServiceImpl {
    public int login(String username, String password, HttpServletRequest request) throws SQLException {
        if (username == null || username.isEmpty()) {
            return USERNAMEISNULL;
        } else {
            String sql  ="select * from " + Employee.class.getAnnotation(Table.class).name() + " where account = '"  + username + "'";
            System.out.println(sql);
            ResultSet resultSet = DBUtil.query(sql);
            while (resultSet.next()) {
                if (password.equals(resultSet.getString(5))) {
                    HttpSession httpSession = request.getSession();
                    httpSession.setAttribute("username",username);
                    httpSession.setAttribute("employeeId", resultSet.getString(1));
                    return SUCCESS;
                }
            }

            return USERNAMENOTEXIST;
        }
    }

    public List<HashMap<String, String>> getUserName(HttpServletRequest request) {
        HttpSession session = request.getSession();
        if (session.getAttribute("username") == null) {
            return null;
        }

        List<HashMap<String, String>> list = new ArrayList<HashMap<String, String>>();
        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("username", (String) session.getAttribute("username"));
        hashMap.put("employeeId", (String) session.getAttribute("employeeId"));
        list.add(hashMap);

        return list;
    }
}
