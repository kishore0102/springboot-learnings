package edu.deadshot.MapStruct.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class Student {
    private int rollno;
    private String name;
    private String dob;
    private String email;
    private String mobile;
    private String course;
    private String leadName;
}
