package com.servlet;

import com.been.User;
import com.dao.DAO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet(name = "UpdateServlet", urlPatterns = "/updateinfo")
public class UpdateServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        User user = new User();
        user.setId(Integer.valueOf(request.getParameter("id")));
        user.setName(request.getParameter("name"));
        user.setSex(request.getParameter("sex"));
        try {
            DAO.createConn();
            if(DAO.update(user) == 0){
                System.out.println("更新失败");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }finally {
            try {
                DAO.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        response.sendRedirect("/getinfo");
    }
}
