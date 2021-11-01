package edu.deadshot.MapStruct.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class StudentDTO {
    private int rollno;
    private String name;
    private StudentContactDTO studentContactDTO;
}
