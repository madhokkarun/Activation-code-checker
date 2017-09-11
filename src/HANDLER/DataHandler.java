package HANDLER;

import com.google.gson.Gson;

import DAO.DataDAO;

public class DataHandler {
	public static String addStudent(String name, String userName)
	{
		return DataDAO.insertStudent(name, userName);
	}
	
	public static Boolean checkCode(String userName, String code)
	{
		return DataDAO.checkCode(userName, code);
	}

}
