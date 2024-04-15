package com.servlet;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;

import com.DAO.TodoDAO;
import com.db.DBConnect;
import com.entity.TodoDtls;


@WebServlet("/update")
public class UpdateServlet extends HttpServlet {
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		int id = Integer.parseInt(req.getParameter("id"));
		String username = req.getParameter("username");
		String todo = req.getParameter("todo");
		String status = req.getParameter("status");
		
		System.out.print(id+" "+ username+ " "+ todo + " "+ status);
			
		
		TodoDAO dao = new TodoDAO(DBConnect.getConn());
		
		TodoDtls t = new TodoDtls();
		t.setId(id);
		t.setName(username);
		t.setTodo(todo);
		t.setStatus(status);
		
		boolean f = dao.updateTodo(t);
		HttpSession session = req.getSession();
		
		
		if(f) {
			session.setAttribute("sucMsg", "Todo Udpated Successfully");
			resp.sendRedirect("index.jsp");
		}
		else {
			session.setAttribute("failedMsg", "SmoeThing Wrong on server");
			resp.sendRedirect("add_todo.jsp");
		}
	}
}
