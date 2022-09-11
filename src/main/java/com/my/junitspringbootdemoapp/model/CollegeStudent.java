package com.my.junitspringbootdemoapp.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CollegeStudent implements Student {

    private String firstName;
    private String lastName;
    private String emailAddress;
    private StudentGrades studentGrades;


    @Override
    public String studentInformation() {
        return getFullName() + " " + getEmailAddress();
    }

    @Override
    public String getFullName() {
        return this.firstName + " " + this.lastName;
    }

    @Override
    public String toString() {
        return "CollegeStudent{" +
                "firstname='" + firstName + '\'' +
                ", lastname='" + lastName + '\'' +
                ", emailAddress='" + emailAddress + '\'' +
                ", studentGrades=" + studentGrades +
                '}';
    }
}
