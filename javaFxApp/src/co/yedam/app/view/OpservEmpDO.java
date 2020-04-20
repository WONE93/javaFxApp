package co.yedam.app.view;

import javafx.beans.property.SimpleStringProperty;

public class OpservEmpDO {
	
	public SimpleStringProperty employeeId = new  SimpleStringProperty();
	public SimpleStringProperty lastName = new  SimpleStringProperty();
	public SimpleStringProperty email = new  SimpleStringProperty();
	public SimpleStringProperty hireDate = new  SimpleStringProperty();
	public SimpleStringProperty jobId = new  SimpleStringProperty();
	
	public String getEmployeeId() {
		return employeeId.get();
	}
//	public void setEmployeeId(SimpleStringProperty employeeId) {
//		this.employeeId = employeeId;
//	}   
	
	public void setEmployeeId(String employeeId) {
		this.employeeId.set(employeeId);
	}
	
	public String getLastName() {
		return lastName.get();
	}
	public void setLastName(String lastName) {
		this.lastName.set(lastName);;
	}
	
	public String getEmail() {
		return email.get();
	}
	public void setEmail(String email) {
		this.email.set(email);;
	}
	public String getHireDate() {
		return hireDate.get();
	}
	public void setHireDate(String hireDate) {
		this.hireDate.set(hireDate);
	}
	public String getJobId() {
		return jobId.get();
	}
	public void setJobId(String jobId) {
		this.jobId.set(jobId);;
	}
	
}
