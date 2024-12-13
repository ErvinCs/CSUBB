package Lab2Maven;

import Domain.Nota;
import Domain.Student;
import Domain.Teme;
import Repository.NoteRepo;
import Service.ServiceNote;
import Validator.NotaValidator;
import Validator.ValidationException;
import org.junit.Test;

import java.util.AbstractMap;
import java.util.Map;

import static org.junit.Assert.assertEquals;

public class AddGrade {
    private String dirPath = System.getProperty("user.dir");
    private NoteRepo rep = new NoteRepo(new NotaValidator());
    private ServiceNote srv = new ServiceNote(rep);


    @Test
    public void addNota(){
        Teme teme1 = new Teme(10,"dfgfdgfdg",1,10);
        Teme teme2 = new Teme(6,"dfgfdgfdg",1,10);
        Student student1 = new Student("1","name",932,"Lemne@.com","Prof");
        Student student2 = new Student("2","name",932,"Lemne@.com","Prof");
        Nota nota1 = new Nota(new AbstractMap.SimpleEntry<>("1", 10),student1,teme1,9,5);
        Nota nota2 = new Nota(new AbstractMap.SimpleEntry<>("2", 6),student2,teme2,7,5);

        try{
            if (srv.find(nota1.getID()) == null)
                srv.add(nota1,"das");
        }catch (ValidationException | NumberFormatException ignored){}
        assertEquals(1,srv.getSize());

        try{
            if (srv.find(nota2.getID()) == null)
                srv.add(nota2, "asd");
        }catch (ValidationException | NumberFormatException ignored){}
        assertEquals(2,srv.getSize());
    }

    @Test
    public void checkDuplicate(){
        Teme teme1 = new Teme(10,"dfgfdgfdg",1,10);
        Teme teme2 = new Teme(6,"dfgfdgfdg",1,10);
        Student student1 = new Student("1","name",932,"Lemne@.com","Prof");
        Student student2 = new Student("2","name",932,"Lemne@.com","Prof");
        Nota nota1 = new Nota(new AbstractMap.SimpleEntry<>("1", 10),student1,teme1,9,5);
        Nota nota2 = new Nota(new AbstractMap.SimpleEntry<>("2", 6),student2,teme2,7,5);
        Nota nota3 = new Nota(new AbstractMap.SimpleEntry<>("2", 6),student2,teme2,7,5);

        try{
            if (srv.find(nota1.getID()) == null)
                srv.add(nota1,"das");
        }catch (ValidationException | NumberFormatException ignored){}
        assertEquals(1,srv.getSize());

        try{
            if (srv.find(nota2.getID()) == null)
                srv.add(nota2, "asd");
        }catch (ValidationException | NumberFormatException ignored){}
        assertEquals(2,srv.getSize());

        try{
            if (srv.find(nota3.getID()) == null)
                srv.add(nota3, "asd");
        }catch (ValidationException | NumberFormatException ignored){}
        assertEquals(2,srv.getSize());
    }

    @Test
    public void checkMark(){
        Teme teme1 = new Teme(10,"dfgfdgfdg",1,10);
        Student student1 = new Student("1","name",932,"Lemne@.com","Prof");
        Student student2 = new Student("2","name",932,"Lemne@.com","Prof");
        Student student3 = new Student("3","name",932,"Lemne@.com","Prof");
        Student student4 = new Student("4","name",932,"Lemne@.com","Prof");
        Nota nota1 = new Nota(new AbstractMap.SimpleEntry<>("1", 10),student1,teme1,9,5);
        Nota nota2 = new Nota(new AbstractMap.SimpleEntry<>("2", 10),student2,teme1,70,5);
        Nota nota3 = new Nota(new AbstractMap.SimpleEntry<>("3", 10),student3,teme1,-7,5);
        Nota nota4 = new Nota(new AbstractMap.SimpleEntry<>("4", 10),student4,teme1,7,5);

        try{
            if (srv.find(nota1.getID()) == null)
                srv.add(nota1,"das");
        }catch (ValidationException | NumberFormatException ignored){}
        assertEquals(1,srv.getSize());

        try{
            if (srv.find(nota2.getID()) == null)
                srv.add(nota2, "asd");
        }catch (ValidationException | NumberFormatException ignored){}
        assertEquals(1,srv.getSize());

        try{
            if (srv.find(nota3.getID()) == null)
                srv.add(nota3, "asd");
        }catch (ValidationException | NumberFormatException ignored){}
        assertEquals(1,srv.getSize());

        try{
            if (srv.find(nota4.getID()) == null)
                srv.add(nota4, "asd");
        }catch (ValidationException | NumberFormatException ignored){}
        assertEquals(2,srv.getSize());
    }

    @Test
    public void checkSaptPrimire(){
        Teme teme1 = new Teme(10,"dfgfdgfdg",1,10);
        Student student1 = new Student("1","name",932,"Lemne@.com","Prof");
        Student student2 = new Student("2","name",932,"Lemne@.com","Prof");
        Student student3 = new Student("3","name",932,"Lemne@.com","Prof");
        Student student4 = new Student("4","name",932,"Lemne@.com","Prof");
        Nota nota1 = new Nota(new AbstractMap.SimpleEntry<>("1", 10),student1,teme1,9,5);
        Nota nota2 = new Nota(new AbstractMap.SimpleEntry<>("2", 10),student2,teme1,7,50);
        Nota nota3 = new Nota(new AbstractMap.SimpleEntry<>("3", 10),student3,teme1,7,-5);
        Nota nota4 = new Nota(new AbstractMap.SimpleEntry<>("4", 10),student4,teme1,7,5);

        try{
            if (srv.find(nota1.getID()) == null)
                srv.add(nota1,"das");
        }catch (ValidationException | NumberFormatException ignored){}
        assertEquals(1,srv.getSize());

        try{
            if (srv.find(nota2.getID()) == null)
                srv.add(nota2, "asd");
        }catch (ValidationException | NumberFormatException ignored){}
        assertEquals(1,srv.getSize());

        try{
            if (srv.find(nota3.getID()) == null)
                srv.add(nota3, "asd");
        }catch (ValidationException | NumberFormatException ignored){}
        assertEquals(1,srv.getSize());

        try{
            if (srv.find(nota4.getID()) == null)
                srv.add(nota4, "asd");
        }catch (ValidationException | NumberFormatException ignored){}
        assertEquals(2,srv.getSize());
    }

    @Test
    public void checkDeadline(){
        Teme teme1 = new Teme(10,"dfgfdgfdg",1,10);
        Teme teme2 = new Teme(10,"dfgfdgfdg",1,100);
        Teme teme3 = new Teme(10,"dfgfdgfdg",1,-10);
        Teme teme4 = new Teme(10,"dfgfdgfdg",1,10);
        Student student1 = new Student("1","name",932,"Lemne@.com","Prof");
        Student student2 = new Student("2","name",932,"Lemne@.com","Prof");
        Student student3 = new Student("3","name",932,"Lemne@.com","Prof");
        Student student4 = new Student("4","name",932,"Lemne@.com","Prof");
        Nota nota1 = new Nota(new AbstractMap.SimpleEntry<>("1", 10),student1,teme1,9,5);
        Nota nota2 = new Nota(new AbstractMap.SimpleEntry<>("2", 10),student2,teme2,7,5);
        Nota nota3 = new Nota(new AbstractMap.SimpleEntry<>("3", 10),student3,teme3,7,5);
        Nota nota4 = new Nota(new AbstractMap.SimpleEntry<>("4", 10),student4,teme4,7,5);

        try{
            if (srv.find(nota1.getID()) == null)
                srv.add(nota1,"das");
        }catch (ValidationException | NumberFormatException ignored){}
        assertEquals(1,srv.getSize());

        try{
            if (srv.find(nota2.getID()) == null)
                srv.add(nota2, "asd");
        }catch (ValidationException | NumberFormatException ignored){}
        assertEquals(1,srv.getSize());

        try{
            if (srv.find(nota3.getID()) == null)
                srv.add(nota3, "asd");
        }catch (ValidationException | NumberFormatException ignored){}
        assertEquals(1,srv.getSize());

        try{
            if (srv.find(nota4.getID()) == null)
                srv.add(nota4, "asd");
        }catch (ValidationException | NumberFormatException ignored){}
        assertEquals(2,srv.getSize());
    }
}
