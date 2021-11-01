package com.fresco.springboot.model;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Student {
	int rollNo;
	String name;
	String department;
	double attendance;

	public Student() {
		super();
	}

	public Student(int rollNo, String name, String department, double attendance) {
		super();
		this.rollNo = rollNo;
		this.name = name;
		this.department = department;
		this.attendance = attendance;
	}

	public int getRollNo() {
		return rollNo;
	}

	public void setRollNo(int rollNo) {
		this.rollNo = rollNo;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}

	public double getAttendance() {
		return attendance;
	}

	public void setAttendance(double attendance) {
		this.attendance = attendance;
	}

	@Override
	public String toString() {
		return "Student [rollNo=" + rollNo + ", name=" + name + ", department=" + department + ", attendance="
				+ attendance + "]";
	}
}
