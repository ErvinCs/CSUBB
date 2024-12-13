package Domain.Validators;

import Domain.Client;

public class ClientValidator implements Validator<Client> {
    @Override
    public void validate(Client entity) throws ValidatorException {
        String errors="";
        if(entity.getID()==null)
            errors+="Invalid ID!\n";
        if (entity.getName()=="")
            errors+="Invalid Name!\n";
        if(entity.getCountry()=="")
            errors+="Invalid Country!\n";
        if (errors.length()>0)
            throw new ValidatorException(errors);
    }
}
