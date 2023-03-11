package co.com.sofka.model.paciente.values;

import co.com.sofka.model.generic.ValueObject;

public class Apellidos  implements ValueObject<String> {
    private String apellidos;

    public Apellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    @Override
    public String value(){
        return apellidos;
    }


}
