package Domain;

import java.util.Map;

/**
 * Grade entity.
 * Holds reference to a st: Student and a tm: Teme.
 * Holds the valoare: float of a grade and the (week) data: int of it's assignment.
 */
public class Nota implements hasID<Map.Entry<String,Integer>> {
    private Map.Entry<String,Integer> id;
    private Student st;
    private Teme tm;
    private float valoare;
    private int data;   //Week number
    public Nota(Map.Entry<String,Integer> id,Student s,Teme t, float val, int d){
        this.id=id;
        this.st=s;
        this.tm=t;
        this.valoare=val;
        this.data=d;
    }
    public Map.Entry<String,Integer> getID(){
        return id;
    }
    public void setID(Map.Entry<String,Integer> x){
        this.id=x;
    }
    public String getStud(){
        return st.getID();
    }
    public Integer getTema(){
        return this.tm.getID();
    }
    public Student getStudent(){return st;}
    public float getVal(){
        return this.valoare;
    }
    public float getValoare(){

        if(this.getDl()-this.getPredat()==-1)
            this.valoare=valoare-2.5f;
        if(this.getPredat()-this.getDl()==2)
            this.valoare=valoare-5;
        if(this.getPredat()-this.getDl()>2)
            this.valoare=0;
        if(this.valoare<0)
            this.valoare=0;
        return this.valoare;
    }
    public int getPredat(){
        return this.data;
    }
    public int getDl(){
        return this.tm.getDeadline();
    }

    @Override
    public String toString() {
        return "Nota{ " + "ID=" + this.id.getValue().toString() + "; " +
                "studentID=" + this.st.getID() + "; " +
                "temeID=" + this.tm.getID().toString() + "; " +
                "valoare=" + this.valoare + "; " +
                "data=" + this.data +
                "}";
    }
}
