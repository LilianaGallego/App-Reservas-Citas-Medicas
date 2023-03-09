package co.com.sofka.model.paciente.values;

import co.com.sofka.model.paciente.generic.ValueObject;

public class Nombres implements ValueObject<String> {
    private String nombres;

    public Nombres(String nombres) {
        this.nombres = nombres;
    }

    @Override
    public String value(){
        return nombres;
    }


}
