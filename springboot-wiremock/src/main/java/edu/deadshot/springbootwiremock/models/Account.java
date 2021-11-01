package edu.deadshot.springbootwiremock.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Account {

    private String accountNum;
    private String phoneNum;
    private String accountName;

}
