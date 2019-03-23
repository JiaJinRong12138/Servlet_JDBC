package com.servlet;

import com.been.User;
import com.dao.DAO;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.List;

@WebServlet(name = "GetServlet", urlPatterns = "/getinfo", loadOnStartup = 10)
public class GetServlet extends HttpServlet {

    @Override
    public void init(ServletConfig config) throws ServletException {
        //初始化
        super.init(config);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        //修改编码
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=UTF-8");
        List<User> users = null;
        //调用查询
        try {
            DAO.createConn();
            users = DAO.getAllUser();
            DAO.close();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        PrintWriter pw = response.getWriter();
        StringBuffer sb = new StringBuffer();
        sb.append("<table align='center' border='1' cellspacing='0'>\r\n");
        sb.append("<tr><td>id</td><td>name</td><td>sex</td><td>Delete</td></tr>\r\n");
        String trFormat = "<tr><td>%d</td><td>%s</td><td>%s</td>" +
                "<td>" +
                "<a href='edit?id=%d'>edit</a>" +
                "</td>" +
                "<td>" +
                "<a href = 'deleteinfo?id=%d'>delete</a>" +
                "</td></tr>\r\n";
        for (User u:users
        ) {
            String str = String.format(trFormat, u.getId(), u.getName(), u.getSex(), u.getId(), u.getId());
            sb.append(str);
        }
        sb.append("</table>");
        pw.write(sb.toString());

    }
}
