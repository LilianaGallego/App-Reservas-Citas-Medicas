package co.com.sofka.model.paciente.values;

import co.com.sofka.model.generic.ValueObject;

public class Celular implements ValueObject<String> {
    private String celular;

    public Celular(String celular) {
        this.celular = celular;
    }

    @Override
    public String value(){
        return celular;
    }


}
