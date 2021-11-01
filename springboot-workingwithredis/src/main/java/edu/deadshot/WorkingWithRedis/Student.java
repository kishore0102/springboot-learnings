package edu.deadshot.WorkingWithRedis;

import java.io.Serializable;

public class Student implements Serializable {

    private int rollno;
    private String studentName;
    private String email;
    private String mobile;

    public Student() {}

    public Student(int rollno, String studentName, String email, String mobile) {
        this.rollno = rollno;
        this.studentName = studentName;
        this.email = email;
        this.mobile = mobile;
    }

    public int getRollno() {
        return rollno;
    }

    public void setRollno(int rollno) {
        this.rollno = rollno;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    @Override
    public String toString() {
        return "Student{" +
                "rollno=" + rollno +
                ", studentName='" + studentName + '\'' +
                ", email='" + email + '\'' +
                ", mobile='" + mobile + '\'' +
                '}';
    }

}
