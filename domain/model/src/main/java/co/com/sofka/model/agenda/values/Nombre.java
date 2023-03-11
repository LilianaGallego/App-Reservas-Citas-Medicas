package co.com.sofka.model.agenda.values;

import co.com.sofka.model.agenda.generic.ValueObject;


public class Nombre implements ValueObject<String> {
    private String value;

    public Nombre(String value) {
        this.value = value;
    }

    @Override
    public String value(){
        return value;
    }


}
