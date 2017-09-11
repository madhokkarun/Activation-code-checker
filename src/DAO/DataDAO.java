package DAO;

import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Random;

import CONNECTION.MySqlConnection;
import POJO.Student;

public class DataDAO {
	
	private static final int UNUSED_CODE = 0;
	private static final int USED_CODE = 1;
	
	private static final String INSERT_STUDENT = "insert into student(name, user_name) values(?, ?)";
	private static final String SELECT_LAST_STUDENT = "SELECT id FROM student ORDER BY id DESC LIMIT 1";
	private static final String INSERT_CODE = "insert into activation_code(code, isUsed, student) values(?, ?, ?)";
	private static final String CHECK_CODE = "select * from activation_code where student = ? and code = ? and isUsed = ?";
	private static final String GET_STUDENT_ID = "select id from student where user_name = ?";
	
	public static Boolean checkCode(String userName, String code)
	{
		Connection conn;
		Boolean isValid = false;
		
		try {
			int index = 1;
			ResultSet rs = null;
			conn = MySqlConnection.getConnection();
			int id = getStudentId(conn, userName);
			
			if(id > 0)
			{
				PreparedStatement ps = conn.prepareStatement(CHECK_CODE);
				ps.setInt(index++, id);
				ps.setString(index++, code);
				ps.setInt(index++, UNUSED_CODE);
				
				rs = ps.executeQuery();
				
				if(rs.next())
					isValid = true;
			}
			
			
			conn.close();
		} catch(SQLException e) {
			e.printStackTrace();
		}
		
		return isValid;
	}
	
	public static String insertStudent(String name, String userName)
	{
		Connection conn;
		String generatedCode = "";
		
		try {
			int index = 1;
			conn = MySqlConnection.getConnection();
			
			PreparedStatement ps = conn.prepareStatement(INSERT_STUDENT);
			
			ps.setString(index++, name);
			ps.setString(index++, userName);
			
			ps.execute();
			
			int lastStudentId = getLastStudentId(conn);
			generatedCode = getSaltString();
			insertActivationCode(conn, generatedCode, UNUSED_CODE, lastStudentId);
			
			conn.close();
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return generatedCode;
		
	}
	
	public static int getStudentId(Connection conn, String userName) throws SQLException
	{
		ResultSet rs = null;
		try {
			PreparedStatement ps = conn.prepareStatement(GET_STUDENT_ID);
			ps.setString(1, userName);
			rs = ps.executeQuery();
		} catch(Exception e)
		{
			
		}
		if(rs.next())
			return rs.getInt(1);
		else 
			return 0; 
	}
	
	public static int getLastStudentId(Connection conn) throws SQLException
	{
		ResultSet rs = null;
		
		try {
			
			PreparedStatement ps = conn.prepareStatement(SELECT_LAST_STUDENT);
			
			rs = ps.executeQuery();
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		rs.next();
		return rs.getInt(1);
	}
	
	public static void insertActivationCode(Connection conn, String code, int isUsed, int studentId) throws SQLException
	{
		try {
			int index = 1;
			
			PreparedStatement ps = conn.prepareStatement(INSERT_CODE);
			
			ps.setString(index++, code);
			ps.setInt(index++, isUsed);
			ps.setInt(index++, studentId);
			
			ps.execute();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private static String getSaltString() 
	{
        String SALTCHARS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
        StringBuilder salt = new StringBuilder();
        
        Random rnd = new Random();
        
        while (salt.length() < 10) { // length of the random string.
            int index = (int) (rnd.nextFloat() * SALTCHARS.length());
            salt.append(SALTCHARS.charAt(index));
        }
        String saltStr = salt.toString();
        return saltStr;
    }
	
	private static Student populateStudent(ResultSet rs)
	{
		Student student = new Student();
		int index = 1;
		
		try {
			student.setName(rs.getString(index++));
			student.setUserName(rs.getString(index++));
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return student;
	}
}
