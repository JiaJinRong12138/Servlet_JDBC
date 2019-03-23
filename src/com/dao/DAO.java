package com.dao;

import com.Util.PrimaryKeyException;
import com.been.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.SplittableRandom;

//提供数据库操作的方法
public class DAO {

    //定义参数
    private static String driver;
    private static String url;
    private static String username;
    private static String password;
    private static Connection conn = null;
    private static PreparedStatement ps = null;
    private static ResultSet rs = null;

    //初始化
    private static void init(){
        driver = "com.mysql.cj.jdbc.Driver";
        url = "jdbc:mysql://localhost:3306/javaend";
        username = "root";
        password = "1006";
    }

    //判断参数是否合法
    private static Boolean isOk(User user){
        //value instanceof type : 比较参数是否为该数据类型
        if(user.getId() instanceof  Integer &&
                ((user.getName() instanceof String) || user.getName() == null) &&
                ((user.getSex() instanceof String) || (user.getSex() == null) )){
            return true;
        }
        return false;
    }

    //注册驱动
    private static void loadDriver() throws ClassNotFoundException {
        init();
        Class.forName(driver);
    }

    //创建链接
    public static void createConn() throws SQLException, ClassNotFoundException {
        loadDriver();
        conn = DriverManager.getConnection(url, username, password);
    }

    //增
    public static int add(User user) throws SQLException {
        String sql = "insert into user value (?, ?, ?)";
        if(isOk(user)){
            ps = conn.prepareStatement(sql);
            ps.setInt(1, user.getId());
            ps.setString(2, user.getName());
            ps.setString(3, user.getSex());
        }
        int num = 0;
        try {
            num = ps.executeUpdate();
        }catch (Exception e) {
            num = 0;
        }

        return num;
    }
    //删
    public static int delete(User user) throws SQLException {
        String sql = "delete from user where id = ?";
        ps = conn.prepareStatement(sql);
        if(isOk(user)){
            ps.setInt(1, user.getId());
        }
        return ps.executeUpdate();
    }

    //查询
    public static List<User> getAllUser() throws SQLException {
        List<User> users = new ArrayList<>();
        String sql = "SELECT * from user";
        ps = conn.prepareStatement(sql);
        rs = ps.executeQuery();
        while (rs.next()){
            User u = new User();
            u.setId(rs.getInt(1));
            u.setName(rs.getString(2));
            u.setSex(rs.getString(3));
            users.add(u);
        }
        return users;

    }
    public static User getUserById(User user) throws SQLException {
        String sql = "SELECT * from user where id = ?";
        ps = conn.prepareStatement(sql);
        if(isOk(user)){
            ps.setInt(1, user.getId());
        }
        rs = ps.executeQuery();
        while (rs.next()){

            user.setId(rs.getInt(1));
            user.setName(rs.getString(2));
            user.setSex(rs.getString(3));
        }
        return user;

    }

    //改
    public static int update(User user) throws SQLException {
//        update中出现and/or关键字应该用‘，’来代替
        String sql = "update user set name = ? , sex = ? where id = ?";
        if(isOk(user)){
            ps = conn.prepareStatement(sql);
            ps.setString(1, user.getName());
            ps.setString(2, user.getSex());
            ps.setInt(3, user.getId());
        }

        return ps.executeUpdate();
    }

    //关闭
    public static void close() throws SQLException {
        if(rs != null){
            rs.close();
            System.out.println("ResuleSte 关闭成功");
        }
        if(ps != null){
            ps.close();
            System.out.println("PreparedStatement 关闭成功");
        }
        if(conn != null){
            conn.close();
            System.out.println("Connection 关闭成功");
        }
    }

}
