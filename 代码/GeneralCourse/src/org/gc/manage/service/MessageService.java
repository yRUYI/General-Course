package org.gc.manage.service;

import org.gc.manage.dao.util.DBUtil;
import org.gc.manage.dao.util.GeneralUtil;
import org.gc.manage.entity.Employee;
import org.gc.manage.entity.Message;
import org.gc.manage.entity.Table;
import org.gc.manage.service.impl.MessageServiceImpl;

import javax.servlet.http.HttpServletRequest;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class MessageService implements MessageServiceImpl {
    public List<HashMap<String, String>> getAllMessage(HttpServletRequest request) throws SQLException {
        List<HashMap<String, String>> list = new ArrayList<>();

        String sql = "select * from " + Message.class.getAnnotation(Table.class).name();
        ResultSet resultSet = DBUtil.query(sql);
        while (resultSet.next()) {
            HashMap<String, String> hashMap = new HashMap<>();
            hashMap.put("id", resultSet.getString(1));
            hashMap.put("content", resultSet.getString(2));
            hashMap.put("createTime", resultSet.getString(3));
            hashMap.put("status", String.valueOf(resultSet.getInt(7)));

            list.add(hashMap);
        }

        return list;
    }

    public List<HashMap<String, String>> getMessageById(String id, HttpServletRequest request) throws SQLException {
        List<HashMap<String, String>> list = new ArrayList<>();

        String sql = "SELECT\n" +
                "\tmessage.id,\n" +
                "\tcontent,\n" +
                "\tcreateTime,\n" +
                "\treply,\n" +
                "\tNAME,\n" +
                "\treplyTime,\n" +
                "STATUS \n" +
                "FROM\n" +
                "\t" + Message.class.getAnnotation(Table.class).name() + "\n" +
                "\tLEFT JOIN " + Employee.class.getAnnotation(Table.class).name() + " ON message.employeeID = employee.id \n" +
                "WHERE\n" +
                "\tmessage.id = '" + id + "'";
        System.out.println(sql);
        ResultSet resultSet = DBUtil.query(sql);
        while (resultSet.next()) {
            HashMap<String, String> hashMap = new HashMap<>();
            hashMap.put("id", resultSet.getString(1));
            hashMap.put("content", resultSet.getString(2));
            hashMap.put("createTime", resultSet.getString(3));
            hashMap.put("reply", resultSet.getString(4));
            hashMap.put("name", resultSet.getString(5));
            hashMap.put("replyTime", resultSet.getString(6));
            hashMap.put("status", String.valueOf(resultSet.getInt(7)));

            list.add(hashMap);
        }

        return list;
    }

    public void updateMessageById(String id, String reply, String employeeId, HttpServletRequest request) throws SQLException {
        String time = GeneralUtil.createTime();
        String sql = "UPDATE " + Message.class.getAnnotation(Table.class).name() + " \n" +
                "SET reply = '" + reply + "', \n" +
                "replyTime = '" + time + "', \n" +
                "employeeId = '" + employeeId + "', \n" +
                "status = 1 \n" +
                "WHERE\n" +
                "\tid = '" + id + "'";
        System.out.println(sql);
        DBUtil.execute(sql);
    }

    public void deleteMessageById(String id, HttpServletRequest request) throws SQLException {
        String sql = "delete from " + Message.class.getAnnotation(Table.class).name() + " where id = '" + id + "'";
        System.out.println(sql);
        DBUtil.execute(sql);
    }
}
