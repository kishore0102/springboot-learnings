package edu.deadshot.JpaSpecification.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class ComplexRequest {
    private String rule;
    private List<ComplexCondition> conditions;
}
