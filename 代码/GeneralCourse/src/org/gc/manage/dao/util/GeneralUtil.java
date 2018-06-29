package org.gc.manage.dao.util;

import java.sql.ResultSet;
import java.text.DateFormat;
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



    public static void main(String[] args) {
        String id = "15302469451248343357";
        String sonId1 = createId();
        DBUtil.execute("insert into sonModule(id, name, status, parentID) values('" + sonId1 + "', '权限管理', 1, '" + id + "')");
        String sonId2 = createId();
        DBUtil.execute("insert into sonModule(id, name, status, parentID) values('" + sonId2 + "', '模块管理', 1, '" + id + "')");
        String sonId3 = createId();
        DBUtil.execute("insert into sonModule(id, name, status, parentID) values('" + sonId3 + "', '人员管理', 1, '" + id + "')");
        String sonId4 = createId();
        DBUtil.execute("insert into sonModule(id, name, status, parentID) values('" + sonId4 + "', '角色管理', 1, '" + id + "')");
        String sonId5 = createId();
        DBUtil.execute("insert into sonModule(id, name, status, parentID) values('" + sonId5 + "', '角色分配', 1, '" + id + "')");


    }
}
