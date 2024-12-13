package Lab2Maven;

import Domain.Teme;
import Repository.TemeRepo;
import Service.ServiceTeme;
import Validator.*;
import org.junit.Test;


import static org.junit.Assert.*;

public class AddTheme {
    private String dirPath = System.getProperty("user.dir");
    private TemeRepo repo = new TemeRepo(new TemeValidator(),dirPath + "\\src\\teme.xml",false);
    private ServiceTeme srv = new ServiceTeme(repo);

    @Test
    public void addTheme() {

        Teme teme= new Teme(123,"fgdfgd",12,12);
        Teme teme1 = new Teme(1,"dfgfdgfdg",1,10);

        try{
            if (srv.find(teme.getID()) == null)
                srv.add(teme);
        }catch (ValidationException | NumberFormatException ignored){}
        assertEquals(1,srv.getSize());

        try{
            if (srv.find(teme1.getID()) == null)
                srv.add(teme1);
        }catch (ValidationException | NumberFormatException ignored){}
        assertEquals(2,srv.getSize());
    }

    @Test
    public void checkDuplicates(){
        Teme teme= new Teme(123,"fgdfgd",12,12);

        try{
            if (srv.find(teme.getID()) == null)
                srv.add(teme);
        }catch (ValidationException | NumberFormatException ignored){}

        try{
            if (srv.find(teme.getID()) == null)
                srv.add(teme);
        }catch (ValidationException | NumberFormatException ignored){}
        assertEquals(1,srv.getSize());
    }

    @Test
    public void checkId(){
        Teme teme= new Teme(123,"fgdfgd",12,12);
        Teme teme1 = new Teme(-1,"dfgfdgfdg",1,10);
        Teme teme2 = new Teme(null,"dfgfdgfdg",1,10);

        try{
            if (srv.find(teme.getID()) == null)
                srv.add(teme);
        }catch (ValidationException | NumberFormatException ignored){}

        try{
            if (srv.find(teme1.getID()) == null)
                srv.add(teme1);
        }catch (ValidationException | NumberFormatException ignored){}

        try{
            if (srv.find(teme2.getID()) == null)
                srv.add(teme2);
        }catch (ValidationException | NumberFormatException ignored){}
        assertEquals(1,srv.getSize());
    }

    @Test
    public void checkDeadline(){
        Teme teme= new Teme(123,"fgdfgd",12,12);
        Teme teme1 = new Teme(1,"dfgfdgfdg",1,16);
        Teme teme2 = new Teme(10,"dfgfdgfdg",0,0);
        Teme teme4 = new Teme(10,"dfgfdgfdg",-1,-1);
        Teme teme3 = new Teme(10,"dfgfdgfdg",11,6);

        try{
            if (srv.find(teme.getID()) == null)
                srv.add(teme);
        }catch (ValidationException | NumberFormatException ignored){}

        try{
            if (srv.find(teme1.getID()) == null)
                srv.add(teme1);
        }catch (ValidationException | NumberFormatException ignored){}

        try{
            if (srv.find(teme2.getID()) == null)
                srv.add(teme2);
        }catch (ValidationException | NumberFormatException ignored){}
        try{
            if (srv.find(teme4.getID()) == null)
                srv.add(teme4);
        }catch (ValidationException | NumberFormatException ignored){}

        try{
            if (srv.find(teme3.getID()) == null)
                srv.add(teme3);
        }catch (ValidationException | NumberFormatException ignored){}
        assertEquals(1,srv.getSize());
    }

    @Test
    public void checkSaptPrimire(){
        Teme teme= new Teme(123,"fgdfgd",12,12);
        Teme teme1 = new Teme(1,"dfgfdgfdg",156,1);
        Teme teme2 = new Teme(10,"dfgfdgfdg",0,7);
        Teme teme3 = new Teme(10,"dfgfdgfdg",-1,7);

        try{
            if (srv.find(teme.getID()) == null)
                srv.add(teme);
        }catch (ValidationException | NumberFormatException ignored){}

        try{
            if (srv.find(teme1.getID()) == null)
                srv.add(teme1);
        }catch (ValidationException | NumberFormatException ignored){}

        try{
            if (srv.find(teme3.getID()) == null)
                srv.add(teme3);
        }catch (ValidationException | NumberFormatException ignored){}

        try{
            if (srv.find(teme2.getID()) == null)
                srv.add(teme2);
        }catch (ValidationException | NumberFormatException ignored){}
        assertEquals(1,srv.getSize());
    }
}
