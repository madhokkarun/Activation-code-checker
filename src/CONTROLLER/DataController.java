package CONTROLLER;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import HANDLER.DataHandler;

/**
 * Servlet implementation class DataController
 */
@WebServlet("/DataController")
public class DataController extends HttpServlet {
	private static final long serialVersionUID = 1L;
   
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String res = manageRequest(request);
		
		PrintWriter out = response.getWriter();
		out.write(res);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
	
	private String manageRequest(HttpServletRequest request)
	{
		Boolean isAjax = Boolean.valueOf(request.getParameter("isAjax"));
		String response = "";
		
		if(isAjax)
			response = performAjaxOperation(request);
		
		return response;
	}
	private String performAjaxOperation(HttpServletRequest request)
	{
		Boolean isAddStudent = Boolean.valueOf(request.getParameter("isAddStudent"));
		Boolean isCheckCode = Boolean.valueOf(request.getParameter("isCheckCode"));
		
		String response = "";
		
		if(isAddStudent)
		{
			String name = request.getParameter("name");
			String userName = request.getParameter("userName");
			
			response = DataHandler.addStudent(name, userName);
		}
		else if(isCheckCode)
		{
			String userName = request.getParameter("userName");
			String code = request.getParameter("code");
			
			response = String.valueOf(DataHandler.checkCode(userName, code));
		}
		
		return response;
	}

}
