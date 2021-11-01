package edu.deadshot.JpaSpecification.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class ComplexCondition {
    private String key;
    private String operator;
    private String value;
}
