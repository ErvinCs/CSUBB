package Domain;

/**
 * A student entity
 * Fields:
 *  idStudent: String
 *  nume: String
 *  grupa: int
 *  email: String
 *  profesor: String
 *
 */
public class Student implements hasID<String> {
    private String idStudent;
    private String nume;
    private int grupa ;
    private String email;
    private String profesor;
    public Student (String id,String n, int gr, String e,String prof){
        this.idStudent=id;
        this.nume=n;
        this.grupa=gr;
        this.email=e;
        this.profesor=prof;
    }
    public String getID(){
        return idStudent;
    }
    public void setID(String id){
        this.idStudent=id;
    }
    public String getNume(){
        return nume;
    }
    public void setNume(String nume){
        this.nume=nume;
    }
    public int getGrupa(){
        return grupa;
    }
    public void setGrupa(int grupa){
        this.grupa=grupa;
    }
    public String getMail(){
        return email;
    }
    public void setMail(String mail){
        this.email=mail;
    }
    public String getProfesor(){
        return profesor;
    }
    public void setProfesor(String prof){
        this.profesor=prof;
    }
    //TODO
    public String toString(){
        return idStudent+' '+nume+' '+grupa+' '+email+' '+profesor;
    }
    //TODO
    public boolean equals(Object object2) {
        return object2 instanceof Student && this.email==((Student) object2).email && this.grupa==((Student) object2).grupa && this.idStudent==((Student) object2).idStudent && this.nume==((Student) object2).nume && this.profesor==((Student) object2).profesor;
    }
}

