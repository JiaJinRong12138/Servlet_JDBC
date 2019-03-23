package com.service;

import com.been.User;
import com.dao.DAO;

import java.sql.SQLException;

public class DeleteInfoService {
    public static boolean deleteInfo(int id){
        User u = new User();
        u.setId(id);
        try {
            DAO.createConn();
            if(DAO.delete(u) == 0){
                DAO.close();
                return false;
            }
            System.out.println("删除数据成功");
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            try {
                DAO.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return false;
    }
}
