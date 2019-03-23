package com.servlet;

import com.been.User;
import com.dao.DAO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

@WebServlet(name = "EditServlet", urlPatterns = "/edit")
public class EditServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        //设置编码
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html; charset=UTF-8");
        //获取ID
        int id = Integer.valueOf(request.getParameter("id"));
        User u = new User();
        try {
            u.setId(id);
            DAO.createConn();
            u = DAO.getUserById(u);
            System.out.println(u.toString());
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        PrintWriter pw = response.getWriter();
        StringBuffer sb = new StringBuffer();
        sb.append("<!DOCTYPE html>");
        sb.append("<form action='/updateinfo' method='post'>");
        sb.append("<input type='hidden' name='id' value='%d' /><br/>");
        sb.append("name:<input type='text' name='name' value='%s' /><br/>");
        sb.append("sex:<input type='text' name='sex' value='%s' /><br/>");
        sb.append("<input type='submit' value='更新' />");
        sb.append("</form>");

        String str = String.format(sb.toString(), u.getId(), u.getName(), u.getSex());
        pw.write(str);

    }
}
