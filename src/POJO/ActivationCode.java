package POJO;

public class ActivationCode {

	private int id;
	private String activationCode;
	private int isValid;
	private int studentId;
	
	public int getId() { return id; }
	public void setId(int id) { this.id = id; }
	
	public String getActivationCode() { return activationCode; }
	public void setActivationCode(String activationCode) { this.activationCode = activationCode; }
	
	public int getIsValid() { return isValid; }
	public void setIsValid(int isValid) { this.isValid = isValid; }
	
	public int getStudentId() { return studentId; }
	public void setStudentId(int studentId) { this.studentId = studentId;}
	
	
}
