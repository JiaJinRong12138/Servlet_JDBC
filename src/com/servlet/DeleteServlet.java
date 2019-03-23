package com.servlet;

import com.been.User;
import com.dao.DAO;
import com.service.DeleteInfoService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.List;

@WebServlet(name = "DeleteServlet", urlPatterns = "/deleteinfo", loadOnStartup = 10)
public class DeleteServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println(request.getParameter("id"));
        DeleteInfoService.deleteInfo(Integer.valueOf(request.getParameter("id")));
        response.sendRedirect("/getinfo");
    }
}
