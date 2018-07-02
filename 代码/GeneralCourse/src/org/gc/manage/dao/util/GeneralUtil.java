package org.gc.manage.dao.util;

import org.gc.manage.entity.Permission;
import org.gc.manage.entity.RolePermission;
import org.gc.manage.entity.SonModule;
import org.gc.manage.entity.Table;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class GeneralUtil {
    //创建长度为20个字符的id
    public static String createId() {
        StringBuilder ID = new StringBuilder();
        ID.append(System.currentTimeMillis());
        ID.append((int)(Math.random() * 10000000));

        for (int i = ID.length(); i < 20; i++) {
            ID.append(0);
        }

        return ID.toString();
    }

    //按照 yyyy-MM-dd hh:mm:ss 格式创建时间
    public static String createTime() {
        Date createTime = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        String time  =simpleDateFormat.format(createTime);

        return time;
    }



    public static void main(String[] args) throws SQLException {

        ResultSet resultSet = DBUtil.query("select id from " + Permission.class.getAnnotation(Table.class).name());
        while (resultSet.next()) {
            String id = createId();
            String sql = "insert into " + RolePermission.class.getAnnotation(Table.class).name() + "(id, roleId, permissionId) " +
                    "values('" + id + "', '15302463133562562266', '" + resultSet.getString(1) + "')";
            System.out.println(sql);
            DBUtil.execute(sql);
        }
    }
}
