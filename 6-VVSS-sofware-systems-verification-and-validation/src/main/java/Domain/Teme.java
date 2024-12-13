package Domain;

/**
 * A theme entity
 * Fields:
 *  nr: Integer
 *  descriere: String
 *  deadline: Integer
 *  sapt_primire: Integer
 */
public class Teme implements hasID<Integer> {
    private Integer nr;
    private String descriere;
    private Integer deadline;
    private Integer sapt_primire;
    public Teme(Integer nr, String descriere, Integer sapt_primire,Integer deadline){
        this.deadline=deadline;
        this.descriere=descriere;
        this.nr=nr;
        this.sapt_primire=sapt_primire;
    }
    public Integer getID(){
        return nr;
    }
    public void setID(Integer id){
        this.nr=id;
    }
    public String getDescriere(){
        return descriere;
    }
    public void setDescriere(String nume){
        this.descriere=nume;
    }
    public Integer getDeadline(){
        return deadline;
    }
    public void setDeadline(Integer deadline){
        this.deadline=deadline;
    }
    public Integer getSapt_primire(){
        return sapt_primire;
    }
    public void setSapt_primire(Integer s){
        this.sapt_primire=s;
    }
    public String toString(){
        return nr.toString()+' '+descriere+' '+sapt_primire.toString()+' '+deadline.toString();
    }
}
