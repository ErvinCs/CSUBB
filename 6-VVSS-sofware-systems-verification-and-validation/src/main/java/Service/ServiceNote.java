package Service;


import Domain.Nota;
import Repository.NoteRepo;

import java.util.Map;

public class ServiceNote {
    private NoteRepo rep;
    public ServiceNote(NoteRepo rep){this.rep=rep;}
    /**
     * Adauga Nota
     * Returneaza Nota adaugata
     */
    public Nota add(Nota s, String fd){
        return rep.save(s,fd);
    }

    public Iterable<Nota> all(){
        return rep.findAll();
    }
    public Nota find(Map.Entry<String,Integer> nota) {return rep.findOne(nota);}
    public int getSize() {return  rep.size();}
}

