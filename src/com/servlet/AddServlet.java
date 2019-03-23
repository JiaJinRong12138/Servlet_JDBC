package com.servlet;

import com.service.AddInfoService;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet(name = "AddServlet", urlPatterns = "/addinfo", loadOnStartup = 10)
public class AddServlet extends HttpServlet {
    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        //接受参数，传递给service
        request.setCharacterEncoding("utf-8");
        int id = Integer.valueOf(request.getParameter("id"));
        String name = request.getParameter("name");
        String sex = request.getParameter("sex");
        System.out.println(id + "\t" + name + "\t" + sex);
        try {
            if(AddInfoService.insertValuye(id, name, sex)){
                System.out.println("数据插入成功");
                id = 0;
                name = null;
                sex = null;
                request.getRequestDispatcher("index.html").forward(request, response);
            }else {
                response.setContentType("text/html;charset=UTF-8");
                response.getWriter().print("数据插入失败");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}
