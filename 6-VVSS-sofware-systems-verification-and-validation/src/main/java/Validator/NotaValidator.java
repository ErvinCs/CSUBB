package Validator;

import Domain.Nota;

public class NotaValidator implements Validator<Nota> {
    /**
     * Validates a Nota entity
     * @param st Nota entity
     * @return error msg String
     */
    @Override
    public String validate(Nota st) {
        String m = new String();
        m = "";
        if(st.getDl()<1 || st.getDl()>14)
            m=m+"Deadline gresit.\n";
        if(st.getPredat()<1 || st.getPredat()>14)
            m=m+"Saptamana in care a fost primita tema e gresita.\n";
        if(st.getVal()<0 || st.getVal()>10)
            m=m+"Nota gresita.\n";
        return m;
    }
}
