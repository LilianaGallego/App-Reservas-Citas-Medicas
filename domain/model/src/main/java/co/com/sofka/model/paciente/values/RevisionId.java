package co.com.sofka.model.paciente.values;

import co.com.sofka.model.paciente.generic.Identity;

public class RevisionId extends Identity {
    private  RevisionId (String uuid){
        super(uuid);
    }

    public RevisionId(){}

    public static RevisionId of(String uuid){
        return new RevisionId(uuid);
    }

}
