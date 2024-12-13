package Lab2Maven;

import org.junit.Test;

public class TopDownIntegration {

    @Test
    public void testAddStudentTopDown(){
        new AddStudent().checkName();
        new AddStudent().checkProfessor();
        new AddStudent().checkId();
        new AddStudent().checkGroup();
        new AddStudent().checkEmail();
        new AddStudent().checkDuplicates();
        new AddStudent().addStudent();
    }

    @Test
    public void testAddThemeTopDown(){
        testAddStudentTopDown();
        new AddTheme().addTheme();
        new AddTheme().checkDeadline();
        new AddTheme().checkDuplicates();
        new AddTheme().checkId();
        new AddTheme().checkSaptPrimire();
    }

    @Test
    public void testAddGradeTopDown(){
        testAddThemeTopDown();
        new AddGrade().addNota();
        new AddGrade().checkDeadline();
        new AddGrade().checkDuplicate();
        new AddGrade().checkMark();
        new AddGrade().checkSaptPrimire();
    }

    @Test
    public void integrationTest(){
        testAddGradeTopDown();
    }
}
