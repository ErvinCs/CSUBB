package Validator;

import Domain.Teme;

public class TemeValidator implements Validator<Teme> {
    /**
     * Validates Teme instaces
     * @param t Teme entity
     * @return error msg String
     */
    @Override
    public String validate(Teme t) {
        String m = new String();
        m = "";
        //TODO - define a concrete type for the id; it can't be both a string and an integer
        if (t.getID() == null || t.getID()<1)
            m = m + "\nID invalid";
        if (t.getDeadline()>14 || t.getDeadline()<1 || t.getDeadline()<t.getSapt_primire())
            m = m + "\nDeadline invalid";
        if(t.getSapt_primire()>14 || t.getSapt_primire()<1)
            m=m+"\nSaptamana in care tema a fost primita este invalida";

        return m;
    }
}
