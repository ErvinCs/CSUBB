package Lab2Maven;

import Repository.NoteRepo;
import Repository.StudentRepo;
import Repository.TemeRepo;
import Service.ServiceNote;
import Service.ServiceStudent;
import Service.ServiceTeme;
import UI.UI;
import Validator.NotaValidator;
import Validator.StudentValidator;
import Validator.TemeValidator;

import java.io.IOException;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        String dirPath = System.getProperty("user.dir");
        //System.out.println("Present Project Directory : " + dirPath);

        StudentRepo rep = new StudentRepo(new StudentValidator(),dirPath + "\\src\\studenti.xml",true);
        TemeRepo repo = new TemeRepo(new TemeValidator(),dirPath + "\\src\\teme.xml",true);
        NoteRepo r = new NoteRepo(new NotaValidator());
        ServiceStudent srv = new ServiceStudent(rep);
        ServiceTeme serv = new ServiceTeme(repo);
        ServiceNote sv = new ServiceNote(r);
        UI ui = new UI(srv,serv,sv);
        try {
            ui.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println( "Hello World!" );
    }
}
