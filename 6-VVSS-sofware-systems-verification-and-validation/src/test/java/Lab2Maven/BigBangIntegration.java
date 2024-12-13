package Lab2Maven;

import org.junit.Test;

public class BigBangIntegration {

    @Test
    public void testAddStudent(){
        new AddStudent().checkName();
        new AddStudent().checkProfessor();
        new AddStudent().checkId();
        new AddStudent().checkGroup();
        new AddStudent().checkEmail();
        new AddStudent().checkDuplicates();
        new AddStudent().addStudent();
    }

    @Test
    public void testAddTheme(){
        new AddTheme().addTheme();
        new AddTheme().checkDeadline();
        new AddTheme().checkDuplicates();
        new AddTheme().checkId();
        new AddTheme().checkSaptPrimire();
    }

    @Test
    public void testAddGrade(){
        new AddGrade().addNota();
        new AddGrade().checkDeadline();
        new AddGrade().checkDuplicate();
        new AddGrade().checkMark();
        new AddGrade().checkSaptPrimire();
    }

    @Test
    public void integrationTest(){
        testAddStudent();
        testAddTheme();
        testAddGrade();
    }
}
