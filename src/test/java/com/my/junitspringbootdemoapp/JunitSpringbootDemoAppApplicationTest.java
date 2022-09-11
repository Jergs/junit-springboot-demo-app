package com.my.junitspringbootdemoapp;

import com.my.junitspringbootdemoapp.model.CollegeStudent;
import com.my.junitspringbootdemoapp.model.StudentGrades;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class JunitSpringbootDemoAppApplicationTest {

    private Integer count = 0;

    @Value("${info.app.name}")
    private String appInfo;
    @Value("${info.app.description}")
    private String appDescription;
    @Value("${info.app.version}")
    private String appVersion;
    @Value("${info.school.name}")
    private String schoolName;

    @Autowired
    private CollegeStudent collegeStudent;
    @Autowired
    private StudentGrades studentGrades;
    @Autowired
    private ApplicationContext applicationContext;

    @BeforeEach
    public void setUp() {
        count = count + 1;
        System.out.println("Testing: " + appInfo + " which is " + appDescription
                + " Version: " + appVersion + ". Execution of test method " + count);

        collegeStudent.setFirstName("Eric");
        collegeStudent.setLastName("Ruby");
        collegeStudent.setEmailAddress("someemail@gmail.com");
        studentGrades.setMathGradesResults(new ArrayList<>(List.of(100.0, 85.0, 76.50, 91.75)));
        collegeStudent.setStudentGrades(studentGrades);
    }

    @Test
    @DisplayName("Add grade results for student grades")
    public void addGradeResultsForStudentGrades() {
        assertEquals(353.25, studentGrades.addGradeResultsForSingleClass(
                collegeStudent.getStudentGrades().getMathGradesResults()
        ));
    }

    @Test
    @DisplayName("Add grade results for student grades not equal")
    public void addGradeResultsForStudentGradesNotEqual() {
        assertNotEquals(0.25, studentGrades.addGradeResultsForSingleClass(
                collegeStudent.getStudentGrades().getMathGradesResults()
        ));
    }

    @Test
    @DisplayName("Is grade greater")
    public void isGradeGreater() {
        assertTrue(studentGrades.isGradeGreater(90, 75), "Should be true");
    }

    @Test
    @DisplayName("Is grade greater false")
    public void isGradeGreaterAssertFalse() {
        assertFalse(studentGrades.isGradeGreater(89, 92), "Should be false");
    }

    @Test
    @DisplayName("Check null for student grades")
    public void checkNullForStudentGrades() {
        assertNotNull(studentGrades.checkNull(collegeStudent.getStudentGrades().getMathGradesResults()),
                "Should not be null");
    }

    @DisplayName("Create student without grades init")
    @Test
    public void createStudentWithoutGradesInit() {
        CollegeStudent studentTwo = applicationContext.getBean("collegeStudent", CollegeStudent.class);
        studentTwo.setFirstName("Chad");
        studentTwo.setLastName("Darby");
        studentTwo.setEmailAddress("someemail2@gmail.com");
        assertNotNull(studentTwo.getFirstName());
        assertNotNull(studentTwo.getLastName());
        assertNotNull(studentTwo.getEmailAddress());
        assertNull(studentTwo.getStudentGrades());
    }

    @DisplayName("Verify students are prototypes")
    @Test
    public void verifyStudentsArePrototypes() {
        CollegeStudent studentTwo = applicationContext.getBean("collegeStudent", CollegeStudent.class);
        assertNotSame(collegeStudent, studentTwo);
    }

    @DisplayName("Find grade point average")
    @Test
    public void findGradePointAverage() {
        assertAll("Testing all assertEquals",
                () -> assertEquals(353.25, studentGrades.addGradeResultsForSingleClass(
                        collegeStudent.getStudentGrades().getMathGradesResults())),
                () -> assertEquals(88.31, studentGrades.findGradePointAverage(
                        collegeStudent.getStudentGrades().getMathGradesResults()))
        );
    }

}
