package com.service;

import com.been.User;
import com.dao.DAO;

import java.sql.SQLException;

public class AddInfoService {

    //获取数据
    //校验数据
    //进行数据整理
    //向DAO传递数据
    public static boolean insertValuye(int id, String name, String sex) throws SQLException, ClassNotFoundException {
        if(Integer.valueOf(id) instanceof Integer && name instanceof String && sex instanceof String){
            User u = new User();
            u.setId(id);
            u.setName(name);
            u.setSex(sex);
            DAO.createConn();
            if(DAO.add(u) == 0){
                DAO.close();
                return false;
            }
            DAO.close();
            return true;
        }
        return false;
    }



}
