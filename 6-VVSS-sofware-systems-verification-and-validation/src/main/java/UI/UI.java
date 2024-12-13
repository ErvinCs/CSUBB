package UI;

import Domain.Nota;
import Domain.Student;
import Domain.Teme;
import Service.ServiceNote;
import Service.ServiceStudent;
import Service.ServiceTeme;
import Validator.ValidationException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.AbstractMap;
import java.util.Map;

/* Changes:
 *  - catch NumberFormatException on line 72
 *  - lines 103-105
 *  - line 117, 134-136
 *  - added print statement, line 160
 *  - catch NumberFormatException on line 163
 *  - catch NumberFormatException on line 96
 */
public class UI {
    private ServiceStudent srv;
    private ServiceTeme serv;
    private ServiceNote sv;
    public UI(ServiceStudent srv, ServiceTeme serv, ServiceNote sv){
        this.srv=srv;
        this.serv=serv;
        this.sv=sv;
    }
    public void show() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        while(true) {
            System.out.print("Meniu: ");
            System.out.print("\n1. Afiseaza studenti");
            System.out.print("\n2. Afiseaza teme");
            System.out.print("\n3. Adauga student");
            System.out.print("\n4. Adauga tema");
            System.out.print("\n5. Sterge student");
            System.out.print("\n6. Gaseste student");
            System.out.print("\n7. Modifica student");
            System.out.print("\n8. Adauga nota");
            System.out.print("\n0. Exit\n");
            String s = br.readLine();
            if(s.equals("1"))
                for(Student st:srv.all())
                    System.out.println(st);
            if(s.equals("2"))
                for(Teme t:serv.all())
                    System.out.println(t);
            if(s.equals("3")){
                try {
                    System.out.println("ID: ");
                    String id = br.readLine();
                    System.out.println("Nume: ");
                    String nume = br.readLine();
                    System.out.println("Grupa: ");
                    String g = br.readLine();
                    int gr = Integer.parseInt(g);
                    System.out.println("Email: ");
                    String em = br.readLine();
                    System.out.println("Profesor: ");
                    String prof = br.readLine();
                    if(srv.find(id)==null) {
                        Student stud = new Student(id, nume, gr, em, prof);
                        srv.add(stud);
                    }
                    else System.out.println("ID deja existent");
                }catch(ValidationException | NumberFormatException ex){
                    System.out.println(ex.getMessage());
                }
            }
            if(s.equals("4")) {
                try {
                    System.out.println("Numar: ");
                    String num = br.readLine();
                    int nr = Integer.parseInt(num);
                    System.out.println("Descriere: ");
                    String desc = br.readLine();
                    System.out.println("Saptamana primire: ");
                    String sp = br.readLine();
                    int sapt = Integer.parseInt(sp);
                    System.out.println("Deadline: ");
                    String dl = br.readLine();
                    int d = Integer.parseInt(dl);
                    if(serv.find(nr)==null) {
                        Teme tema = new Teme(nr, desc, sapt, d);
                        serv.add(tema);
                    }
                    else System.out.println("ID deja existent");
                }catch(ValidationException | NumberFormatException ex){
                    System.out.println(ex);
                }
            }
            if(s.equals("5")){
                System.out.println("Dati id-ul: ");
                String id=br.readLine();
                if(srv.find(id)!=null)
                    srv.del(id);
                else System.out.println("Nu exista acest student");
            }
            if(s.equals("6")){
                System.out.println("Dati id-ul");
                String id=br.readLine();
                if(srv.find(id)!=null)
                    System.out.println(srv.find(id));
                else System.out.println("Nu exista acest student");
            }
            if(s.equals("7")) {
                try {
                    System.out.println("Dati id-ul");
                    String id = br.readLine();
                    System.out.println("Nume: ");
                    String nume = br.readLine();
                    System.out.println("Grupa: ");
                    String g = br.readLine();
                    int gr = Integer.parseInt(g);
                    System.out.println("Email: ");
                    String em = br.readLine();
                    System.out.println("Profesor: ");
                    String prof = br.readLine();
                    if (srv.find(id) != null) {
                        Student ss = new Student(id, nume, gr, em, prof);
                        srv.mod(ss);
                    } else System.out.println("Nu exista acest student.");
                } catch (NumberFormatException | ValidationException ex) {
                    System.out.println(ex.toString());
                }
            }
            if(s.equals("8")) {
                try {
                    System.out.println("ID-ul studentului: ");
                    String id = br.readLine();
                    System.out.println("Numarul temei: ");
                    String num = br.readLine();
                    int nr = Integer.parseInt(num);
                    System.out.println("Nota: ");
                    String n = br.readLine();
                    float nota = Float.parseFloat(n);
                    System.out.println("Data (Week No.): ");
                    String dt = br.readLine();
                    int data = Integer.parseInt(dt);
                    System.out.println("Feedback: ");
                    String fd=br.readLine();
                    if (srv.find(id) != null && serv.find(nr) != null) {
                        Student st = srv.find(id);
                        Teme tm = serv.find(nr);
                        Map.Entry<String, Integer> nid = new AbstractMap.SimpleEntry<String, Integer>(id, nr);
                        Nota nt = new Nota(nid, st, tm, nota, data);
                        nota = nt.getValoare();
                        nt = new Nota(nid, st, tm, nota, data);
                        sv.add(nt,fd);
                        System.out.println("Adaugat: " + nt.toString());
                    } else System.out.println("Student sau tema invalida.");
                }
                catch(ValidationException | NumberFormatException ex){
                    System.out.println(ex);
                }
            }
            if(s.equals("0"))
                break;
        }
    }
}

