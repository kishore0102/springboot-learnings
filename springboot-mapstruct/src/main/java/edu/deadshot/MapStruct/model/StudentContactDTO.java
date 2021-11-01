package edu.deadshot.MapStruct.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class StudentContactDTO {
    private String email;
    private String mobile;
}
