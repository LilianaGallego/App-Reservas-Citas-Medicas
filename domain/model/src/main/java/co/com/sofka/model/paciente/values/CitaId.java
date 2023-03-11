package co.com.sofka.model.paciente.values;

import co.com.sofka.model.generic.Identity;

public class CitaId extends Identity {
    private  CitaId (String uuid){
        super(uuid);
    }

    public CitaId(){}

    public static CitaId of(String uuid){
        return new CitaId(uuid);
    }

}
