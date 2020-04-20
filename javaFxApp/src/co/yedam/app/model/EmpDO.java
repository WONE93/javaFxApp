package co.yedam.app.model;

public class EmpDO { //DATA object

	String employeeId;
	String lastName;
	String email;
	String hireDate;
	String jobId;
	
	public String getEmployeeId() {
		return employeeId;
	}
	public void setEmployeeId(String employeeId) {
		this.employeeId = employeeId;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getHireDate() {
		return hireDate;
	}
	public void setHireDate(String hireDate) {
		this.hireDate = hireDate;
	}
	public String getJobId() {
		return jobId;
	}
	public void setJobId(String jobId) {
		this.jobId = jobId;
	}
	@Override
	public String toString() {
		return "EmpDO [employeeId=" + employeeId + ", lastName=" + lastName + ", email=" + email + ", hireDate="
				+ hireDate + ", jobId=" + jobId + "]";
	}
	

	

	
}
