package co.com.sofka.model.paciente.values;

import co.com.sofka.model.generic.ValueObject;

public class Correo  implements ValueObject<String> {
    private String correo;

    public Correo(String correo) {
        this.correo = correo;
    }

    @Override
    public String value(){
        return correo;
    }


}
